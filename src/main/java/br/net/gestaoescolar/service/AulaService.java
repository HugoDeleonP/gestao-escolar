package br.net.gestaoescolar.service;

import br.net.gestaoescolar.dto.aula.AulaDTORequisicao;
import br.net.gestaoescolar.dto.aula.AulaDTOResposta;
import br.net.gestaoescolar.mapper.AulaMapper;
import br.net.gestaoescolar.model.Aula;
import br.net.gestaoescolar.model.Turma;
import br.net.gestaoescolar.repository.AulaRepository;
import br.net.gestaoescolar.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AulaService {
    
    private final AulaRepository aulaRepository;
    private final TurmaRepository turmaRepository;
    private final AulaMapper aulaMapper;
    
    public AulaService(AulaRepository aulaRepository, AulaMapper aulaMapper, TurmaRepository turmaRepository){
        this.aulaRepository = aulaRepository;
        this.aulaMapper = aulaMapper;
        this.turmaRepository = turmaRepository;
    }

    public AulaDTOResposta salvaAula(AulaDTORequisicao aulaRequisicao) throws SQLException {

        Aula aula = aulaMapper.paraEntidade(aulaRequisicao);


        return aulaMapper.paraRespostaDto(
                aulaRepository.salvaAula(aula),
                turmaRepository.listaTurmaPorId(aula.getTurma_id())
            );

    }

    public AulaDTOResposta listaAulaPorId(Long id) throws SQLException{

        Aula aula = aulaRepository.listaAulaPorId(id);

        if( aula == null ){
            throw new RuntimeException("aula não encontrada");
        }

        return aulaMapper.paraRespostaDto(
                aula,
                turmaRepository.listaTurmaPorId(aula.getTurma_id())
        );

    }

    public List<AulaDTOResposta> listaAulas() throws SQLException{

        List<Aula> aulas = aulaRepository.listaAulas();

        return aulas.stream()
                .map( aula -> {
                    try {
                        return aulaMapper.paraRespostaDto(
                                aula,
                                turmaRepository.listaTurmaPorId(aula.getTurma_id())
                        );
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }

    public AulaDTOResposta atualizaAula(AulaDTORequisicao aulaRequisicao, Long id) throws SQLException {
        Aula aula = aulaMapper.paraEntidade(aulaRequisicao);
        aula.setId(id);



        return aulaMapper.paraRespostaDto(
                aulaRepository.atualizaAula(aula),
                turmaRepository.listaTurmaPorId(aula.getTurma_id())
        );
    }

    public void deletaAula(Long id) throws SQLException{
        aulaRepository.deletaAula(id);
    }

}
