package com.patitofeliz.levelup_service.service.gacha;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patitofeliz.levelup_service.model.gacha.Banner;
import com.patitofeliz.levelup_service.model.gacha.BannerItem;
import com.patitofeliz.levelup_service.repository.gacha.BannerItemRepository;
import com.patitofeliz.levelup_service.repository.gacha.BannerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BannerService 
{
    private final BannerRepository bannerRepository;
    private final BannerItemRepository bannerItemRepository;

    public List<Banner> findAll()
    {
        return bannerRepository.findAll();
    }

    public List<Banner> findByActivo(boolean activo)
    {
        List<Banner> banners = bannerRepository.findByActivo(activo);

        for (Banner banner : banners) 
        {
            if (banner.getItems() != null) 
            {
                banner.setItems(
                    banner.getItems().stream()
                        .filter(BannerItem::isActivo)
                        .collect(Collectors.toList()));
            }
        }
        return banners;
    }

    public Banner save(Banner banner) 
    {
        if (banner.getItems().size() == 0)
            banner.setActivo(false);

        return bannerRepository.save(banner);
    }

    @Transactional
    public Banner update(int bannerId, Banner bannerUpdater) 
    {
        Banner banner = bannerRepository.findById(bannerId)
            .orElseThrow(() -> new RuntimeException("Banner no encontrado"));

        banner.setNombre(bannerUpdater.getNombre());
        banner.setDescripcion(bannerUpdater.getDescripcion());
        banner.setActivo(bannerUpdater.isActivo());

        if (bannerUpdater.getItems() != null)
            banner.setItems(bannerUpdater.getItems());

        if (banner.getItems().size() == 0)
            banner.setActivo(false);

        return bannerRepository.save(banner);
    }

    @Transactional
    public void deleteById(int id)
    {
        Banner banner = bannerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Banner no encontrado"));

        banner.getItems().clear(); // Limpiamos la lista para borrar las relaciónes

        bannerRepository.save(banner); // hacemos "Commit" a los cambios

        bannerRepository.deleteById(id); // Borramos
    }

    public Banner agregarItem(int bannerId, int itemId)
    {
        Banner banner = bannerRepository.findById(bannerId)
                .orElseThrow(() -> new RuntimeException("Banner no encontrado"));

        BannerItem item = bannerItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));

        if (banner.getItems() == null)
            banner.setItems(new ArrayList<>());

        boolean yaExiste = banner.getItems()
                .stream()
                .anyMatch(i -> i.getId() == item.getId());

        if (yaExiste)
            throw new RuntimeException("El item ya está agregado a este banner");

        banner.getItems().add(item);

        return bannerRepository.save(banner);
    }

    public Banner quitarItem(int bannerId, int itemId)
    {
        Banner banner = bannerRepository.findById(bannerId)
                .orElseThrow(() -> new RuntimeException("Banner no encontrado"));

        if (banner.getItems() == null || banner.getItems().isEmpty())
            throw new RuntimeException("El banner no tiene items asignados");

        BannerItem itemAEliminar = banner.getItems()
                .stream()
                .filter(i -> i.getId() == itemId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ese item no está asignado al banner"));

        banner.getItems().remove(itemAEliminar);

        return bannerRepository.save(banner);
    }
}
