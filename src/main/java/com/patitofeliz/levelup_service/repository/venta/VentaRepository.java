package com.patitofeliz.levelup_service.repository.venta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patitofeliz.levelup_service.model.venta.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer>
{

}
