package com.patitofeliz.levelup_service.model.gacha;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Banner 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String descripcion;
    private boolean activo;

    @ManyToMany
    @JoinTable(
        name = "banner_items",
        joinColumns = @JoinColumn(name = "banner_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<BannerItem> items;
}