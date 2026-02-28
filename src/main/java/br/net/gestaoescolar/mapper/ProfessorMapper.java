package br.net.gestaoescolar.mapper;

import br.net.gestaoescolar.dto.professor.ProfessorDTORequisicao;
import br.net.gestaoescolar.dto.professor.ProfessorDTOResposta;
import br.net.gestaoescolar.model.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {

    public Professor paraEntidade(ProfessorDTORequisicao professorRequisicao){
        return new Professor(
                professorRequisicao.nome(),
                professorRequisicao.email(),
                professorRequisicao.disciplina()
        );
    }

    public ProfessorDTOResposta paraRespostaDto(
            Professor professor
    ){
        return new ProfessorDTOResposta(
                professor.getId(),
                professor.getNome(),
                professor.getEmail(),
                professor.getDisciplina()
        );
    }
}
