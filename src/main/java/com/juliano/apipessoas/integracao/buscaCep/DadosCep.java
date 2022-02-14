package com.juliano.apipessoas.integracao.buscaCep;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DadosCep {
    private Integer status;
    private Boolean ok;
    private String code;
    private String state;
    private String city;
    private String district;
    private String address;
    private String statusText;
    private String message;
}
