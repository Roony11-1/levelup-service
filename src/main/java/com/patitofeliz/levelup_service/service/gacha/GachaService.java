package com.patitofeliz.levelup_service.service.gacha;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patitofeliz.levelup_service.model.gacha.Banner;
import com.patitofeliz.levelup_service.model.gacha.BannerItem;
import com.patitofeliz.levelup_service.model.gacha.Pull;
import com.patitofeliz.levelup_service.repository.gacha.BannerRepository;
import com.patitofeliz.levelup_service.repository.gacha.PullRepository;

@Service
public class GachaService 
{
    @Autowired
    private BannerRepository bannerRepository;
    @Autowired
    private PullRepository pullRepository;

    public Pull pull(int bannerId, int usuarioId) 
    {
        Banner banner = bannerRepository.findById(bannerId)
                .orElseThrow(() -> new RuntimeException("Banner no encontrado"));

        List<BannerItem> items = banner.getItems();

        if (items.isEmpty())
            throw new RuntimeException("El banner no tiene items");

        BannerItem resultado = getItemPorProbabilidad(items);

        Pull pull = new Pull();
        pull.setUsuarioId(usuarioId);
        pull.setBanner(banner);
        pull.setItemResultado(resultado);

        return pullRepository.save(pull);
    }

    private BannerItem getItemPorProbabilidad(List<BannerItem> items) 
    {
        double totalProb = items.stream().mapToDouble(BannerItem::getProbabilidad).sum();

        double rand = Math.random() * totalProb;
        double acumulado = 0.0;

        for (BannerItem item : items) 
        {
            acumulado += item.getProbabilidad();
            if (rand <= acumulado)
                return item;
        }

        return items.get(items.size() - 1);
    }
}
