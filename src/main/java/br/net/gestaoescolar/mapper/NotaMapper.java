package br.net.gestaoescolar.mapper;

import br.net.gestaoescolar.dto.nota.NotaDTORequisicao;
import br.net.gestaoescolar.dto.nota.NotaDTOResposta;
import br.net.gestaoescolar.model.Aluno;
import br.net.gestaoescolar.model.Aula;
import br.net.gestaoescolar.model.Nota;
import org.springframework.stereotype.Component;

@Component
public class NotaMapper {

    public Nota paraEntidade(
            NotaDTORequisicao notaRequisicao
    ){
        return new Nota(
                notaRequisicao.aluno_id(),
                notaRequisicao.aula_id(),
                notaRequisicao.valor()
        );
    }

    public NotaDTOResposta paraRespostaDto(
            Nota nota, Aluno aluno, Aula aula
    ){
        return new NotaDTOResposta(
                nota.getId(),
                aluno.getNome(),
                aula.getAssunto(),
                nota.getValor()
        );
    }
}
