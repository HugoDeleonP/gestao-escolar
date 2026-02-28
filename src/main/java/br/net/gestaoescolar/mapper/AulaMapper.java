package br.net.gestaoescolar.mapper;

import br.net.gestaoescolar.dto.aula.AulaDTORequisicao;
import br.net.gestaoescolar.dto.aula.AulaDTOResposta;
import br.net.gestaoescolar.model.Aula;
import br.net.gestaoescolar.model.Turma;
import org.springframework.stereotype.Component;

@Component
public class AulaMapper {

    public Aula paraEntidade(
            AulaDTORequisicao aulaRequisicao
    ){
        return new Aula(
                aulaRequisicao.turma_id(),
                aulaRequisicao.data_hora(),
                aulaRequisicao.assunto()
        );
    }

    public AulaDTOResposta paraRespostaDto(
            Aula aula,  Turma turma
    ){
        return new AulaDTOResposta(
                aula.getId(),
                turma.getNome(),
                aula.getData_hora(),
                aula.getAssunto()
        );
    }
}
