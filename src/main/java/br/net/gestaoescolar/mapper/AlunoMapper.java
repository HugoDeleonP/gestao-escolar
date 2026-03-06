package br.net.gestaoescolar.mapper;

import br.net.gestaoescolar.dto.aluno.AlunoDTORequisicao;
import br.net.gestaoescolar.dto.aluno.AlunoDTOResposta;
import br.net.gestaoescolar.model.Aluno;
import br.net.gestaoescolar.service.AlunoService;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    public Aluno paraEntidade(
            AlunoDTORequisicao alunoRequisicao
    ){
        return new Aluno(
                alunoRequisicao.nome(),
                alunoRequisicao.email(),
                alunoRequisicao.matricula(),
                alunoRequisicao.data_nascimento()
        );
    }

    public AlunoDTOResposta paraRespostaDto(
            Aluno aluno
    ){
        return new AlunoDTOResposta(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getMatricula(),
                aluno.getData_nascimento()
        );
    }
}
