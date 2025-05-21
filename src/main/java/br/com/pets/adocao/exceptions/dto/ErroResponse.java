package br.com.pets.adocao.exceptions.dto;

import java.time.LocalDateTime;

public record ErroResponse(String titulo, String mensagem, LocalDateTime timeStamp) {
}
