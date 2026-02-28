package br.net.gestaoescolar.controller;

import br.net.gestaoescolar.dto.aluno.AlunoDTORequisicao;
import br.net.gestaoescolar.dto.aluno.AlunoDTOResposta;
import br.net.gestaoescolar.dto.curso.CursoDTOResposta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    /*
    @GetMapping
    public List<CursoDTOResposta> listaCurso(){
        try{

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public CursoDTOResposta listaCursoPorId(@PathVariable Long id){
        try{

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public CursoDTOResposta salvaCurso(@RequestBody CursoDTOResposta cursoRequisicao){
        try{

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public CursoDTOResposta atualizaCurso(@PathVariable Long id ,@RequestBody CursoDTOResposta cursoRequisicao){
        try{

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaCurso(@PathVariable Long id){
        try{

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

     */
}
