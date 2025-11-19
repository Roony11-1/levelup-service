package com.patitofeliz.levelup_service.controller.gacha;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patitofeliz.levelup_service.model.gacha.Banner;
import com.patitofeliz.levelup_service.service.gacha.BannerService;

@RestController
@RequestMapping("/api/banner")
public class BannerController 
{
    @Autowired
    private BannerService bannerService;

    @GetMapping
    public ResponseEntity<List<Banner>> findAll()
    {
        List<Banner> listaBanners = bannerService.findAll();

        if (listaBanners.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(listaBanners);
    }

    @PostMapping("/{bannerId}/item/{itemId}")
    public ResponseEntity<Banner> agregarItem(@PathVariable int bannerId, @PathVariable int itemId) 
    {
        Banner bannerModificado = bannerService.agregarItem(bannerId, itemId);
        return ResponseEntity.ok(bannerModificado);
    }

    @DeleteMapping ("/{bannerId}/item/{itemId}")
    public ResponseEntity<Banner> quitarItem(@PathVariable int bannerId, @PathVariable int itemId)
    {
        Banner bannerModificado = bannerService.quitarItem(bannerId, itemId);
        return ResponseEntity.ok(bannerModificado);
    }
}
