package com.juliano.apipessoas.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Email {

    @Id
    private String id;
    private String idCliente;
    private String email;
    private String descEmail;

    public Email(String idCliente, String email, String descEmail) {
        this.idCliente = idCliente;
        this.email = email;
        this.descEmail = descEmail;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", descEmail='" + descEmail + '\'' +
                '}';
    }
}
