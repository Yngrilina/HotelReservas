package test;

import model.Quarto;

import java.time.LocalDate;

// Este código simula a classe Quarto para que a classe Hotel possa ser testada
public class QuartoStub extends Quarto {
    public QuartoStub(Long id, String tipo, int capacidade, double precoDiaria, boolean tv, boolean banheira, boolean vistaMar, double area) {
        super(id, tipo, capacidade, precoDiaria, tv, banheira, vistaMar, area);
    }

    @Override
    public boolean isDisponivel(LocalDate checkin, LocalDate checkout) {
        // Retorna sempre true para não travar os testes da lógica do Hotel
        return true;
    }
}