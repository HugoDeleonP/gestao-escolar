package br.net.gestaoescolar.controller;

import br.net.gestaoescolar.dto.aluno.AlunoDTORequisicao;
import br.net.gestaoescolar.dto.aluno.AlunoDTOResposta;
import br.net.gestaoescolar.dto.curso.CursoDTORequisicao;
import br.net.gestaoescolar.dto.curso.CursoDTOResposta;
import br.net.gestaoescolar.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService){
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<CursoDTOResposta> listaCurso(){
        try{
            return cursoService.listaCursos();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public CursoDTOResposta listaCursoPorId(@PathVariable Long id){
        try{
            return cursoService.listaCursoPorId(id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public CursoDTOResposta salvaCurso(@RequestBody CursoDTORequisicao cursoRequisicao){
        try{
            return cursoService.salvaCurso(cursoRequisicao);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public CursoDTOResposta atualizaCurso(@PathVariable Long id ,@RequestBody CursoDTORequisicao cursoRequisicao){
        try{
            return cursoService.atualizaCurso(cursoRequisicao, id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaCurso(@PathVariable Long id){
        try{
            cursoService.deletaCurso(id);
            return new ResponseEntity<>("Deletado com sucesso!", HttpStatus.OK);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


}
