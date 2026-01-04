package com.DevMaker.Plano_Odondologico.repository;

import com.DevMaker.Plano_Odondologico.model.Consulta;
import com.DevMaker.Plano_Odondologico.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByDentistaAndDataHora(Dentista dentista, LocalDateTime dataHora);

    List<Consulta> findByDentistaId(Long dentistaId);

    List<Consulta> findByPacienteId(Long pacienteId);
}
