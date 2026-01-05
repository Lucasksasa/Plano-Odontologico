package com.DevMaker.Plano_Odondologico.service;

import com.DevMaker.Plano_Odondologico.model.*;
import com.DevMaker.Plano_Odondologico.repository.ConsultaRepository;
import com.DevMaker.Plano_Odondologico.repository.DentistaRepository;
import com.DevMaker.Plano_Odondologico.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final DentistaRepository dentistaRepository;

    public List<Consulta> listarConsultas() {
        return consultaRepository.findAll();
    }

    @Transactional
    public Consulta agendarConsulta(Long pacienteId,
                                    Long dentistaId,
                                    LocalDateTime dataHora,
                                    String observacoes) {

        if (dataHora.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Não é permitido agendar consulta no passado");
        }

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        Dentista dentista = dentistaRepository.findById(dentistaId)
                .orElseThrow(() -> new EntityNotFoundException("Dentista não encontrado"));

        boolean existeConflito = consultaRepository
                .existsByDentistaAndDataHora(dentista, dataHora);

        if (existeConflito) {
            throw new IllegalStateException("Dentista já possui consulta nesse horário");
        }

        Consulta consulta = new Consulta();
        consulta.setPaciente(paciente);
        consulta.setDentista(dentista);
        consulta.setDataHora(dataHora);
        consulta.setStatus(StatusConsulta.AGENDADA);
        consulta.setObservacoes(observacoes);

        return consultaRepository.save(consulta);
    }

    @Transactional
    public Consulta cancelarConsulta(Long consultaId) {
        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada"));

        consulta.setStatus(StatusConsulta.CANCELADA);
        return consulta;
    }

    @Transactional
    public Consulta finalizarConsulta(Long consultaId) {
        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada"));

        consulta.setStatus(StatusConsulta.FINALIZADA);
        return consulta;
    }

    public List<Consulta> listarPorDentista(Long dentistaId) {
        return consultaRepository.findByDentistaId(dentistaId);
    }

    public List<Consulta> listarPorPaciente(Long pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId);
    }
}
