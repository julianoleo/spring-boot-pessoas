package com.juliano.apipessoas.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.juliano.apipessoas.utils.ValidaDocumento;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Contato {

    @Id
    private String id;
    private String idCliente;
    private String fone;
    private String email;
    private String descContato;

    public Contato() { super(); }

    public Contato(String idCliente, String fone, String email, String descContato) {
        this.idCliente = idCliente;
        this.fone = ValidaDocumento.removeCaracteresEspeciaisFone(fone);
        this.email = email;
        this.descContato = descContato;
    }
}
