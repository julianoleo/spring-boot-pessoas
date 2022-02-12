package com.juliano.pessoas.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Endereco {

    @Id
    private String id;
    private Date dataCriacao;
    private String idPessoa;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String pais;
    private String uf;
    private String cidade;
    private String cep;

    public Endereco(Date dataCriacao, String idPessoa, String endereco, String numero, String complemento, String bairro, String pais, String uf, String cidade, String cep) {
        this.dataCriacao = new Date();
        this.idPessoa = idPessoa;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.pais = buscaPais(pais);
        this.uf = uf;
        this.cidade = cidade;
        this.cep = cep;
    }

    private String buscaPais(String pais) {
        if(pais == null || pais.isBlank() || pais.isEmpty()) {
            return "Brasil";
        }
        else {
            return pais;
        }
    }
}
