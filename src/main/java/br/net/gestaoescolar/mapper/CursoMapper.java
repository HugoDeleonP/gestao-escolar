package br.net.gestaoescolar.mapper;

import br.net.gestaoescolar.dto.curso.CursoDTORequisicao;
import br.net.gestaoescolar.dto.curso.CursoDTOResposta;
import br.net.gestaoescolar.model.Curso;
import br.net.gestaoescolar.model.Professor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CursoMapper {

    public Curso paraEntidade(
            CursoDTORequisicao cursoRequisicao
    ){
        return new Curso(
                cursoRequisicao.nome(),
                cursoRequisicao.codigo()
        );
    }

    public CursoDTOResposta paraRespostaDto(
            Curso curso, List<String> professores
    ){
        return new CursoDTOResposta(
                curso.getId(),
                curso.getNome(),
                curso.getCodigo(),
                professores
        );
    }
}
