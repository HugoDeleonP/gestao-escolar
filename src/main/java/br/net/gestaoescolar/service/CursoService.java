package br.net.gestaoescolar.service;

import br.net.gestaoescolar.dto.curso.CursoDTORequisicao;
import br.net.gestaoescolar.dto.curso.CursoDTOResposta;
import br.net.gestaoescolar.mapper.CursoMapper;
import br.net.gestaoescolar.model.Curso;
import br.net.gestaoescolar.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CursoService {

    private final CursoMapper cursoMapper;
    private final CursoRepository cursoRepository;


    public CursoService(CursoMapper cursoMapper, CursoRepository cursoRepository){
        this.cursoMapper = cursoMapper;
        this.cursoRepository = cursoRepository;
    }


    public CursoDTOResposta salvaCurso(CursoDTORequisicao cursoRequisicao) throws SQLException {

        Curso curso = cursoMapper.paraEntidade(cursoRequisicao);

        Curso cursoDatabase = cursoRepository.salvaCurso(curso);

        List<String> nomeProfessores = cursoRepository.listaNomeProfessoresPorCurso(cursoDatabase.getId());

        return cursoMapper.paraRespostaDto(cursoDatabase, nomeProfessores);

    }

    public CursoDTOResposta listaCursoPorId(Long id) throws SQLException{

        Curso curso = cursoRepository.listaCursoPorId(id);

        if( curso == null ){
            throw new RuntimeException("Curso não encontrado");
        }

        List<String> nomeProfessores = cursoRepository.listaNomeProfessoresPorCurso(curso.getId());


        return cursoMapper.paraRespostaDto(curso, nomeProfessores);
    }


    public List<CursoDTOResposta> listaCursos() throws SQLException{

        List<Curso> cursos = cursoRepository.listaCursos();


        return cursos.stream()
                .map(curso -> {
                    try {
                        return cursoMapper.paraRespostaDto(curso, cursoRepository.listaNomeProfessoresPorCurso(curso.getId()));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }

    public CursoDTOResposta atualizaCurso(CursoDTORequisicao cursoRequisicao, Long id) throws SQLException {

        Curso curso = cursoMapper.paraEntidade(cursoRequisicao);
        curso.setId(id);

        List<String> nomeProfessores = cursoRepository.listaNomeProfessoresPorCurso(id);

        return cursoMapper.paraRespostaDto(cursoRepository.atualizaCurso(curso), nomeProfessores);
    }

    public void deletaCurso(Long id) throws SQLException{

        cursoRepository.deletaCurso(id);
    }

}
