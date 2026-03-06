package br.net.gestaoescolar.controller;

import br.net.gestaoescolar.dto.aluno.AlunoDTORequisicao;
import br.net.gestaoescolar.dto.aluno.AlunoDTOResposta;
import br.net.gestaoescolar.dto.nota.NotaDTORequisicao;
import br.net.gestaoescolar.dto.nota.NotaDTOResposta;
import br.net.gestaoescolar.service.NotaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/notas")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService){
        this.notaService = notaService;
    }

    @GetMapping
    public List<NotaDTOResposta> listaNotas(){
        try{
            return notaService.listaNotas();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public NotaDTOResposta listaNotaPorId(@PathVariable Long id){
        try{
            return notaService.listaNotaPorId(id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public NotaDTOResposta salvaNota(@RequestBody NotaDTORequisicao notaRequisicao){
        try{
            return notaService.salvaNota(notaRequisicao);
        }catch (SQLException e){
            throw new RuntimeException(e);

        }
    }

    @PutMapping("/{id}")
    public NotaDTOResposta atualizaNota(@PathVariable Long id ,@RequestBody NotaDTORequisicao notaRequisicao){
        try{
            return notaService.atualizaNota(notaRequisicao, id);
        }catch (SQLException e){
            throw new RuntimeException(e);

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaNota(@PathVariable Long id){
        try{
            notaService.deletaNota(id);
            return new ResponseEntity<>("Deletado com sucesso!", HttpStatus.OK);
        }catch (SQLException e){
            throw new RuntimeException(e);

        }
    }

}
