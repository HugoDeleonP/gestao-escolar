package br.net.gestaoescolar.mapper;

import br.net.gestaoescolar.dto.turma.TurmaDTORequisicao;
import br.net.gestaoescolar.dto.turma.TurmaDTOResposta;
import br.net.gestaoescolar.model.Curso;
import br.net.gestaoescolar.model.Professor;
import br.net.gestaoescolar.model.Turma;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TurmaMapper {

    public Turma paraEntidade(
            TurmaDTORequisicao turmaRequisicao
    ){
        return new Turma(
                turmaRequisicao.nome(),
                turmaRequisicao.curso_id(),
                turmaRequisicao.professor_id()
        );
    }

    public TurmaDTOResposta paraRespostaDto(
            Turma turma, Curso curso, Professor professor, List<String> alunoNomes
    ){
        return new TurmaDTOResposta(
                turma.getId(),
                turma.getNome(),
                curso.getNome(),
                professor.getNome(),
                alunoNomes
        );
    }
}
