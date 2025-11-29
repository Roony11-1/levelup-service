package com.patitofeliz.levelup_service.controller.gacha;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patitofeliz.levelup_service.model.gacha.Banner;
import com.patitofeliz.levelup_service.service.gacha.BannerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movil/banner")
@RequiredArgsConstructor
public class BannerController 
{
    private final BannerService bannerService;

    @GetMapping
    public ResponseEntity<List<Banner>> findAll()
    {
        List<Banner> listaBanners = bannerService.findAll();

        if (listaBanners.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(listaBanners);
    }

    @GetMapping("/activo/{activo}")
    public ResponseEntity<List<Banner>> findAllByActivo(@PathVariable(required = false) Boolean activo) 
    {
        if (activo == null)
            return ResponseEntity.noContent().build();

        List<Banner> banners = bannerService.findByActivo(activo);
        return ResponseEntity.ok(banners);
    }

    @PostMapping
    public ResponseEntity<Banner> save(@RequestBody Banner banner)
    {
        Banner bannerGuardado = bannerService.save(banner);

        return ResponseEntity.ok(bannerGuardado);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Banner> update(@PathVariable int id, @RequestBody Banner banner)
    {
        Banner updateado = bannerService.update(id, banner);

        return ResponseEntity.ok(updateado);
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
