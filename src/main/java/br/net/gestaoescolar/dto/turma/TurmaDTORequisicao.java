package br.net.gestaoescolar.dto.turma;

import java.util.List;

public record TurmaDTORequisicao(
        String nome,
        Long curso_id,
        Long professor_id,
        List<Long> alunoIds
) {
}
