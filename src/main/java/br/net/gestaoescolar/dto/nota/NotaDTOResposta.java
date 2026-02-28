package br.net.gestaoescolar.dto.nota;

public record NotaDTOResposta(
        Long id,
        String aluno_nome,
        String aula_assunto,
        double valor
) {
}
