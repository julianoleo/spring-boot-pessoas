package com.juliano.apipessoas.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContatosDto {

    private List<FoneDto> telefone;
    private List<EmailDto> email;

    public ContatosDto(List<FoneDto> telefone, List<EmailDto> email) {
        this.telefone = telefone;
        this.email = email;
    }
}
