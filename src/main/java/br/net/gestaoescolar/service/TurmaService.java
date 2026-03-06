package br.net.gestaoescolar.service;

import br.net.gestaoescolar.dto.turma.TurmaDTORequisicao;
import br.net.gestaoescolar.dto.turma.TurmaDTOResposta;
import br.net.gestaoescolar.mapper.TurmaMapper;
import br.net.gestaoescolar.model.Turma;
import br.net.gestaoescolar.repository.AlunoRepository;
import br.net.gestaoescolar.repository.CursoRepository;
import br.net.gestaoescolar.repository.ProfessorRepository;
import br.net.gestaoescolar.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final CursoRepository cursoRepository;
    private final ProfessorRepository professorRepository;
    private final AlunoRepository alunoRepository;
    private final TurmaMapper turmaMapper;

    public TurmaService(TurmaRepository turmaRepository, TurmaMapper turmaMapper,
                        CursoRepository cursoRepository, ProfessorRepository professorRepository,
                        AlunoRepository alunoRepository
    ){
        this.turmaRepository = turmaRepository;
        this.turmaMapper = turmaMapper;
        this.cursoRepository = cursoRepository;
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
    }

    public TurmaDTOResposta salvaTurma(TurmaDTORequisicao turmaRequisicao) throws SQLException {

        Turma turma = turmaMapper.paraEntidade(turmaRequisicao);

        return turmaMapper.paraRespostaDto(
                turmaRepository.salvaTurma(turma),
                cursoRepository.listaCursoPorId(turma.getCurso_id()),
                professorRepository.listaProfessorPorId(turma.getProfessor_id()),
                turmaRepository.listaNomeAlunos(turma.getId())
        );

    }

    public TurmaDTOResposta listaTurmaPorId(Long id) throws SQLException{

        Turma turma = turmaRepository.listaTurmaPorId(id);

        if( turma == null ){
            throw new RuntimeException("Turma não encontrada");
        }


        return turmaMapper.paraRespostaDto(
                turma,
                cursoRepository.listaCursoPorId(turma.getCurso_id()),
                professorRepository.listaProfessorPorId(turma.getProfessor_id()),
                turmaRepository.listaNomeAlunos(turma.getId())
        );

    }

    public List<TurmaDTOResposta> listaTurmas() throws SQLException{

        List<Turma> turmas = turmaRepository.listaTurmas();

        return turmas.stream()
                .map(turma ->
                {
                    try {
                        return turmaMapper.paraRespostaDto(
                        turma,
                        cursoRepository.listaCursoPorId(turma.getCurso_id()),
                        professorRepository.listaProfessorPorId(turma.getProfessor_id()),
                        turmaRepository.listaNomeAlunos(turma.getId())
                        );
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();

    }

    public TurmaDTOResposta atualizaTurma(TurmaDTORequisicao turmaRequisicao, Long id) throws SQLException {
        Turma turma = turmaMapper.paraEntidade(turmaRequisicao);
        turma.setId(id);

        return turmaMapper.paraRespostaDto(
                turmaRepository.atualizaTurma(turma),
                cursoRepository.listaCursoPorId(turma.getId()),
                professorRepository.listaProfessorPorId(turma.getProfessor_id()),
                turmaRepository.listaNomeAlunos(turma.getId())
        );
    }

    public void deletaTurma(Long id) throws SQLException{
        turmaRepository.deletaTurma(id);
    }

}
