package com.juliano.apipessoas.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.juliano.apipessoas.utils.ValidaDocumento;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Fone {

    @Id
    private String id;
    private String idCliente;
    private String fone;
    private String descFone;

    public Fone(String idCliente, String fone, String descFone) {
        this.idCliente = idCliente;
        this.fone = ValidaDocumento.removeCaracteresEspeciaisFone(fone);
        this.descFone = descFone;
    }

    @Override
    public String toString() {
        return "Fone{" +
                "id='" + id + '\'' +
                ", fone='" + fone + '\'' +
                ", descFone='" + descFone + '\'' +
                '}';
    }
}
