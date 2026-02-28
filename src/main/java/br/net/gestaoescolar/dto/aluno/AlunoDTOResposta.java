package br.net.gestaoescolar.dto.aluno;

import java.time.LocalDate;

public record AlunoDTOResposta(
        Long id,
        String nome,
        String email,
        String matricula,
        LocalDate data_nascimento
) {
}
