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

        // tendre rarezas B - A - S
        // Lista de objetos de el banner
        List<BannerItem> listaBanner = banner.getItems();

        if (listaBanner.isEmpty())
            throw new RuntimeException("El banner no tiene items asignados");

        // Metodo auxiliar par aescoger el item de el banner

        Pull pull = new Pull();

        return pullRepository.save(pull);
    }

    private BannerItem recompenza(List<BannerItem> recompenzas)
    {
        
    }
}
