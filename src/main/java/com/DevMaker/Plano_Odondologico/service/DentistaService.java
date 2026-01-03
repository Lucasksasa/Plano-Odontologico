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
    public Dentista atualizar(Long id, Dentista dados){
        Dentista existente = dentistaRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Dentista não encontrado com id: " + id));

        existente.setNome(dados.getNome());
        existente.setEspecialidade(dados.getEspecialidade());
        existente.setEmail(dados.getEmail());
        existente.setTelefone(dados.getTelefone());
        existente.setCro(dados.getCro());

        return dentistaRepository.save(existente);
    }

    @Transactional
    public void excluirDentista(Long id){
        if (!dentistaRepository.existsById(id)){
            throw new EntityNotFoundException("Dentista não encontrado com id: " + id);
        }
        dentistaRepository.deleteById(id);
    }
}
