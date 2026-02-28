package br.net.gestaoescolar.dto.aula;

import java.time.LocalDateTime;

public record AulaDTOResposta(
        Long id,
        String turma_nome,
        LocalDateTime data_hora,
        String assunto
) {
}
