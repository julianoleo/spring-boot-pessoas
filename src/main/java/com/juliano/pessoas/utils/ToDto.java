package com.juliano.pessoas.utils;

import com.juliano.pessoas.model.Endereco;
import com.juliano.pessoas.model.FinalPessoaDto;
import com.juliano.pessoas.model.Pessoa;
import com.juliano.pessoas.model.PessoaDto;
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
