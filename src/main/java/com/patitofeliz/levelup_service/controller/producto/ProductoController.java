package com.patitofeliz.levelup_service.controller.producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patitofeliz.levelup_service.model.producto.Producto;
import com.patitofeliz.levelup_service.service.producto.ProductoService;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductoController 
{
    @Autowired
    private ProductoService productoService;

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
}
