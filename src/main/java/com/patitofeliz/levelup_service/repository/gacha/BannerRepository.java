package com.patitofeliz.levelup_service.repository.gacha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patitofeliz.levelup_service.model.gacha.Banner;
import java.util.List;


@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer>
{
    List<Banner> findByActivo(boolean activo);
}
