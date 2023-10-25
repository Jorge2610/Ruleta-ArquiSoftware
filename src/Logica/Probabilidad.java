package Logica;

public class Probabilidad {

    private int vecesPar;
    private int vecesImpar;
    private int vecesRojo;
    private int vecesNegro;
    private int total;

    public Probabilidad() {
        vecesPar = 18;
        vecesImpar = 18;
        vecesRojo = 18;
        vecesNegro = 18;
        total = 38;
    }

    public void incrementarVecesPar() {
        vecesPar++;
    }

    public void incrementarVecesImpar() {
        vecesImpar++;
    }

    public void incrementarVecesRojo() {
        vecesRojo++;
    }

    public void incrementarVecesNegro() {
        vecesNegro++;
    }

    public void incrementarTotal(){
        total++;
    }

    public int getProbabilidadPar() {
        double probabilidad = vecesPar * 1.0 / total;
        probabilidad = Math.round(probabilidad * 100);
        return (int) probabilidad;
    }

    public int getProbabilidadImpar() {
        double probabilidad = vecesImpar * 1.0 / total;
        probabilidad = Math.round(probabilidad * 100);
        return (int) probabilidad;
    }

    public int getProbabilidadRojo() {
        double probabilidad = vecesRojo * 1.0 / total;
        probabilidad = Math.round(probabilidad * 100);
        return (int) probabilidad;
    }

    public int getProbabilidadNegro() {
        double probabilidad = vecesNegro * 1.0 / total;
        probabilidad = Math.round(probabilidad * 100);
        return (int) probabilidad;
    }
}
