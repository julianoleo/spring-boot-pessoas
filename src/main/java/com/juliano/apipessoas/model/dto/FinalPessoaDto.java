package com.juliano.apipessoas.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FinalPessoaDto {

    private PessoaDto Pessoa;

    public FinalPessoaDto() {super(); }

    public FinalPessoaDto(PessoaDto pessoa) {
        Pessoa = pessoa;
    }
}
