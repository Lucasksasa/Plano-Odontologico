package com.DevMaker.Plano_Odondologico.controller;

import com.DevMaker.Plano_Odondologico.dto.AgendarConsultaRequest;
import com.DevMaker.Plano_Odondologico.model.Consulta;
import com.DevMaker.Plano_Odondologico.service.ConsultaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<Consulta>> listar() {
        return ResponseEntity.ok(consultaService.listarConsultas());
    }

    //  Agendar consulta
    @PostMapping
    public ResponseEntity<Consulta> agendar(@Valid @RequestBody AgendarConsultaRequest request) {

        Consulta consulta = consultaService.agendarConsulta(
                request.getPacienteId(),
                request.getDentistaId(),
                request.getDataHora(),
                request.getObservacoes()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(consulta);
    }

    //  Cancelar consulta
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Consulta> cancelar(@PathVariable Long id) {
        Consulta consulta = consultaService.cancelarConsulta(id);
        return ResponseEntity.ok(consulta);
    }

    //  Finalizar consulta
    @PutMapping("/{id}/finalizar")
    public ResponseEntity<Consulta> finalizar(@PathVariable Long id) {
        Consulta consulta = consultaService.finalizarConsulta(id);
        return ResponseEntity.ok(consulta);
    }

    //  Listar consultas por dentista
    @GetMapping("/dentista/{dentistaId}")
    public ResponseEntity<List<Consulta>> listarPorDentista(@PathVariable Long dentistaId) {
        return ResponseEntity.ok(consultaService.listarPorDentista(dentistaId));
    }

    //  Listar consultas por paciente
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Consulta>> listarPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(consultaService.listarPorPaciente(pacienteId));
    }
}

