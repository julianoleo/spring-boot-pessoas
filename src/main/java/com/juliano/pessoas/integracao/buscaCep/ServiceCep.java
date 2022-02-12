package com.juliano.pessoas.integracao.buscaCep;

import com.juliano.pessoas.exceptions.NoContentRuntimeException;
import com.juliano.pessoas.utils.ValidaCep;
import com.juliano.pessoas.utils.ValidaDocumento;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceCep {

    @Value("${api_url}")
    private String apiUrl;

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
