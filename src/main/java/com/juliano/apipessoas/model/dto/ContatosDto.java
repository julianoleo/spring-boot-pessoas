package com.juliano.apipessoas.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContatosDto {

    private List<FoneDto> telefone;

    public ContatosDto(List<FoneDto> telefone) {
        this.telefone = telefone;
    }
}
