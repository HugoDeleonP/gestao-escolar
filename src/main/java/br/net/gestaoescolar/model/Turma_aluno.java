package br.net.gestaoescolar.model;

public class Turma_aluno {

    private Long turma_id;
    private Long aluno_id;

    public Turma_aluno(){}

    public Turma_aluno(Long turma_id, Long aluno_id) {
        this.turma_id = turma_id;
        this.aluno_id = aluno_id;
    }

    public Long getTurma_id() {
        return turma_id;
    }

    public void setTurma_id(Long turma_id) {
        this.turma_id = turma_id;
    }

    public Long getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(Long aluno_id) {
        this.aluno_id = aluno_id;
    }
}
