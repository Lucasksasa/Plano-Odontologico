package com.DevMaker.Plano_Odondologico.service;

import com.DevMaker.Plano_Odondologico.model.Dentista;
import com.DevMaker.Plano_Odondologico.repository.DentistaRepositorio;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DentistaService {
    private final DentistaRepositorio dentistaRepositorio;

    public List<Dentista> listarDentistas(){
        return dentistaRepositorio.findAll();
    }

    public Optional<Dentista> buscarPorId(Long id){
        return dentistaRepositorio.findById(id);
    }

    @Transactional
    public Dentista salvarDentista(Dentista dentista){
        return dentistaRepositorio.save(dentista);
    }

    @Transactional
    public Dentista atualizar(Long id, Dentista dados){
        Dentista existente = dentistaRepositorio.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Dentista não encontrado com id: " + id));

        existente.setNome(dados.getNome());
        existente.setEspecialidade(dados.getEspecialidade());
        existente.setEmail(dados.getEmail());
        existente.setTelefone(dados.getTelefone());
        existente.setCro(dados.getCro());

        return dentistaRepositorio.save(existente);
    }

    @Transactional
    public void excluirDentista(Long id){
        if (!dentistaRepositorio.existsById(id)){
            throw new EntityNotFoundException("Dentista não encontrado com id: " + id);
        }
        dentistaRepositorio.deleteById(id);
    }
}
