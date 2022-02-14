package com.juliano.apipessoas.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoneDto {
    @Id
    private String id;
    private String fone;
    private String descFone;

    public FoneDto(String id, String fone, String descFone) {
        this.id = id;
        this.fone = fone;
        this.descFone = descFone;
    }
}
