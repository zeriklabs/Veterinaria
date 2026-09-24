package com.webclient.veterinaria.medicamentos.infrastructure;

import com.webclient.veterinaria.medicamentos.application.MedicamentoService;
import com.webclient.veterinaria.medicamentos.domain.Medicamento;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    // GET - Listar todos los medicamentos
    @GetMapping
    public ResponseEntity<List<Medicamento>> listarTodos() {
        List<Medicamento> medicamentos = medicamentoService.obtenerTodos();
        return new ResponseEntity<>(medicamentos, HttpStatus.OK);
    }

    // GET - Buscar medicamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> buscarPorId(@PathVariable Long id) {
        return medicamentoService.obtenerPorId(id)
                .map(medicamento -> new ResponseEntity<>(medicamento, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST - Agregar nuevo medicamento
    @PostMapping
    public ResponseEntity<Medicamento> agregar(@RequestBody Medicamento medicamento) {
        Medicamento nuevoMedicamento = medicamentoService.guardar(medicamento);
        return new ResponseEntity<>(nuevoMedicamento, HttpStatus.CREATED);
    }

    // PUT - Actualizar medicamento existente
    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> actualizar(@PathVariable Long id, @RequestBody Medicamento medicamento) {
        try {
            Medicamento medicamentoActualizado = medicamentoService.actualizar(id, medicamento);
            return new ResponseEntity<>(medicamentoActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - Eliminar medicamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            medicamentoService.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
