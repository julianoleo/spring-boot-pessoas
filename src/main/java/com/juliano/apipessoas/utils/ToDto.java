package com.juliano.apipessoas.utils;

import com.juliano.apipessoas.model.Endereco;
import com.juliano.apipessoas.model.FinalPessoaDto;
import com.juliano.apipessoas.model.Pessoa;
import com.juliano.apipessoas.model.PessoaDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDto {

    public FinalPessoaDto pessoaDto(Pessoa pessoa, List<Endereco> enderecoList) {
        var _pessoaDto =  new PessoaDto(
                pessoa.getId(),
                pessoa.getDataCriacao(),
                pessoa.getTipoDoc(),
                pessoa.getDocumento(),
                pessoa.getNome(),
                pessoa.getRg(),
                pessoa.getSexo(),
                pessoa.getNomePai(),
                pessoa.getNomeMae(),
                pessoa.getEstCivil(),
                enderecoList
        );

        return new FinalPessoaDto(
            _pessoaDto
        );
    }
}
