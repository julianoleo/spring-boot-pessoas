package com.juliano.pessoas.integracao.apiChaves;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChavesModel {
    private Integer statusCode;
    private String descStatus;

    public ChavesModel() { super(); }

    public ChavesModel(Integer statusCode, String descStatus) {
        this.statusCode = statusCode;
        this.descStatus = descStatus;
    }
}
