package br.net.gestaoescolar.service;

import br.net.gestaoescolar.dto.professor.ProfessorDTORequisicao;
import br.net.gestaoescolar.dto.professor.ProfessorDTOResposta;
import br.net.gestaoescolar.mapper.ProfessorMapper;
import br.net.gestaoescolar.model.Professor;
import br.net.gestaoescolar.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    public ProfessorService(ProfessorRepository professorRepository, ProfessorMapper professorMapper){
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    public ProfessorDTOResposta salvaProfessor(ProfessorDTORequisicao professorRequisicao) throws SQLException {

        Professor professor = professorMapper.paraEntidade(professorRequisicao);

        return professorMapper.paraRespostaDto(professorRepository.salvaProfessor(professor));
    }

    public ProfessorDTOResposta listaProfessorPorId(Long id) throws SQLException{

        return professorMapper.paraRespostaDto(professorRepository.listaProfessorPorId(id));
    }

    public List<ProfessorDTOResposta> listaProfessores() throws SQLException{

        List<Professor> professores = professorRepository.listaProfessores();

        return professores.stream()
                .map(professor -> professorMapper.paraRespostaDto(professor))
                .collect(Collectors.toList());
    }

    public ProfessorDTOResposta atualizaProfessor(ProfessorDTORequisicao professorRequisicao, Long id) throws SQLException {
        Professor professor = professorMapper.paraEntidade(professorRequisicao);
        professor.setId(id);

        return professorMapper.paraRespostaDto(professorRepository.atualizaProfessor(professor));
    }

    public void deletaProfessor(Long id) throws SQLException{

        professorRepository.deletaProfessor(id);

    }


}