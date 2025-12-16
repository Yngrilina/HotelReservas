package Model;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Reserva {

    /**
     * Default constructor
     */
    public Reserva() {
    }

    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private LocalDate dataCheckIn;

    /**
     * 
     */
    private LocalDate dataCheckOut;

    /**
     * 
     */
    private String status;

    /**
     * 
     */
    private double valorTotal;





    /**
     * @return
     */
    public double calcularValorTotal() {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @return
     */
    public void confirmarReserva() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void cancelarReserva() {
        // TODO implement here
        return null;
    }

}