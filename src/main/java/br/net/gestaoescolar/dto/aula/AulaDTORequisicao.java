package br.net.gestaoescolar.dto.aula;

import java.time.LocalDateTime;

public record AulaDTORequisicao(
        Long turma_id,
        LocalDateTime data_hora,
        String assunto
) {
}
