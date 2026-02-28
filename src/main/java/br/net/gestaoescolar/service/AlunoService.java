package br.net.gestaoescolar.service;

import br.net.gestaoescolar.dto.aluno.AlunoDTORequisicao;
import br.net.gestaoescolar.dto.aluno.AlunoDTOResposta;
import br.net.gestaoescolar.mapper.AlunoMapper;
import br.net.gestaoescolar.model.Aluno;
import br.net.gestaoescolar.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;

    public AlunoService(AlunoRepository alunoRepository, AlunoMapper alunoMapper){
        this.alunoRepository = alunoRepository;
        this.alunoMapper = alunoMapper;
    }

    public AlunoDTOResposta salvaAluno(AlunoDTORequisicao alunoRequisicao) throws SQLException {

        Aluno aluno = alunoMapper.paraEntidade(alunoRequisicao);

        return alunoMapper.paraRespostaDto(alunoRepository.salvaAluno(aluno));

    }

    public AlunoDTOResposta listaAlunoPorId(Long id) throws SQLException{

        return alunoMapper.paraRespostaDto(alunoRepository.listaAlunoPorId(id));
    }

    public List<AlunoDTOResposta> listaAlunos() throws SQLException{

        List<Aluno> alunos = alunoRepository.listaAlunos();

        return alunos.stream()
                .map(aluno -> alunoMapper.paraRespostaDto(aluno))
                .collect(Collectors.toList());
    }

    public AlunoDTOResposta atualizaAluno(AlunoDTORequisicao alunoRequisicao, Long id) throws SQLException {

        Aluno aluno = alunoMapper.paraEntidade(alunoRequisicao);
        aluno.setId(id);

        return alunoMapper.paraRespostaDto(alunoRepository.atualizaAluno(aluno));
    }

    public void deletaAluno(Long id) throws SQLException{

        alunoRepository.deletaAluno(id);
    }


}
