package br.net.gestaoescolar.controller;

import br.net.gestaoescolar.dto.curso.CursoDTOResposta;
import br.net.gestaoescolar.dto.professor.ProfessorDTORequisicao;
import br.net.gestaoescolar.dto.professor.ProfessorDTOResposta;
import br.net.gestaoescolar.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService){
        this.professorService = professorService;
    }

    @GetMapping
    public List<ProfessorDTOResposta> listaProfessores(){
        try{
            return professorService.listaProfessores();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ProfessorDTOResposta listaProfessorPorId(@PathVariable Long id){
        try{
            return professorService.listaProfessorPorId(id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ProfessorDTOResposta salvaProfessor(@RequestBody ProfessorDTORequisicao professorRequisicao){
        try{
            return professorService.salvaProfessor(professorRequisicao);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ProfessorDTOResposta atualizaProfessor(@PathVariable Long id ,@RequestBody ProfessorDTORequisicao professorRequisicao){
        try{
            return professorService.atualizaProfessor(professorRequisicao, id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaProfessor(@PathVariable Long id){
        try{
            professorService.deletaProfessor(id);
            return new ResponseEntity<>("Deletado com sucesso!", HttpStatus.OK);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
