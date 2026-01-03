package com.DevMaker.Plano_Odondologico.service;

import com.DevMaker.Plano_Odondologico.model.Dentista;
import com.DevMaker.Plano_Odondologico.repository.DentistaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DentistaService {
    private final DentistaRepository dentistaRepository;

    public List<Dentista> listarDentistas(){
        return dentistaRepository.findAll();
    }

    public Optional<Dentista> buscarPorId(Long id){
        return dentistaRepository.findById(id);
    }

    @Transactional
    public Dentista salvarDentista(Dentista dentista){
        return dentistaRepository.save(dentista);
    }

    @Transactional
    public Dentista atualizar(Long id, Dentista dentistaNovo){
        Dentista dentistaExistente = dentistaRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Dentista não encontrado com id: " + id));

        dentistaExistente.setNome(dentistaNovo.getNome());
        dentistaExistente.setEspecialidade(dentistaNovo.getEspecialidade());
        dentistaExistente.setEmail(dentistaNovo.getEmail());
        dentistaExistente.setTelefone(dentistaNovo.getTelefone());
        dentistaExistente.setCro(dentistaNovo.getCro());

        return dentistaRepository.save(dentistaExistente);
    }

    @Transactional
    public void excluirDentista(Long id){
        if (!dentistaRepository.existsById(id)){
            throw new EntityNotFoundException("Dentista não encontrado com id: " + id);
        }
        dentistaRepository.deleteById(id);
    }
}
