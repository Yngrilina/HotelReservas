package model;

public class Administrador extends Usuario {

    private String cargo;

    public Administrador(Long id, String nome, String email, String senha, String cargo) {
        super(id, nome, email, senha);
        this.cargo = cargo;
    }

    public void cadastrarHotel(Hotel hotel) {
        if (hotel == null) {
            throw new IllegalArgumentException("Hotel inválido");
        }
    }

    public void cadastrarQuarto(Hotel hotel, Quarto quarto) {
        if (hotel == null || quarto == null) {
            throw new IllegalArgumentException("Hotel ou quarto inválido");
        }
        hotel.adicionarQuarto(quarto);
    }

    public void cadastrarServico(Servico servico) {
        if (servico == null) {
            throw new IllegalArgumentException("Serviço inválido");
        }
    }

    public void alterarStatusQuarto(Quarto quarto, String status) {
        if (quarto == null) {
            throw new IllegalArgumentException("Quarto inválido");
        }
        quarto.atualizarStatus(status);
    }

    public String getCargo() {
        return cargo;
    }
}