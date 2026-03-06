package br.net.gestaoescolar.controller;

import br.net.gestaoescolar.dto.aula.AulaDTORequisicao;
import br.net.gestaoescolar.dto.aula.AulaDTOResposta;
import br.net.gestaoescolar.dto.turma.TurmaDTORequisicao;
import br.net.gestaoescolar.dto.turma.TurmaDTOResposta;
import br.net.gestaoescolar.service.AulaService;
import br.net.gestaoescolar.service.TurmaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/aulas")
public class AulaController {

    private final AulaService aulaService;

    public AulaController(AulaService aulaService){
        this.aulaService = aulaService;
    }

    @GetMapping
    public List<AulaDTOResposta> listaAulas(){
        try{
            return aulaService.listaAulas();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public AulaDTOResposta listaAulaPorId(@PathVariable Long id){
        try{
            return aulaService.listaAulaPorId(id);
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public AulaDTOResposta salvaAula(@RequestBody AulaDTORequisicao aulaRequisicao){
        try{
            return aulaService.salvaAula(aulaRequisicao);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public AulaDTOResposta atualizaAula(@PathVariable Long id ,@RequestBody AulaDTORequisicao aulaRequisicao){
        try{
            return aulaService.atualizaAula(aulaRequisicao, id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaAula(@PathVariable Long id){
        try{
            aulaService.deletaAula(id);
            return new ResponseEntity<>("Deletado com sucesso!", HttpStatus.OK);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
