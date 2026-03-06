package br.net.gestaoescolar.model;

public class Turma {

    private Long id;
    private String nome;
    private Long curso_id;
    private Long professor_id;

    public Turma(){}

    public Turma(Long id, String nome, Long curso_id, Long professor_id) {
        this.id = id;
        this.nome = nome;
        this.curso_id = curso_id;
        this.professor_id = professor_id;
    }

    public Turma(String nome, Long curso_id, Long professor_id) {
        this.nome = nome;
        this.curso_id = curso_id;
        this.professor_id = professor_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCurso_id() {
        return curso_id;
    }

    public void setCurso_id(Long curso_id) {
        this.curso_id = curso_id;
    }

    public Long getProfessor_id() {
        return professor_id;
    }

    public void setProfessor_id(Long professor_id) {
        this.professor_id = professor_id;
    }
}
