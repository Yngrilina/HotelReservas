package model;

import java.time.LocalDate;

public class Avaliacao {

    private Long id;
    private int nota;
    private String comentario;
    private LocalDate dataAvaliacao;

    public Avaliacao(int nota, String comentario) {
        validarNota(nota);
        this.nota = nota;
        this.comentario = comentario;
        this.dataAvaliacao = LocalDate.now();
    }

    public void editarAvaliacao(int nota, String comentario) {
        validarNota(nota);
        this.nota = nota;
        this.comentario = comentario;
    }

    public void excluirAvaliacao() {
        this.comentario = null;
        this.nota = 0;
    }

    private void validarNota(int nota) {
        if (nota < 1 || nota > 5) {
            throw new IllegalArgumentException("Nota deve estar entre 1 e 5");
        }
    }

    public int getNota() {
        return nota;
    }
}