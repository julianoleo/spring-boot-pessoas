package com.juliano.apipessoas.utils;

import com.juliano.apipessoas.model.Email;
import com.juliano.apipessoas.model.Endereco;
import com.juliano.apipessoas.model.Fone;
import com.juliano.apipessoas.model.dto.*;
import com.juliano.apipessoas.model.Pessoa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDto {

    public FinalPessoaDto pessoaDto(
            Pessoa pessoa,
            List<Endereco> enderecoList,
            List<Fone> fones,
            List<Email> emails
    ) {

        List<EnderecoDto> _endDto = new ArrayList<EnderecoDto>();

        for(Endereco  end : enderecoList) {
            _endDto.add(convertEndDto(end));
        }

        List<FoneDto> _foneDto = new ArrayList<FoneDto>();

        for(Fone fone : fones) {
            _foneDto.add(convertFoneDto(fone));
        }


        List<EmailDto> _emailDto = new ArrayList<EmailDto>();

        for (Email email: emails) {
            _emailDto.add(convertEmailDto(email));
        }


        ContatosDto _contatoDto = new ContatosDto(
                _foneDto,
                _emailDto
        );

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
                _endDto,
                _contatoDto
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

    private FoneDto convertFoneDto(Fone fone) {
        return new FoneDto(
                fone.getId(),
                fone.getFone(),
                fone.getDescFone()
        );
    }

    private EmailDto convertEmailDto(Email email) {
        return new EmailDto(
                email.getId(),
                email.getEmail(),
                email.getDescEmail()
        );
    }
}
