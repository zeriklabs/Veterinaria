package com.webclient.veterinaria.medicamentos.infrastructure;

import com.webclient.veterinaria.medicamentos.domain.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
}
