package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Quarto {

    private Long id;
    private String tipo;
    private int capacidade;
    private double precoDiaria;
    private String status;

    private boolean tv;
    private boolean banheira;
    private boolean vistaMar;
    private double area;

    private List<Reserva> reservas;

    public Quarto(Long id, String tipo, int capacidade, double precoDiaria,
                  boolean tv, boolean banheira, boolean vistaMar, double area) {

        this.id = id;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.precoDiaria = precoDiaria;
        this.status = "DISPONIVEL";
        this.tv = tv;
        this.banheira = banheira;
        this.vistaMar = vistaMar;
        this.area = area;
        this.reservas = new ArrayList<>();
    }

    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public boolean isDisponivel(LocalDate checkin, LocalDate checkout) {

        if (!"DISPONIVEL".equalsIgnoreCase(status)) {
            return false;
        }

        for (Reserva r : reservas) {
            if (r.conflitaCom(checkin, checkout)) {
                return false;
            }
        }
        return true;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public double getPrecoDiaria() {
        return precoDiaria;
    }

    public double getArea() {
        return area;
    }

    public boolean hasTV() {
        return tv;
    }

    public boolean hasBanheira() {
        return banheira;
    }

    public boolean hasVistaMar() {
        return vistaMar;
    }

}