package Logica;

public class Jugador {

    private String nombre;
    private int saldo;

    public Jugador(String nombre, int saldo){
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public String getNombre(){
        return nombre;
    }

    public int getSaldo(){
        return saldo;
    }

    public boolean apostar(int apuesta){
        boolean apostado = false;
        if(apuesta <= saldo){
            saldo -= apuesta;
            apostado = true;
        }
        return apostado;
    }

    public void recibirGanancias(int ganancia){
        saldo += ganancia;
    }
    
}
