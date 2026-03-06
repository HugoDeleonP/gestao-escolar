package br.net.gestaoescolar.controller;

import br.net.gestaoescolar.dto.curso.CursoDTORequisicao;
import br.net.gestaoescolar.dto.curso.CursoDTOResposta;
import br.net.gestaoescolar.dto.turma.TurmaDTORequisicao;
import br.net.gestaoescolar.dto.turma.TurmaDTOResposta;
import br.net.gestaoescolar.model.Turma;
import br.net.gestaoescolar.service.TurmaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService){
        this.turmaService = turmaService;
    }

    @GetMapping
    public List<TurmaDTOResposta> listaTurmas(){
        try{
            return turmaService.listaTurmas();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public TurmaDTOResposta listaTurmaPorId(@PathVariable Long id){
        try{
            return turmaService.listaTurmaPorId(id);
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public TurmaDTOResposta salvaTurma(@RequestBody TurmaDTORequisicao turmaRequisicao){
        try{
            return turmaService.salvaTurma(turmaRequisicao);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public TurmaDTOResposta atualizaTurma(@PathVariable Long id ,@RequestBody TurmaDTORequisicao turmaRequisicao){
        try{
            return turmaService.atualizaTurma(turmaRequisicao, id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaTurma(@PathVariable Long id){
        try{
            turmaService.deletaTurma(id);
            return new ResponseEntity<>("Deletado com sucesso!", HttpStatus.OK);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }





}
