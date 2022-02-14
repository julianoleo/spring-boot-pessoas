package com.juliano.apipessoas.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnderecoDto {

    private String id;
    private Date dataCriacao;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String pais;
    private String uf;
    private String cidade;
    private String cep;

    public EnderecoDto(String id, Date dataCriacao, String endereco, String numero, String complemento, String bairro, String pais, String uf, String cidade, String cep) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.pais = pais;
        this.uf = uf;
        this.cidade = cidade;
        this.cep = cep;
    }
}
