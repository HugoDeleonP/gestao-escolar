package br.net.gestaoescolar.dto.curso;

import java.util.List;

public record CursoDTOResposta(
        Long id,
        String nome,
        String codigo,
        List<String> professores
) {
}
