package com.patitofeliz.levelup_service.controller.gacha;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.patitofeliz.levelup_service.model.dto.PullResponseDTO;
import com.patitofeliz.levelup_service.model.gacha.Pull;
import com.patitofeliz.levelup_service.service.gacha.GachaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/gacha")
@RequiredArgsConstructor
public class GachaController 
{
    private final GachaService gachaService;

    @PostMapping("/pull")
    public ResponseEntity<PullResponseDTO> pull(@RequestParam int bannerId, @RequestParam int usuarioId) 
    {
        Pull pull = gachaService.pull(bannerId, usuarioId);
        return ResponseEntity.ok(new PullResponseDTO(pull));
    }
}
