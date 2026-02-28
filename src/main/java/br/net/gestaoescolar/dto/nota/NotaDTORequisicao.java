package br.net.gestaoescolar.dto.nota;

public record NotaDTORequisicao(
        Long aluno_id,
        Long aula_id,
        double valor
) {
}
