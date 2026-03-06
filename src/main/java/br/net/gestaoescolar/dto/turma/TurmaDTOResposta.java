package br.net.gestaoescolar.dto.turma;

import java.util.List;

public record TurmaDTOResposta(
        Long id,
        String nome,
        String curso_nome,
        String professor_nome,
        List<String> alunoNomes
) {
}
