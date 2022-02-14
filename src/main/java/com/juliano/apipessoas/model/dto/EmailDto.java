package com.juliano.apipessoas.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailDto {
    @Id
    private String id;
    private String email;
    private String descFone;

    public EmailDto(String id, String email, String descFone) {
        this.id = id;
        this.email = email;
        this.descFone = descFone;
    }
}
