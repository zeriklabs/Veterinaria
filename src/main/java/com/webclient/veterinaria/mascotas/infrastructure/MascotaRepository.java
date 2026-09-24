package com.webclient.veterinaria.mascotas.infrastructure;

import com.webclient.veterinaria.mascotas.domain.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
}
