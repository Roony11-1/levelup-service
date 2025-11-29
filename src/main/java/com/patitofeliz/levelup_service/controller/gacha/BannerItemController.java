package com.patitofeliz.levelup_service.controller.gacha;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patitofeliz.levelup_service.model.gacha.BannerItem;
import com.patitofeliz.levelup_service.service.gacha.BannerItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movil/banneritem")
@RequiredArgsConstructor
public class BannerItemController 
{
    private final BannerItemService bannerItemService;

    @GetMapping
    public ResponseEntity<List<BannerItem>> findAll()
    {
        List<BannerItem> items = bannerItemService.findAll();

        if (items.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(items);
    }

    @GetMapping("/activo/{activo}")
    public ResponseEntity<List<BannerItem>> findAll(@PathVariable boolean activo)
    {
        List<BannerItem> items = bannerItemService.findAllByActivo(activo);

        if (items.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<BannerItem> save(@RequestBody BannerItem bannerItem)
    {
        BannerItem bannerItemGuardado = bannerItemService.save(bannerItem);

        return ResponseEntity.ok(bannerItemGuardado);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<BannerItem> update(@PathVariable int id, @RequestBody BannerItem bannerItem)
    {
        BannerItem actualizado = bannerItemService.update(id, bannerItem);

        return ResponseEntity.ok(actualizado);
    }
}
