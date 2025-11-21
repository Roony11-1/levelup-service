package com.patitofeliz.levelup_service.repository.producto;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.patitofeliz.levelup_service.model.producto.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>
{
    List<Producto> findByDestacado(boolean destacado);
    List<Producto> findByCategoriaContainingIgnoreCase(String categoria);
    List<Producto> findByMarcaContainingIgnoreCase(String marca);
    List<Producto> findAllByNombreContainingIgnoreCase(String nombre);
    Optional<Producto> findByNombre(String nombre);

    // Marcas únicas
    @Query("SELECT DISTINCT p.marca FROM Producto p")
    List<String> findDistinctMarcas();

    // Categorías únicas
    @Query("SELECT DISTINCT p.categoria FROM Producto p")
    List<String> findDistinctCategorias();
}
