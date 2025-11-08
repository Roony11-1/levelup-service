package com.patitofeliz.levelup_service.controller.fireemblem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patitofeliz.levelup_service.model.Response;
import com.patitofeliz.levelup_service.model.fireemblem.Unidad;
import com.patitofeliz.levelup_service.service.fireemblem.UnidadService;

@RestController
@RequestMapping("/api/unidades")
@CrossOrigin(origins = "http://localhost:5173")
public class UnidadController 
{
    @Autowired
    private UnidadService unidadService;

    @GetMapping
    public ResponseEntity<List<Unidad>> findAll()
    {
        List<Unidad> unidades = this.unidadService.findAll();

        if (unidades.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(unidades);
    }

    @GetMapping("/propietario/not/{idPropietario}")
    public ResponseEntity<List<Unidad>> findAllByIdPropietarioNot(@PathVariable("idPropietario") int idPropietario)
    {
        List<Unidad> unidades = this.unidadService.findAllByIdPropietarioNot(idPropietario);

        if (unidades.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(unidades);
    }

    @GetMapping("/propietario/{idPropietario}")
    public ResponseEntity<List<Unidad>> findAllByIdPropietario(@PathVariable("idPropietario") int idPropietario)
    {
        List<Unidad> unidades = this.unidadService.findAllByIdPropietario(idPropietario);

        if (unidades.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(unidades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unidad> findById(@PathVariable("id") int id)
    {
        Unidad unidad = this.unidadService.findById(id);

        if (unidad == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(unidad);
    }

    @PostMapping
    public ResponseEntity<Response<Unidad>> save(@RequestBody Unidad unidad) 
    {
        Response<Unidad> response = this.unidadService.save(unidad);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<Response<Unidad>> update(@RequestBody Unidad unidad) 
    {
        Response<Unidad> response = this.unidadService.update(unidad);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
