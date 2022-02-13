package com.juliano.pessoas.utils;

import com.juliano.pessoas.exceptions.NotFoundException;
import com.juliano.pessoas.model.Pessoa;
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
        else if(pessoa.getSexo().toString().equalsIgnoreCase("F") || pessoa.getSexo().toString().equalsIgnoreCase("M")) {
            throw new NotFoundException("Campo sexo incorreto, setar F para feminino ou M para masculino.");
        }
        else { }
    }
}