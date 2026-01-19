package com.DevMaker.Plano_Odondologico.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AgendarConsultaRequest {

    @NotNull(message = "O id do paciente é obrigatório")
    private Long pacienteId;
    @NotNull(message = "O id do dentista é obrigatório")
    private Long dentistaId;
    @NotNull(message = "A data e hora da consulta são obrigatórias")
    @Future(message = "A consulta deve ser agendada para uma data futura")
    private LocalDateTime dataHora;
    private String observacoes;
}

