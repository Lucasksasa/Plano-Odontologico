package com.DevMaker.Plano_Odondologico.controller;

import com.DevMaker.Plano_Odondologico.dto.AgendarConsultaRequest;
import com.DevMaker.Plano_Odondologico.dto.ConsultaResponseDTO;
import com.DevMaker.Plano_Odondologico.model.Consulta;
import com.DevMaker.Plano_Odondologico.service.ConsultaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    //  Listar todas as consultas
    @GetMapping
    public ResponseEntity<Page<ConsultaResponseDTO>> listar(Pageable pageable) {
        return ResponseEntity.ok(
                consultaService.listarConsultas(pageable)
        );
    }

    //  Agendar consulta
    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> agendar(
            @RequestBody @Valid AgendarConsultaRequest dto) {

        ConsultaResponseDTO resposta = consultaService.agendarConsulta(
                dto.getPacienteId(),
                dto.getDentistaId(),
                dto.getDataHora(),
                dto.getObservacoes()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }


    //  Cancelar consulta
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<ConsultaResponseDTO> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.cancelarConsulta(id));
    }

    //  Finalizar consulta
    @PutMapping("/{id}/finalizar")
    public ResponseEntity<ConsultaResponseDTO> finalizar(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.finalizarConsulta(id));
    }

    //  Listar consultas por dentista
    @GetMapping("/dentista/{dentistaId}")
    public ResponseEntity<Page<ConsultaResponseDTO>> listarPorDentista(
            @PathVariable Long dentistaId,
            Pageable pageable) {

        return ResponseEntity.ok(
                consultaService.listarPorDentista(dentistaId, pageable)
        );
    }

    //  Listar consultas por paciente
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<Page<ConsultaResponseDTO>> listarPorPaciente(
            @PathVariable Long pacienteId,
            Pageable pageable) {

        return ResponseEntity.ok(
                consultaService.listarPorPaciente(pacienteId, pageable)
        );
    }

}

