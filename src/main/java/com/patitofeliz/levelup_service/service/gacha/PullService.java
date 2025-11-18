package com.patitofeliz.levelup_service.service.gacha;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patitofeliz.levelup_service.model.gacha.Pull;
import com.patitofeliz.levelup_service.repository.gacha.PullRepository;

@Service
public class PullService 
{
    @Autowired
    private PullRepository pullRepository;

    public List<Pull> findAll()
    {
        return pullRepository.findAll();
    }

    public Pull save(Pull pull)
    {
        return pullRepository.save(pull);
    }
}
