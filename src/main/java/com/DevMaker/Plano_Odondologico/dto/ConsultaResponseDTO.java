package com.DevMaker.Plano_Odondologico.dto;

import com.DevMaker.Plano_Odondologico.model.StatusConsulta;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ConsultaResponseDTO {

    private Long id;

    private Long pacienteId;
    private String pacienteNome;

    private Long dentistaId;
    private String dentistaNome;

    private LocalDateTime dataHora;

    private StatusConsulta status;

    private String observacoes;
}

