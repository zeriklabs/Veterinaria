package com.webclient.veterinaria.medicamentos.application;

import com.webclient.veterinaria.medicamentos.domain.Medicamento;
import com.webclient.veterinaria.medicamentos.infrastructure.MedicamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    public MedicamentoService(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    public List<Medicamento> obtenerTodos() {
        return medicamentoRepository.findAll();
    }

    public Optional<Medicamento> obtenerPorId(Long id) {
        return medicamentoRepository.findById(id);
    }

    public Medicamento guardar(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    public Medicamento actualizar(Long id, Medicamento medicamentoActualizado) {
        return medicamentoRepository.findById(id)
                .map(medicamento -> {
                    medicamento.setNombre(medicamentoActualizado.getNombre());
                    medicamento.setDescripcion(medicamentoActualizado.getDescripcion());
                    medicamento.setPrecio(medicamentoActualizado.getPrecio());
                    medicamento.setStock(medicamentoActualizado.getStock());
                    medicamento.setTipo(medicamentoActualizado.getTipo());
                    return medicamentoRepository.save(medicamento);
                })
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado con id: " + id));
    }

    public void eliminar(Long id) {
        if (!medicamentoRepository.existsById(id)) {
            throw new RuntimeException("Medicamento no encontrado con id: " + id);
        }
        medicamentoRepository.deleteById(id);
    }
}
