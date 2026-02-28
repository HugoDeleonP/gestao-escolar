package br.net.gestaoescolar.controller;

import br.net.gestaoescolar.dto.aluno.AlunoDTORequisicao;
import br.net.gestaoescolar.dto.aluno.AlunoDTOResposta;
import br.net.gestaoescolar.dto.nota.NotaDTOResposta;
import br.net.gestaoescolar.repository.AlunoRepository;
import br.net.gestaoescolar.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {


    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService){
        this.alunoService = alunoService;
    }

    @GetMapping
    public List<AlunoDTOResposta> listaAlunos(){
        try{
            return alunoService.listaAlunos();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public AlunoDTOResposta listaAlunoPorId(@PathVariable Long id){
        try{
            return alunoService.listaAlunoPorId(id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public AlunoDTOResposta salvaAluno(@RequestBody AlunoDTORequisicao alunoRequisicao){
        try{
            return alunoService.salvaAluno(alunoRequisicao);
        }catch (SQLException e){
            throw new RuntimeException(e);

        }
    }

    @PutMapping("/{id}")
    public AlunoDTOResposta atualizaAluno(@PathVariable Long id ,@RequestBody AlunoDTORequisicao alunoRequisicao){
        try{
            return alunoService.atualizaAluno(alunoRequisicao, id);
        }catch (SQLException e){
            throw new RuntimeException(e);

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaAluno(@PathVariable Long id){
        try{
            alunoService.deletaAluno(id);
            return new ResponseEntity<>("Deletado com sucesso!", HttpStatus.OK);
        }catch (SQLException e){
            throw new RuntimeException(e);

        }
    }

    /*

    @GetMapping("/{id}/notas")
    public List<NotaDTOResposta> listaAlunoNota(@PathVariable Long id){
        try{

        }catch (SQLException e){
            throw new RuntimeException(e);

        }
    }

    */

}
