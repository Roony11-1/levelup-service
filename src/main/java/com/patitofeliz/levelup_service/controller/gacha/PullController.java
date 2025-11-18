package com.patitofeliz.levelup_service.controller.gacha;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patitofeliz.levelup_service.model.gacha.Pull;
import com.patitofeliz.levelup_service.service.gacha.PullService;

@RestController
@RequestMapping("/api/gacha")
@CrossOrigin(origins = "http://localhost:5173")
public class PullController 
{
    @Autowired
    private PullService pullService;

    @GetMapping
    public ResponseEntity<List<Pull>> findAll()
    {
        List<Pull> listaTiradas = pullService.findAll();

        if (listaTiradas.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(listaTiradas);
    }

    @PostMapping
    public ResponseEntity<Pull> save(@RequestBody Pull tirada)
    {
        return ResponseEntity.ok(pullService.save(tirada));
    }
}
