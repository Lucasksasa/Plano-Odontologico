package com.DevMaker.Plano_Odondologico.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ApiError {

  private int status;
  private String mensagem;
  private LocalDateTime timestamp;
  private Map<String, String> erros;
}
