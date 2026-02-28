package br.net.gestaoescolar.model;

public class Nota {

    private Long id;
    private Long aluno_id;
    private Long aula_id;
    private double valor;

    public Nota(){}

    public Nota(Long id, Long aluno_id, Long aula_id, double valor) {
        this.id = id;
        this.aluno_id = aluno_id;
        this.aula_id = aula_id;
        this.valor = valor;
    }

    public Nota(Long aluno_id, Long aula_id, double valor) {
        this.aluno_id = aluno_id;
        this.aula_id = aula_id;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(Long aluno_id) {
        this.aluno_id = aluno_id;
    }

    public Long getAula_id() {
        return aula_id;
    }

    public void setAula_id(Long aula_id) {
        this.aula_id = aula_id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
