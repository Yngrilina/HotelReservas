package model;

public class Usuario {

    protected Long id;
    protected String nome;
    protected String email;
    protected String senha;
    protected boolean ativo;

    public Usuario(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ativo = true;
    }

    public boolean login(String email, String senha) {
        return ativo &&
                this.email.equals(email) &&
                this.senha.equals(senha);
    }

    public void logout() {

    }

    public void atualizarDados(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
}