package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TableroGUI extends JPanel {

    private MesaDeJuego mesaDeJuego;
    private final Color ROJO = new Color(194, 39, 45);
    private final Color NEGRO = new Color(21, 21, 21);
    private JButton[][] casillas;
    private String[][] casillasValores;
    private HashMap<JButton, String> textoBoton;

    public TableroGUI(int ancho, int alto, MesaDeJuego mesaDeJuego) {
        super(new GridBagLayout());
        setPreferredSize(new Dimension(ancho, alto));
        this.mesaDeJuego = mesaDeJuego;
        initTablero();
    }

    private void initTablero() {
        int anchoCasilla = 45;
        int altoCasilla = 35;
        initCasillasColumnas(anchoCasilla, altoCasilla);
        initCasillasCeros(anchoCasilla, altoCasilla);
        initCasillasNumericas(anchoCasilla, altoCasilla);
        initCasillasFilas(anchoCasilla, altoCasilla);
        initCasillasDocenas(anchoCasilla, altoCasilla);
        initCasillasUltimaFila(anchoCasilla, altoCasilla);
        textoBoton = new HashMap<>();
    }

    public Color getColorRojo(){
        return ROJO;
    }

    private void initCasillasColumnas(int anchoCasilla, int altoCasilla) {
        GridBagConstraints cons = new GridBagConstraints();
        JButton[] casillasCol = new JButton[12];
        cons.gridy = 0;
        for (int i = 0; i < casillasCol.length; i++) {
            cons.gridx = i + 1;
            casillasCol[i] = new JButton("2:1");
            casillasCol[i].setPreferredSize(new Dimension(anchoCasilla, altoCasilla));
            casillasEvent(casillasCol[i], i, 0);
            add(casillasCol[i], cons);
        }
    }

    private void initCasillasCeros(int anchoCasilla, int altoCasilla) {
        JPanel panelCeros = new JPanel(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 1;
        cons.gridheight = 3;
        initBotonesCeros(panelCeros, anchoCasilla, altoCasilla);
        add(panelCeros, cons);
    }

    private void initBotonesCeros(JPanel panelCeros, int anchoCasilla, int altoCasilla) {
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 0;
        JButton dobleCero = new JButton("00");
        dobleCero.setPreferredSize(new Dimension(anchoCasilla + 5, altoCasilla + (altoCasilla / 2)));
        casillasPlenasEvent(dobleCero, -1, -1);
        panelCeros.add(dobleCero, cons);

        cons.gridx = 0;
        cons.gridy = 1;
        JButton cero = new JButton("0");
        cero.setPreferredSize(new Dimension(anchoCasilla + 5, altoCasilla + (altoCasilla / 2) + 1));
        casillasPlenasEvent(cero, -1, -1);
        panelCeros.add(cero, cons);
    }

    private void initCasillasNumericas(int anchoCasilla, int altoCasilla) {
        GridBagConstraints cons = new GridBagConstraints();
        casillas = new JButton[3][12];
        casillasValores = new String[3][12];
        int contador = 1;
        Color colorPrevio = ROJO;
        for (int j = 0; j < casillas[0].length; j++) {
            for (int i = casillas.length - 1; i >= 0; i--) {
                cons.gridx = j + 1;
                cons.gridy = i + 1;
                casillas[i][j] = new JButton();
                casillas[i][j].setPreferredSize(new Dimension(anchoCasilla, altoCasilla));
                Color color = getColorCasilla(contador, colorPrevio);
                colorPrevio = color;
                casillas[i][j].setBackground(color);
                casillas[i][j].setText("" + contador);
                casillasValores[i][j] = "" + contador;
                casillasPlenasEvent(casillas[i][j], i, j);
                add(casillas[i][j], cons);
                contador++;
            }
        }
    }

    private Color getColorCasilla(int numCasilla, Color colorPrevio) {
        if (numCasilla == 1) {
            return ROJO;
        }
        if (numCasilla == 11) {
            return NEGRO;
        }
        if (numCasilla == 19) {
            return ROJO;
        }
        if (numCasilla == 29) {
            return NEGRO;
        }
        return colorPrevio.equals(ROJO) ? NEGRO : ROJO;
    }

    private void initCasillasFilas(int anchoCasilla, int altoCasilla) {
        GridBagConstraints cons = new GridBagConstraints();
        JButton[] casillasFil = new JButton[3];
        cons.gridx = 13;
        for (int i = 0; i < casillasFil.length; i++) {
            cons.gridy = i + 1;
            casillasFil[i] = new JButton("2:1");
            casillasFil[i].setPreferredSize(new Dimension(anchoCasilla, altoCasilla));
            casillasEvent(casillasFil[i], i, 2);
            add(casillasFil[i], cons);
        }
    }

    private void initCasillasDocenas(int anchoCasilla, int altoCasilla) {
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridwidth = 4;
        cons.gridheight = 1;
        cons.fill = GridBagConstraints.HORIZONTAL;

        cons.gridx = 1;
        cons.gridy = 4;
        JButton prDocena = new JButton("1ra - 12");
        prDocena.setPreferredSize(new Dimension(anchoCasilla, altoCasilla));
        casillasEvent(prDocena, 0, 3);
        add(prDocena, cons);

        cons.gridx = 5;
        JButton sgDocena = new JButton("2da - 12");
        sgDocena.setPreferredSize(new Dimension(anchoCasilla, altoCasilla));
        casillasEvent(sgDocena, 1, 3);
        add(sgDocena, cons);

        cons.gridx = 9;
        JButton trDocena = new JButton("3ra - 12");
        trDocena.setPreferredSize(new Dimension(anchoCasilla, altoCasilla));
        casillasEvent(trDocena, 2, 3);
        add(trDocena, cons);
    }

    private void initCasillasUltimaFila(int anchoCasilla, int altoCasilla) {
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridwidth = 2;
        cons.fill = GridBagConstraints.HORIZONTAL;

        cons.gridx = 1;
        cons.gridy = 5;
        JButton prMitad = new JButton("1 - 18");
        prMitad.setPreferredSize(new Dimension(anchoCasilla, altoCasilla));
        casillasEvent(prMitad, 0, 4);
        add(prMitad, cons);

        cons.gridx = 3;
        JButton par = new JButton("Par");
        par.setPreferredSize(new Dimension(anchoCasilla, altoCasilla));
        casillasEvent(par, 0, 5);
        add(par, cons);

        cons.gridx = 5;
        JButton rojo = new JButton();
        rojo.setBackground(ROJO);
        rojo.setPreferredSize(new Dimension(anchoCasilla, altoCasilla));
        casillasEvent(rojo, 0, 6);
        add(rojo, cons);

        cons.gridx = 7;
        JButton negro = new JButton();
        negro.setBackground(NEGRO);
        negro.setPreferredSize(new Dimension(anchoCasilla, altoCasilla));
        casillasEvent(negro, 1, 6);
        add(negro, cons);

        cons.gridx = 9;
        JButton impar = new JButton("Impar");
        impar.setPreferredSize(new Dimension(anchoCasilla, altoCasilla));
        casillasEvent(impar, 1, 5);
        add(impar, cons);

        cons.gridx = 11;
        JButton sgMitad = new JButton("19 - 36");
        sgMitad.setPreferredSize(new Dimension(anchoCasilla, altoCasilla));
        casillasEvent(sgMitad, 1, 4);
        add(sgMitad, cons);
    }

    private void casillasPlenasEvent(JButton casilla, int i, int j) {
        String valor = i == -1 ? casilla.getText() : casillasValores[i][j];
        casilla.addMouseListener(getMouseListener(casilla, valor, 1));
    }

    private void casillasEvent(JButton casilla, int i, int tipo) {
        casilla.addMouseListener(getMouseListener(casilla, "" + i, tipo));
    }

    private MouseListener getMouseListener(JButton casilla, String i, int tipo){
        return new MouseListener() {
            public void mousePressed(MouseEvent me) {
            }

            public void mouseReleased(MouseEvent me) {
            }

            public void mouseEntered(MouseEvent me) {
            }

            public void mouseExited(MouseEvent me) {
            }

            public void mouseClicked(MouseEvent me) {
                if (me.getButton() == MouseEvent.BUTTON1) {
                    clickIzquierdo(casilla, tipo, i);
                }
                if (me.getButton() == MouseEvent.BUTTON3) {
                    clickDerecho(casilla, tipo, i);
                }
            }
        };
    }

    private void clickIzquierdo(JButton casilla, int tipo, String i) {
        if (mesaDeJuego.getValorApuesta() != 0) {
            mesaDeJuego.realizarApuesta(tipo, i);
            if (textoBoton.get(casilla) == null) {
                textoBoton.put(casilla, casilla.getText());
            }
            setCasillaIcon(casilla, mesaDeJuego.getValorApuesta());
        }
    }

    private void clickDerecho(JButton casilla, int tipo, String i) {
        if (textoBoton.get(casilla) != null) {
            mesaDeJuego.retirarApuesta(tipo, "" + i);
            int valorFicha = mesaDeJuego.verSiguienteApuesta(tipo, i);
            if (valorFicha != 0) {
                setCasillaIcon(casilla, valorFicha);
            } else {
                resetCasillaText(casilla, textoBoton.get(casilla));
            }
        }
    }

    private void setCasillaIcon(JButton casilla, int valorFicha) {
        ImageIcon fichaIcon = new ImageIcon(getClass().getResource("/Recursos/ficha" + valorFicha + ".png"));
        Icon icono = new ImageIcon(fichaIcon.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        casilla.setText("");
        casilla.setIcon(icono);
    }

    private void resetCasillaText(JButton casilla, String texto) {
        casilla.setIcon(null);
        casilla.setText(texto);
    }

    public void resetTablero(){
        for (Map.Entry<JButton, String> set : textoBoton.entrySet()) {
            resetCasillaText(set.getKey(), set.getValue());
        }
        textoBoton = new HashMap<>();
    }

    public void setCursor(Cursor cursor) {
        setCursor(cursor);
    }

    public int[] buscarCasillaNro(int numero) {
        int[] posicion = new int[2];
        boolean encontrado = false;
        int i = 0;
        int j = 0;
        while (!encontrado && i < casillas.length) {
            while (!encontrado && j < casillas[0].length) {
                encontrado = casillasValores[i][j].equals("" + numero);
                if (encontrado) {
                    posicion[0] = i;
                    posicion[1] = j;
                }
                j++;
            }
            j = 0;
            i++;
        }
        return posicion;
    }

    public JButton getCasilla(int[] posicion) {
        return casillas[posicion[0]][posicion[1]];
    }
}
