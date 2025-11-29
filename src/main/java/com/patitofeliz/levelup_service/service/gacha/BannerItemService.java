package com.patitofeliz.levelup_service.service.gacha;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public BannerItem save(BannerItem bannerItem)
    {
        return bannerItemRepository.save(bannerItem);
    }
}
