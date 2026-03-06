package br.net.gestaoescolar.service;

import br.net.gestaoescolar.dto.nota.NotaDTORequisicao;
import br.net.gestaoescolar.dto.nota.NotaDTOResposta;
import br.net.gestaoescolar.dto.turma.TurmaDTORequisicao;
import br.net.gestaoescolar.dto.turma.TurmaDTOResposta;
import br.net.gestaoescolar.mapper.NotaMapper;
import br.net.gestaoescolar.model.Nota;
import br.net.gestaoescolar.model.Turma;
import br.net.gestaoescolar.repository.AlunoRepository;
import br.net.gestaoescolar.repository.AulaRepository;
import br.net.gestaoescolar.repository.NotaRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class NotaService {

    private final NotaMapper notaMapper;
    private final NotaRepository notaRepository;
    private final AlunoRepository alunoRepository;
    private final AulaRepository aulaRepository;

    public NotaService(
            NotaMapper notaMapper, NotaRepository notaRepository,
            AlunoRepository alunoRepository, AulaRepository aulaRepository){
        this.notaMapper = notaMapper;
        this.notaRepository = notaRepository;
        this.alunoRepository = alunoRepository;
        this.aulaRepository = aulaRepository;
    }

    public NotaDTOResposta salvaNota(NotaDTORequisicao notaRequisicao) throws SQLException {

        Nota nota = notaMapper.paraEntidade(notaRequisicao);


        return notaMapper.paraRespostaDto(
            notaRepository.salvaNota(nota),
            alunoRepository.listaAlunoPorId(nota.getAluno_id()),
            aulaRepository.listaAulaPorId(nota.getAula_id())
        );
    }

    public NotaDTOResposta listaNotaPorId(Long id) throws SQLException{

        Nota nota = notaRepository.listaNotaPorId(id);

        if( nota == null ){
            throw new RuntimeException("Nota não encontrada");
        }

        return notaMapper.paraRespostaDto(
                nota,
                alunoRepository.listaAlunoPorId(nota.getAluno_id()),
                aulaRepository.listaAulaPorId(nota.getAula_id())
        );


    }

    public List<NotaDTOResposta> listaNotas() throws SQLException{

        List<Nota> notas = notaRepository.listaNotas();

        return notas.stream()
                .map( nota -> {
                    try {
                        return notaMapper.paraRespostaDto(
                                nota,
                                alunoRepository.listaAlunoPorId(nota.getAluno_id()),
                                aulaRepository.listaAulaPorId(nota.getAula_id())
                        );
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
    }

    public NotaDTOResposta atualizaNota(NotaDTORequisicao notaRequisicao, Long id) throws SQLException {
        Nota nota = notaMapper.paraEntidade(notaRequisicao);
        nota.setId(id);

        return notaMapper.paraRespostaDto(
                notaRepository.atualizaNota(nota),
                alunoRepository.listaAlunoPorId(nota.getAluno_id()),
                aulaRepository.listaAulaPorId(nota.getAula_id())
        );
    }

    public void deletaNota(Long id) throws SQLException{
        notaRepository.deletaNota(id);
    }

}
