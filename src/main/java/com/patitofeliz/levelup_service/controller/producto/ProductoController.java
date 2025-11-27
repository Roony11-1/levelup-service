package com.patitofeliz.levelup_service.controller.producto;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patitofeliz.levelup_service.model.producto.Producto;
import com.patitofeliz.levelup_service.service.producto.ProductoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class ProductoController 
{
    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> findAll()
    {
        List<Producto> productos = productoService.findAll();

        if (productos.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/getDestacados")
    public ResponseEntity<List<Producto>> findDestacados()
    {
        return ResponseEntity.ok(productoService.findByDestacado());
    }

    @GetMapping("/getMarcas")
    public ResponseEntity<List<String>> findDistinctMarcas()
    {
        return ResponseEntity.ok(productoService.findDistinctMarcas());
    }

    @GetMapping("/getCategorias")
    public ResponseEntity<List<String>> findDistinctCategorias()
    {
        return ResponseEntity.ok(productoService.findDistinctCategorias());
    }

    @GetMapping("/con-relacionados/{nombre}")
    public ResponseEntity<?> obtenerConRelacionados(@PathVariable String nombre) 
    {
        return ResponseEntity.ok(productoService.obtenerProductoConRelacionados(nombre));
    }

    // Buscar por categoría
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> getProductosByCategoria(@PathVariable String categoria) 
    {
        List<Producto> productos = productoService.getProductosByCategoria(categoria);
        if (productos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(productos);
    }

    // Buscar por marca
    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<Producto>> getProductosByMarca(@PathVariable String marca) 
    {
        List<Producto> productos = productoService.getProductosByMarca(marca);
        if (productos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(productos);
    }

    // Buscar por nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Producto>> getProductosByNombre(@PathVariable String nombre) 
    {
        List<Producto> productos = productoService.getProductosByNombre(nombre);
        if (productos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscar/{filtro}")
    public ResponseEntity<List<Producto>> findProducto(@PathVariable String filtro) 
    {
        List<Producto> productos = productoService.findProducto(filtro);
        if (productos.isEmpty()) 
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(productos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(@PathVariable int id, @RequestBody Producto producto) 
    {
        Producto actualizado = productoService.update(id, producto);
        return ResponseEntity.ok(actualizado);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Producto> save(@RequestBody Producto producto)
    {
        Producto productoGuardado = productoService.save(producto);

        return ResponseEntity.ok(productoGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) 
    {
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
