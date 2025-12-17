package model;

import java.time.LocalDate;

public class Avaliacao {

    private int nota;
    private String comentario;
    private LocalDate data;

    public Avaliacao(int nota, String comentario) {
        this.nota = nota;
        this.comentario = comentario;
        this.data = LocalDate.now();
    }

    public int getNota() {
        return nota;
    }
}