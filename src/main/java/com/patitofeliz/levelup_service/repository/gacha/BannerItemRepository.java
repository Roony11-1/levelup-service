package com.patitofeliz.levelup_service.repository.gacha;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patitofeliz.levelup_service.model.gacha.BannerItem;

@Repository
public interface BannerItemRepository extends JpaRepository<BannerItem, Integer>
{
    List<BannerItem> findByActivo(boolean activo);
}
