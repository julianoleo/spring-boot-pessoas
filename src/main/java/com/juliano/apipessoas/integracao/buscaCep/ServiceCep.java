package com.juliano.apipessoas.integracao.buscaCep;

import com.juliano.apipessoas.exceptions.NoContentRuntimeException;
import com.juliano.apipessoas.utils.ValidaCep;
import com.juliano.apipessoas.utils.ValidaDocumento;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceCep {

    private static final String apiUrl = "https://ws.apicep.com/cep/";

    public ResponseEntity<DadosCep> buscaCep(String cep) {
        if(ValidaCep.verificaCep(cep)) {
            var _cep = ValidaCep.formataCep(ValidaDocumento.removeCaracteresEspeciais(cep));
            RestTemplate restTemplate = new RestTemplate();
            var _dadosCep = restTemplate.getForEntity(apiUrl + _cep + ".json", DadosCep.class );
            return _dadosCep;
        }
        else {
            throw new NoContentRuntimeException("CEP Inválido.");
        }
    }
}
