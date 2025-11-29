package com.patitofeliz.levelup_service.repository.gacha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patitofeliz.levelup_service.model.gacha.Banner;
import com.patitofeliz.levelup_service.model.gacha.Pull;
import java.util.List;


@Repository
public interface PullRepository extends JpaRepository<Pull, Integer>
{
    List<Pull> findByBanner(Banner banner);
}
