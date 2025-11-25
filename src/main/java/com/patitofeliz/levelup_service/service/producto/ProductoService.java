package com.patitofeliz.levelup_service.service.producto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.patitofeliz.levelup_service.model.producto.Producto;
import com.patitofeliz.levelup_service.repository.producto.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService 
{
    private final ProductoRepository productoRepository;

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

    public List<Producto> getProductosByCategoria(String categoria) 
    {
        return productoRepository.findByCategoriaContainingIgnoreCase(categoria);
    }

    public List<Producto> getProductosByMarca(String marca) 
    {
        return productoRepository.findByMarcaContainingIgnoreCase(marca);
    }

    public List<Producto> getProductosByNombre(String nombre) 
    {
        return productoRepository.findAllByNombreContainingIgnoreCase(nombre);
    }

    // Nuevo método: busca por filtro en categoría, nombre o marca
    public List<Producto> findProducto(String filtro) 
    {
        List<Producto> productos = new ArrayList<>();
        productos.addAll(getProductosByCategoria(filtro));
        productos.addAll(getProductosByNombre(filtro));
        productos.addAll(getProductosByMarca(filtro));

        // Eliminar duplicados por id
        return productos.stream()
                .filter(distinctByKey(Producto::getId))
                .collect(Collectors.toList());
    }

    // Función auxiliar para filtrar por clave única
    private static <T> java.util.function.Predicate<T> distinctByKey(java.util.function.Function<? super T, ?> keyExtractor) 
    {
        Set<Object> seen = new HashSet<>();
        return t -> seen.add(keyExtractor.apply(t));
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

        List<Producto> porCategoria = productoRepository.findByCategoriaContainingIgnoreCase(producto.getCategoria());
        List<Producto> porMarca = productoRepository.findByMarcaContainingIgnoreCase(producto.getMarca());

        List<Producto> relacionados = new ArrayList<>();
        relacionados.addAll(porCategoria);
        relacionados.addAll(porMarca);

        Set<Integer> idsVistos = new HashSet<>();

        relacionados = relacionados.stream()
            .filter(p -> p.getId() != producto.getId())
            .filter(p -> idsVistos.add(p.getId()))
            .toList();

        return Map.of(
            "producto", producto,
            "relacionados", relacionados
        );
    }

    public Producto update(int id, Producto producto) 
    {
        Producto existente = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        existente.setNombre(producto.getNombre());
        existente.setDescripcion(producto.getDescripcion());
        existente.setPrecio(producto.getPrecio());
        existente.setCategoria(producto.getCategoria());
        existente.setMarca(producto.getMarca());
        existente.setDestacado(producto.isDestacado());
        existente.setCodigo(producto.getCodigo());
        existente.setCantidad(producto.getCantidad());
        existente.setImagen(producto.getImagen());
        existente.setOferta(producto.getOferta());

        return productoRepository.save(existente);
    }

    public void deleteById(int id)
    {
        productoRepository.deleteById(id);
    }
}
