package com.patitofeliz.levelup_service.service.gacha;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patitofeliz.levelup_service.model.gacha.BannerItem;
import com.patitofeliz.levelup_service.repository.gacha.BannerItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BannerItemService 
{
    private final BannerItemRepository bannerItemRepository;

    public List<BannerItem> findAll()
    {
        return bannerItemRepository.findAll();
    }

    public List<BannerItem> findAllByActivo(boolean activo)
    {
        return bannerItemRepository.findByActivo(activo);
    }

    @Transactional
    public BannerItem save(BannerItem bannerItem)
    {
        return bannerItemRepository.save(bannerItem);
    }

    @Transactional
    public BannerItem update(int id, BannerItem bannerItem)
    {
        BannerItem existente = bannerItemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("BannerItem no encontrado"));

        existente.setNombre(bannerItem.getNombre());
        existente.setClase(bannerItem.getClase());
        existente.setTipo(bannerItem.getTipo());
        existente.setRareza(bannerItem.getRareza());
        existente.setActivo(bannerItem.isActivo());
        existente.setProbabilidad(bannerItem.getProbabilidad());

        return bannerItemRepository.save(existente);
    }
}
