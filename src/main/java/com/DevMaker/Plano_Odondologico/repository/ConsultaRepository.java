package com.DevMaker.Plano_Odondologico.repository;

import com.DevMaker.Plano_Odondologico.model.Consulta;
import com.DevMaker.Plano_Odondologico.model.Dentista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByDentistaAndDataHora(Dentista dentista, LocalDateTime dataHora);

    Page<Consulta> findByDentistaId(Long dentistaId, Pageable pageable);

    Page<Consulta> findByPacienteId(Long pacienteId, Pageable pageable);
}
