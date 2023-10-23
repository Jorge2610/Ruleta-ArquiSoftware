package Logica;

import java.util.Stack;

public class Apuesta implements GestionApuesta {

    protected Stack<Integer> fichasApostadas;
    protected int pagoApuesta;
    protected int totalApuesta;

    public Apuesta(int pagoApuesta) {
        fichasApostadas = new Stack<>();
        this.pagoApuesta = pagoApuesta;
        totalApuesta = 0;
    }

    @Override
    public void realizarApuesta(int valorFicha) {
        fichasApostadas.push(valorFicha);
        totalApuesta += valorFicha;
    }

    @Override
    public int retirarApuesta() {
        if (fichasApostadas.size() > 0) {
            int fichaRetirada = fichasApostadas.pop();
            totalApuesta -= fichaRetirada;
            return fichaRetirada;
        }
        return 0;
    }

    @Override
    public int verUltimaApuesta(){
        if(fichasApostadas.size() > 0){
            return fichasApostadas.peek();
        }
        return 0;
    }

    @Override
    public int getGanancia() {
        return totalApuesta * pagoApuesta + totalApuesta;
    }
}
