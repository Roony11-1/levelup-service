package com.patitofeliz.levelup_service.repository.gacha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patitofeliz.levelup_service.model.gacha.Pull;

@Repository
public interface PullRepository extends JpaRepository<Pull, Integer>
{

}
