package com.juliano.apipessoas.utils;

import com.juliano.apipessoas.exceptions.NotFoundException;
import com.juliano.apipessoas.model.Pessoa;
import org.springframework.stereotype.Service;

@Service
public class ValidaPessoa {

    public void checaPessoa(Pessoa pessoa) {
        if(pessoa.getDocumento() == null || pessoa.getDocumento().isEmpty() || pessoa.getDocumento().isBlank()) {
            throw new NotFoundException("Campo documento vazio ou inexistente.");
        }
        else if(pessoa.getNome() == null || pessoa.getNome().isEmpty() || pessoa.getNome().isBlank()) {
            throw new NotFoundException("Campo nome vazio ou inexistente.");
        }
        else if(pessoa.getSexo() == null | pessoa.getSexo().isEmpty() || pessoa.getSexo().isBlank()) {
            throw new NotFoundException("Campo sexo vazio ou inexistente.");
        }
        else if(!verificaSexo(pessoa.getSexo())) {
            throw new NotFoundException("Campo sexo incorreto, setar F para feminino ou M para masculino.");
        }
        else { }
    }

    public Boolean verificaSexo(String sexo) {
        if(sexo.length() == 1) {
            if(sexo.toLowerCase().contains("m")){
                return true;
            }
            else if(sexo.toLowerCase().contains("f")) {
                return true;
            }
            else{
                return false;
            }
        } else {
            return false;
        }
    }
}
