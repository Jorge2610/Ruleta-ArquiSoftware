package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MesaDeJuego extends JPanel {

    private final Color ROJO = new Color(194, 39, 45);
    private final Color NEGRO = new Color(21, 21, 21);

    private JPanel probabilidades;
    private JPanel ruleta;
    private JPanel infoPartida;
    private JPanel tableroApuestas;
    private JPanel infoUsuario;
    private JPanel fichasApuestas;
    private JPanel controlPartida;
    private JLabel ruletaDisplay;

    private int valorApuesta;
    private ArrayList<JButton> casillaApostada;
    private ArrayList<String> textoCasilla;

    public MesaDeJuego() {
        setLayout(new GridBagLayout());
        initProbabilidades(0, 0);
        initRuleta(1, 0);
        initInfoPartida(2, 0);
        initTableroApuestas(0, 1);
        initInfoUsuario(0, 2);
        initFichasApuestas(1, 2);
        initControlPartida(2, 2);
        valorApuesta = 0;
        casillaApostada = new ArrayList<>();
        textoCasilla = new ArrayList<>();
    }

    private void initProbabilidades(int x, int y) {
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = x;
        cons.gridy = y;

        probabilidades = new JPanel();
        JLabel label = new JLabel("Probabilidades");
        probabilidades.add(label);
        add(probabilidades, cons);
    }

    private void initRuleta(int x, int y) {
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = x;
        cons.gridy = y;
        ruleta = new JPanel();
        ruletaDisplay = new JLabel();
        ruletaDisplay.setPreferredSize(new Dimension(250, 250));
        try {
            ImageIcon ruletaIcon = new ImageIcon(getClass().getResource("/Recursos/ruleta.png"));
            Icon icono = new ImageIcon(
                    ruletaIcon.getImage().getScaledInstance(ruletaDisplay.getPreferredSize().width - 5,
                            ruletaDisplay.getPreferredSize().height - 5, Image.SCALE_DEFAULT));
            ruletaDisplay = new JLabel(icono);
            ruleta.add(ruletaDisplay);
            add(ruleta, cons);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void initInfoPartida(int x, int y) {
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = x;
        cons.gridy = y;

        infoPartida = new JPanel();
        JLabel label = new JLabel("Info partida");
        infoPartida.add(label);
        add(infoPartida, cons);
    }

    private void initTableroApuestas(int x, int y) {
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = x;
        cons.gridy = y;
        cons.gridwidth = 3;
        cons.ipady = 0;
        tableroApuestas = new JPanel();
        initCasillas(tableroApuestas);
        add(tableroApuestas, cons);
    }

    private void initCasillas(JPanel tableroApuestas) {
        tableroApuestas.setLayout(new GridBagLayout());
        int anchoCasilla = 45;
        int altoCasilla = 35;
        GridBagConstraints cons = new GridBagConstraints();

        JButton[] casillasCol = new JButton[12];
        cons.gridy = 0;
        for (int i = 0; i < casillasCol.length; i++) {
            cons.gridx = i + 1;
            casillasCol[i] = new JButton("2:1");
            casillasCol[i].setPreferredSize(new Dimension(anchoCasilla, altoCasilla));
            casillasEvent(casillasCol[i]);
            tableroApuestas.add(casillasCol[i], cons);
        }

        JButton[][] casillas = new JButton[3][12];
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
                casillasEvent(casillas[i][j]);
                tableroApuestas.add(casillas[i][j], cons);
                contador++;
            }
        }

        JButton[] casillasFil = new JButton[3];
        cons.gridx = 13;
        for (int i = 0; i < casillasFil.length; i++) {
            cons.gridy = i + 1;
            casillasFil[i] = new JButton("2:1");
            casillasFil[i].setPreferredSize(new Dimension(anchoCasilla, altoCasilla));
            casillasEvent(casillasFil[i]);
            tableroApuestas.add(casillasFil[i], cons);
        }

        JPanel panelCeros = new JPanel(new GridBagLayout());
        cons.gridx = 0;
        cons.gridy = 1;
        cons.gridheight = 3;
        cons.fill = GridBagConstraints.VERTICAL;
        initBotonesCeros(panelCeros, anchoCasilla, altoCasilla);
        tableroApuestas.add(panelCeros, cons);

        cons.gridx = 1;
        cons.gridy = 4;
        cons.gridwidth = 4;
        cons.gridheight = 1;
        cons.fill = GridBagConstraints.HORIZONTAL;
        JButton prDocena = new JButton("1ra - 12");
        prDocena.setPreferredSize(new Dimension(anchoCasilla, altoCasilla));
        casillasEvent(prDocena);
        tableroApuestas.add(prDocena, cons);

        cons.gridx = 5;
        cons.gridy = 4;
        cons.gridwidth = 4;
        cons.fill = GridBagConstraints.HORIZONTAL;
        JButton sgDocena = new JButton("2da - 12");
        sgDocena.setPreferredSize(new Dimension(anchoCasilla, altoCasilla));
        casillasEvent(sgDocena);
        tableroApuestas.add(sgDocena, cons);

        cons.gridx = 9;
        cons.gridy = 4;
        cons.gridwidth = 4;
        cons.fill = GridBagConstraints.HORIZONTAL;
        JButton trDocena = new JButton("3ra - 12");
        trDocena.setPreferredSize(new Dimension(anchoCasilla, altoCasilla));
        casillasEvent(trDocena);
        tableroApuestas.add(trDocena, cons);

        cons.gridx = 1;
        cons.gridy = 5;
        cons.gridwidth = 2;
        cons.fill = GridBagConstraints.HORIZONTAL;
        JButton prMitad = new JButton("1 - 18");
        prMitad.setPreferredSize(new Dimension(anchoCasilla, altoCasilla));
        casillasEvent(prMitad);
        tableroApuestas.add(prMitad, cons);

        cons.gridx = 3;
        cons.gridy = 5;
        cons.gridwidth = 2;
        cons.fill = GridBagConstraints.HORIZONTAL;
        JButton par = new JButton("Par");
        par.setPreferredSize(new Dimension(anchoCasilla, altoCasilla));
        casillasEvent(par);
        tableroApuestas.add(par, cons);

        cons.gridx = 5;
        cons.gridy = 5;
        cons.gridwidth = 2;
        cons.fill = GridBagConstraints.HORIZONTAL;
        JButton rojo = new JButton();
        rojo.setBackground(ROJO);
        rojo.setPreferredSize(new Dimension(anchoCasilla, altoCasilla));
        casillasEvent(rojo);
        tableroApuestas.add(rojo, cons);

        cons.gridx = 7;
        cons.gridy = 5;
        cons.gridwidth = 2;
        cons.fill = GridBagConstraints.HORIZONTAL;
        JButton negro = new JButton();
        negro.setBackground(NEGRO);
        negro.setPreferredSize(new Dimension(anchoCasilla, altoCasilla));
        casillasEvent(negro);
        tableroApuestas.add(negro, cons);

        cons.gridx = 9;
        cons.gridy = 5;
        cons.gridwidth = 2;
        cons.fill = GridBagConstraints.HORIZONTAL;
        JButton impar = new JButton("Impar");
        impar.setPreferredSize(new Dimension(anchoCasilla, altoCasilla));
        casillasEvent(impar);
        tableroApuestas.add(impar, cons);

        cons.gridx = 11;
        cons.gridy = 5;
        cons.gridwidth = 2;
        cons.fill = GridBagConstraints.HORIZONTAL;
        JButton sgMitad = new JButton("19 - 36");
        sgMitad.setPreferredSize(new Dimension(anchoCasilla, altoCasilla));
        casillasEvent(sgMitad);
        tableroApuestas.add(sgMitad, cons);
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

    private void initBotonesCeros(JPanel panelCeros, int anchoCasilla, int altoCasilla) {
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.VERTICAL;
        cons.gridx = 0;
        cons.gridy = 0;
        JButton dobleCero = new JButton("00");
        dobleCero.setPreferredSize(new Dimension(anchoCasilla + 5, altoCasilla));
        casillasEvent(dobleCero);
        panelCeros.add(dobleCero, cons);

        cons.gridx = 0;
        cons.gridy = 1;
        JButton cero = new JButton("0");
        cero.setPreferredSize(new Dimension(anchoCasilla + 5, altoCasilla));
        casillasEvent(cero);
        panelCeros.add(cero, cons);
    }

    private void casillasEvent(JButton casilla) {
        casilla.addActionListener(e -> {
            if (valorApuesta != 0) {
                ImageIcon imagen = new ImageIcon(getClass().getResource("/Recursos/ficha" + valorApuesta + ".png"));
                Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
                if (!casillaApostada.contains(casilla)) {
                    casillaApostada.add(casilla);
                    textoCasilla.add(casilla.getText());
                }
                casilla.setText("");
                casilla.setIcon(icono);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                valorApuesta = 0;
            }
        });
    }

    private void initInfoUsuario(int x, int y) {
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = x;
        cons.gridy = y;

        infoUsuario = new JPanel();
        JLabel label = new JLabel("Info usuario");
        infoUsuario.add(label);
        add(infoUsuario, cons);
    }

    private void initFichasApuestas(int x, int y) {
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = x;
        cons.gridy = y;
        fichasApuestas = new JPanel();
        JButton[] fichas = new JButton[5];
        try {
            for (int i = 0; i < fichas.length; i++) {
                fichas[i] = new JButton();
                initFicha(fichas[i], i);
                fichasApuestas.add(fichas[i]);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        add(fichasApuestas, cons);
    }

    private void initFicha(JButton ficha, int i) {
        int valoresFicha[] = { 1, 5, 10, 25, 50 };
        ImageIcon fichaIcon = new ImageIcon(getClass().getResource("/Recursos/ficha" + valoresFicha[i] + ".png"));
        Icon icono = new ImageIcon(fichaIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        ficha.setBorder(null);
        ficha.setBackground(null);
        ficha.setIcon(icono);
        ficha.addActionListener(e -> {
            Image im = Toolkit.getDefaultToolkit()
                    .createImage(getClass().getResource("/Recursos/ficha" + valoresFicha[i] + ".png"));
            Cursor cur = Toolkit.getDefaultToolkit().createCustomCursor(im, new Point(10, 10), "1");
            setCursor(cur);
            valorApuesta = valoresFicha[i];
        });
    }

    private void initControlPartida(int x, int y) {
        GridBagConstraints cons = new GridBagConstraints();
        controlPartida = new JPanel(new GridBagLayout());
        JButton girarRuleta = new JButton("Girar ruleta");
        girarRuletaEvent(girarRuleta);
        cons.gridx = 0;
        cons.gridy = 0;
        cons.insets = new Insets(5, 5, 5, 5);
        cons.fill = GridBagConstraints.BOTH;
        controlPartida.add(girarRuleta, cons);
        JButton salir = new JButton("Salir");
        salir.addActionListener(e -> {
            System.exit(0);
        });
        cons.gridx = 0;
        cons.gridy = 1;
        controlPartida.add(salir, cons);
        cons.gridx = x;
        cons.gridy = y;
        cons.insets = new Insets(0, 0, 0, 0);
        add(controlPartida, cons);
    }

    private void girarRuletaEvent(JButton girarRuleta) {
        girarRuleta.addActionListener(e -> {
            try {
                ImageIcon ruletaGif = new ImageIcon(getClass().getResource("/Recursos/ruleta-gif.gif"));
                Icon icono = new ImageIcon(
                        ruletaGif.getImage().getScaledInstance(ruletaDisplay.getPreferredSize().width - 5,
                                ruletaDisplay.getPreferredSize().height - 5, Image.SCALE_DEFAULT));
                ruletaDisplay.setIcon(icono);
                /*
                 * TimerRuleta timer = new TimerRuleta(ruletaDisplay, 2500);
                 * timer.start();
                 */
                Timer timer = new Timer(2000, new ActionListener() { // 10 sec
                    public void actionPerformed(ActionEvent e) {
                        try {
                            ImageIcon ruletaIcon = new ImageIcon(getClass().getResource("/Recursos/ruleta.png"));
                            Icon icono = new ImageIcon(
                                    ruletaIcon.getImage().getScaledInstance(ruletaDisplay.getPreferredSize().width - 5,
                                            ruletaDisplay.getPreferredSize().height - 5, Image.SCALE_DEFAULT));
                            ruletaDisplay.setIcon(icono);
                        } catch (Exception error) {
                            System.out.println(error);
                        }
                    }
                });
                timer.setRepeats(false);
                timer.start();
                casillaApostada.get(0).setText(textoCasilla.get(0));
                casillaApostada.get(0).setIcon(null);
                casillaApostada.remove(0);
                textoCasilla.remove(0);
            } catch (Exception error) {
                System.out.println(error);
            }
        });
    }
}