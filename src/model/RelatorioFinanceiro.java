package model;

import java.util.ArrayList;
import java.util.List;

public class RelatorioFinanceiro {

    private double valorTotal;
    private List<Double> lancamentos;

    public RelatorioFinanceiro() {
        this.valorTotal = 0;
        this.lancamentos = new ArrayList<>();
    }

    public void adicionarValor(double valor) {
        lancamentos.add(valor);
        valorTotal += valor;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}