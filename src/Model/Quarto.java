package Model;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Quarto {

    /**
     * Default constructor
     */
    public Quarto() {
    }

    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private int numero;

    /**
     * 
     */
    private String tipo;

    /**
     * 
     */
    private int capacidade;

    /**
     * 
     */
    private double valorDiaria;

    /**
     * 
     */
    private String status;



    /**
     * @param dataCheckIn 
     * @param dataCheckOut 
     * @return
     */
    public boolean verificarDisponibilidade(void dataCheckIn, void dataCheckOut) {
        // TODO implement here
        return false;
    }

    /**
     * @param status 
     * @return
     */
    public void atualizarStatus(void status) {
        // TODO implement here
        return null;
    }

}