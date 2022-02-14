package com.juliano.apipessoas.utils;

import com.juliano.apipessoas.model.Endereco;
import com.juliano.apipessoas.model.dto.EnderecoDto;
import com.juliano.apipessoas.model.dto.FinalPessoaDto;
import com.juliano.apipessoas.model.Pessoa;
import com.juliano.apipessoas.model.dto.PessoaDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDto {

    public FinalPessoaDto pessoaDto(Pessoa pessoa, List<Endereco> enderecoList) {

        List<EnderecoDto> _endDto = new ArrayList<EnderecoDto>();

        for(Endereco  end : enderecoList) {
            _endDto.add(convertEndDto(end));
        }

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
                _endDto
        );

        return new FinalPessoaDto(
            _pessoaDto
        );
    }

    private EnderecoDto convertEndDto(Endereco endereco) {
        return new EnderecoDto(
                endereco.getId(),
                endereco.getDataCriacao(),
                endereco.getEndereco(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getPais(),
                endereco.getUf(),
                endereco.getCidade(),
                endereco.getCep()
        );
    }
}
