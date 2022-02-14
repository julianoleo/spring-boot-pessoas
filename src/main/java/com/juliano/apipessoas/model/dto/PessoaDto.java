package com.juliano.apipessoas.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PessoaDto<T> {

    private String id;
    private Date dataCriacao;
    private String tipoDoc;
    private String documento;
    private String nome;
    private String rg;
    private String sexo;
    private String nomePai;
    private String nomeMae;
    private String estCivil;

    private List<T> endereco;
    private List<T> telefone;


    public PessoaDto() { super(); }

    public PessoaDto(String id, Date dataCriacao, String tipoDoc, String documento, String nome, String rg, String sexo, String nomePai, String nomeMae, String estCivil, List<T> endereco, List<T> telefone) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.tipoDoc = tipoDoc;
        this.documento = documento;
        this.nome = nome;
        this.rg = rg;
        this.sexo = sexo;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
        this.estCivil = estCivil;
        this.endereco = endereco;
        this.telefone = telefone;
    }
}
