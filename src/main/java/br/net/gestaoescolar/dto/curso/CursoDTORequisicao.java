package br.net.gestaoescolar.dto.curso;

import java.util.List;

public record CursoDTORequisicao(
        String nome,
        String codigo,
        List<Long> professorIds

) {
}
