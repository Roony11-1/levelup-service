package com.patitofeliz.levelup_service.service.producto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patitofeliz.levelup_service.model.producto.Producto;
import com.patitofeliz.levelup_service.repository.producto.ProductoRepository;

@Service
public class ProductoService 
{
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll()
    {
        return productoRepository.findAll();
    }

    public List<String> findDistinctCategorias()
    {
        return productoRepository.findDistinctCategorias();
    }

    public List<Producto> findByDestacado()
    {
        return productoRepository.findByDestacado(true);
    }

    public List<String> findDistinctMarcas()
    {
        return productoRepository.findDistinctMarcas();
    }

    public Map<String, Object> obtenerProductoConRelacionados(String nombre) 
    {
        Optional<Producto> productoOpt = productoRepository.findByNombre(nombre);

        if (productoOpt.isEmpty()) 
        {
            return Map.of(
                "producto", null,
                "relacionados", List.of()
            );
        }

        Producto producto = productoOpt.get();

        List<Producto> porCategoria = productoRepository.findByCategoria(producto.getCategoria());
        List<Producto> porMarca = productoRepository.findByMarca(producto.getMarca());

        List<Producto> relacionados = new ArrayList<>();
        relacionados.addAll(porCategoria);
        relacionados.addAll(porMarca);

        Set<Integer> idsVistos = new HashSet<>();

        relacionados = relacionados.stream()
            .filter(p -> p.getId() != producto.getId())   // <- aquí estaba el bug
            .filter(p -> idsVistos.add(p.getId()))
            .toList();

        return Map.of(
            "producto", producto,
            "relacionados", relacionados
        );
    }
}
