package com.DevMaker.Plano_Odondologico.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AgendarConsultaRequest {

    private Long pacienteId;
    private Long dentistaId;
    private LocalDateTime dataHora;
    private String observacoes;
}

