package com.juliano.apipessoas.integracao.apiChaves;

import com.juliano.apipessoas.exceptions.UnauthorizedException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Service
public class ChavesService {

    private static final String apiUrl = "https://spring-boot-chaves.herokuapp.com/api/v1/chaves/validation";

    public ResponseEntity<ChavesModel> buscaValidation(HttpServletRequest request, HttpHeaders headers) {
        if(request.getHeader(headers.AUTHORIZATION) == null || request.getHeader(headers.AUTHORIZATION).isBlank() || request.getHeader(headers.AUTHORIZATION).isEmpty()) {
            throw new UnauthorizedException("Dados de autenticação não encontrados.");
        } else {
            HttpEntity requestSend = new HttpEntity(headers);
            ResponseEntity<ChavesModel> response = new RestTemplate().exchange(apiUrl, HttpMethod.GET, requestSend, ChavesModel.class);
            return response;
        }
    }
}
