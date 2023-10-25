package Logica;

import java.awt.Color;
import java.util.Random;

import GUI.MesaDeJuego;
import Logica.TiposDeApuesta.Columna;
import Logica.TiposDeApuesta.Docena;
import Logica.TiposDeApuesta.Fila;
import Logica.TiposDeApuesta.ParImpar;
import Logica.TiposDeApuesta.PasaFalta;
import Logica.TiposDeApuesta.Plena;
import Logica.TiposDeApuesta.RojoNegro;

public class ControlPartida {

    private MesaDeJuego mesaDeJuego;
    private Random generador;
    private int numeroSorteado;
    private int pagoApuestas;
    private int totalApostado;
    private int[] posicionSorteado;
    private Plena[] casillasPlenas;
    private Columna[] casillasColumna;
    private Fila[] casillasFila;
    private Docena[] casillasDocena;
    private PasaFalta[] casillasPasaFalta;
    private ParImpar[] casillasParImpar;
    private RojoNegro[] casillasRojoNegro;
    private int turno;

    public ControlPartida(MesaDeJuego mesaDeJuego) {
        this.mesaDeJuego = mesaDeJuego;
        generador = new Random();
        turno = 1;
        initCasillas();
    }

    public void initCasillas() {
        casillasPlenas = new Plena[38];
        initCasillasPlenas();
        casillasColumna = new Columna[12];
        initCasillasColumna();
        casillasFila = new Fila[] { new Fila(), new Fila(), new Fila() };
        casillasDocena = new Docena[] { new Docena(), new Docena(), new Docena() };
        casillasPasaFalta = new PasaFalta[] { new PasaFalta(), new PasaFalta() };
        casillasParImpar = new ParImpar[] { new ParImpar(), new ParImpar() };
        casillasRojoNegro = new RojoNegro[] { new RojoNegro(), new RojoNegro() };
        pagoApuestas = 0;
        totalApostado = 0;
    }

    public int getTurno(){
        return turno;
    }

    private void initCasillasPlenas() {
        for (int i = 0; i < casillasPlenas.length; i++) {
            casillasPlenas[i] = new Plena();
        }
    }

    private void initCasillasColumna() {
        for (int i = 0; i < casillasColumna.length; i++) {
            casillasColumna[i] = new Columna();
        }
    }

    public void realizarTurno() {  
        numeroSorteado = generador.nextInt(38);
        if (numeroSorteado > 0 && numeroSorteado < 37) {
            posicionSorteado = mesaDeJuego.buscarCasillaNro(numeroSorteado);
        } else {
            posicionSorteado = null;
        }
        pagoApuestas = pagarApuestas();
        turno++;
    }

    public int getNumeroSorteado() {
        return numeroSorteado;
    }

    public int[] getPosicionSorteado() {
        return posicionSorteado;
    }

    public int getPagoApuestas(){
        return pagoApuestas;
    }

    public int getTotalApostado(){
        return totalApostado;
    }

    public void apostarCasilla(int tipo, int casilla, int valorFicha) {
        Apuesta tipoApuesta = determinarTipoApuesta(tipo)[casilla];
        tipoApuesta.realizarApuesta(valorFicha);
        totalApostado += valorFicha;
    }

    public int retirarApuesta(int tipo, int casilla) {
        Apuesta tipoApuesta = determinarTipoApuesta(tipo)[casilla];
        int valorFicha = tipoApuesta.retirarApuesta();
        totalApostado -= valorFicha;
        return valorFicha;
    }

    public int verSiguienteApuesta(int tipo, int casilla) {
        Apuesta tipoApuesta = determinarTipoApuesta(tipo)[casilla];
        int valorFicha = tipoApuesta.verUltimaApuesta();
        return valorFicha;
    }

    private Apuesta[] determinarTipoApuesta(int tipo) {
        switch (tipo) {
            case 0:
                return (Apuesta[]) casillasColumna;
            case 1:
                return (Apuesta[]) casillasPlenas;
            case 2:
                return (Apuesta[]) casillasFila;
            case 3:
                return (Apuesta[]) casillasDocena;
            case 4:
                return (Apuesta[]) casillasPasaFalta;
            case 5:
                return (Apuesta[]) casillasParImpar;
            default:
                return (Apuesta[]) casillasRojoNegro;
        }
    }

    public int pagarApuestas() {
        int totalGanado = 0;
        totalGanado += getPagoColumnas();
        totalGanado += casillasPlenas[numeroSorteado].getGanancia();
        totalGanado += getPagoFilas();
        totalGanado += getPagoDocenas();
        totalGanado += getPagoPasaFalta();
        totalGanado += getPagoParImpar();
        totalGanado += getPagoRojoNegro();
        return totalGanado;
    }

    private int getPagoColumnas() {
        int pagoColumnas = 0;
        if (numeroSorteado > 0 && numeroSorteado < 37) {
            pagoColumnas += casillasColumna[posicionSorteado[1]].getGanancia();
        }
        return pagoColumnas;
    }

    private int getPagoFilas() {
        int pagoFilas = 0;
        if (numeroSorteado > 0 && numeroSorteado < 37) {
            pagoFilas += casillasFila[posicionSorteado[0]].getGanancia();
        }
        return pagoFilas;
    }

    private int getPagoDocenas() {
        int pagoDocenas = 0;
        if (numeroSorteado > 0 && numeroSorteado < 37) {
            if (numeroSorteado > 0 && numeroSorteado < 13) {
                pagoDocenas += casillasDocena[0].getGanancia();
            } else if (numeroSorteado > 12 && numeroSorteado < 25) {
                pagoDocenas += casillasDocena[1].getGanancia();
            } else {
                pagoDocenas += casillasDocena[2].getGanancia();
            }
        }
        return pagoDocenas;
    }

    private int getPagoPasaFalta() {
        int pagoPasaFalta = 0;
        if (numeroSorteado > 0 && numeroSorteado < 37) {
            if (numeroSorteado > 0 && numeroSorteado < 19) {
                pagoPasaFalta += casillasPasaFalta[0].getGanancia();
            } else {
                pagoPasaFalta += casillasPasaFalta[1].getGanancia();
            }
        }
        return pagoPasaFalta;
    }

    private int getPagoParImpar() {
        int pagoParImpar = 0;
        if (numeroSorteado > 0 && numeroSorteado < 37) {
            if (numeroSorteado % 2 == 0) {
                pagoParImpar += casillasParImpar[0].getGanancia();
            } else {
                pagoParImpar += casillasParImpar[1].getGanancia();
            }
        }
        return pagoParImpar;
    }

    private int getPagoRojoNegro() {
        int pagoRojoNegro = 0;
        if (numeroSorteado > 0 && numeroSorteado < 37) {
            Color sorteado = mesaDeJuego.getColorSorteado();
            if (sorteado.equals(mesaDeJuego.getColorRojo())) {
                pagoRojoNegro += casillasRojoNegro[0].getGanancia();
            } else {
                pagoRojoNegro += casillasRojoNegro[1].getGanancia();
            }
        }
        return pagoRojoNegro;
    }
}
