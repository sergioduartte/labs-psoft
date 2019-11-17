package psoft.lab2.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Disciplina implements Comparable<Disciplina> {

    private String nome;
    private double nota;
    private long id;

    @JsonCreator
    public Disciplina(long id, String nome, double nota) {
        this.id = id;
        this.nome = nome;
        this.nota = nota;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int compareTo(Disciplina outraDisc) {
        if (this.nota < outraDisc.getNota()) {
            return 1;
        }
        if (this.nota > outraDisc.getNota()) {
            return -1;
        }
        return 0;
    }

}
