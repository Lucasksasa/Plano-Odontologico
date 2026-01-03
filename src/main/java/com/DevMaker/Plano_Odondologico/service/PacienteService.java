package com.DevMaker.Plano_Odondologico.service;

import com.DevMaker.Plano_Odondologico.model.Paciente;
import com.DevMaker.Plano_Odondologico.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    public List<Paciente> listarPaciente(){
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPacientePorId(Long id){
        return pacienteRepository.findById(id);
    }

    @Transactional
    public Paciente salvarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    @Transactional
    public Paciente atualizarPaciente(Long id, Paciente pacienteNovo){
        Paciente pacienteExistente = pacienteRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Paciente não encontrado com id: " + id));

        pacienteExistente.setNome(pacienteNovo.getNome());
        pacienteExistente.setTelefone(pacienteNovo.getTelefone());
        pacienteExistente.setEmail(pacienteNovo.getEmail());
        pacienteExistente.setCpf(pacienteNovo.getCpf());
        pacienteExistente.setDataNascimento(pacienteNovo.getDataNascimento());

        return pacienteRepository.save(pacienteExistente);
    }

    @Transactional
    public void excluirPaciente(Long id){
        if (!pacienteRepository.existsById(id)){
            throw new EntityNotFoundException("Paciente não encontrado com id: " + id);
        }
        pacienteRepository.deleteById(id);
    }
}
