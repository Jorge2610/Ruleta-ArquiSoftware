package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Logica.ControlPartida;
import Logica.Jugador;
import Logica.Probabilidad;

public class MesaDeJuego extends JPanel {

    private final int ANCHO = 850;
    private final int ALTO = 300;

    private RuletaGUI ruleta;
    private TableroGUI tableroApuestas;
    private FichasGUI fichas;

    private ControlPartida controlPartida;
    private Jugador jugador;
    private Probabilidad probabilidad;

    private int valorApuesta;

    public MesaDeJuego() {
        setLayout(new GridBagLayout());
        initRuleta(0, 0);
        initTableroApuestas(0, 1);
        initFichas(0, 2);
        controlPartida = new ControlPartida(this);
        probabilidad = new Probabilidad();
        valorApuesta = 0;
    }

    public void setJugador(Jugador jugador) {
        ruleta.setNombreJugador(jugador.getNombre());
        ruleta.setSaldoJugador(jugador.getSaldo());
        this.jugador = jugador;
    }

    private void initRuleta(int x, int y) {
        GridBagConstraints cons = new GridBagConstraints();
        cons.insets = new Insets(10, 10, 10, 10);
        cons.gridx = x;
        cons.gridy = y;
        cons.gridwidth = 3;
        ruleta = new RuletaGUI(ANCHO, ALTO + 30);
        add(ruleta, cons);
    }

    private void initTableroApuestas(int x, int y) {
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = x;
        cons.gridy = y;
        cons.gridwidth = 3;
        cons.ipady = 0;
        tableroApuestas = new TableroGUI(ANCHO, ALTO, this);
        add(tableroApuestas, cons);
    }

    private void initFichas(int x, int y) {
        GridBagConstraints cons = new GridBagConstraints();
        cons.insets = new Insets(10, 10, 10, 10);
        cons.gridx = x;
        cons.gridy = y;
        fichas = new FichasGUI(ANCHO, ALTO / 3, this);
        add(fichas, cons);
    }

    public void setValorApuesta(int valorApuesta) {
        this.valorApuesta = valorApuesta;
    }

    public void girarRuleta() {
        ruleta.setRuletaDisplay(1);
        Timer timer = new Timer(2500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ruleta.setRuletaDisplay(2);
                gestionTurno();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void gestionTurno() {
        controlPartida.realizarTurno();
        mostrarNumeroSorteado();
        mostrarBalanceTurno();
        jugador.recibirGanancias(controlPartida.getPagoApuestas());
        ruleta.setSaldoJugador(jugador.getSaldo());
        guardarDatosTurno();
        actualizarProbabilidades();
        tableroApuestas.resetTablero();
        controlPartida.initCasillas();
        ruleta.setApuesta(controlPartida.getTotalApostado());
        ruleta.setTurno(controlPartida.getTurno());
    }

    private void guardarDatosTurno() {
        int turno = controlPartida.getTurno() - 1;
        String nroGanador = controlPartida.getNumeroSorteado() == 37 ? "00" : "" + controlPartida.getNumeroSorteado();
        int apostado = controlPartida.getTotalApostado();
        int pago = controlPartida.getPagoApuestas();
        int saldo = jugador.getSaldo();
        ruleta.insertTurnoHistorial(turno, nroGanador, apostado, pago, saldo);
    }

    private void actualizarProbabilidades() {
        probabilidad.incrementarTotal();
        if (controlPartida.getNumeroSorteado() > 0 && controlPartida.getNumeroSorteado() < 37) {
            if (controlPartida.getNumeroSorteado() % 2 == 0) {
                probabilidad.incrementarVecesPar();
            } else {
                probabilidad.incrementarVecesImpar();
            }
            if (getColorSorteado().equals(tableroApuestas.getColorRojo())) {
                probabilidad.incrementarVecesRojo();
            } else {
                probabilidad.incrementarVecesNegro();
            }
        }
        fichas.setProbabilidadImpar(probabilidad.getProbabilidadImpar());
        fichas.setProbabilidadPar(probabilidad.getProbabilidadPar());
        fichas.setProbabilidadNegro(probabilidad.getProbabilidadNegro());
        fichas.setProbabilidadRojo(probabilidad.getProbabilidadRojo());
    }

    private void mostrarNumeroSorteado() {
        JLabel numeroSorteado = new JLabel("" + controlPartida.getNumeroSorteado());
        numeroSorteado.setHorizontalAlignment(SwingConstants.CENTER);
        numeroSorteado.setFont(new Font("Arial", Font.BOLD, 30));
        if (controlPartida.getPosicionSorteado() != null) {
            Color color = getColorSorteado();
            numeroSorteado.setBackground(color);
            numeroSorteado.setOpaque(true);
        }
        if (numeroSorteado.getText().equals("37")) {
            numeroSorteado.setText("00");
        }
        JOptionPane.showMessageDialog(this, numeroSorteado, "Numero sorteado...",
                JOptionPane.PLAIN_MESSAGE);
    }

    private void mostrarBalanceTurno() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel apostado = new JLabel("Monto apostado:  " + controlPartida.getTotalApostado());
        apostado.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(apostado, gbc);
        gbc.gridy = 1;
        JLabel ganado = new JLabel("Monto ganado:  " + controlPartida.getPagoApuestas());
        ganado.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(ganado, gbc);
        gbc.gridy = 2;
        JLabel balance = new JLabel(
                "Balance:  " + (controlPartida.getPagoApuestas() - controlPartida.getTotalApostado()));
        balance.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(balance, gbc);
        JOptionPane.showMessageDialog(this, panel, "Resultado de las apuestas...",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public Color getColorSorteado() {
        return tableroApuestas.getCasilla(controlPartida.getPosicionSorteado()).getBackground();
    }

    public Color getColorRojo() {
        return tableroApuestas.getColorRojo();
    }

    public int[] buscarCasillaNro(int numero) {
        return tableroApuestas.buscarCasillaNro(numero);
    }

    public void realizarApuesta(int tipo, String casilla) {
        int valorCasilla = casilla.equals("00") ? 37 : Integer.parseInt(casilla);
        if (valorApuesta != 0 && jugador.apostar(valorApuesta)) {
            controlPartida.apostarCasilla(tipo, valorCasilla, valorApuesta);
            ruleta.setSaldoJugador(jugador.getSaldo());
            ruleta.setApuesta(controlPartida.getTotalApostado());
        }
    }

    public int retirarApuesta(int tipo, String casilla) {
        int valorCasilla = casilla.equals("00") ? 37 : Integer.parseInt(casilla);
        int valorFicha = controlPartida.retirarApuesta(tipo, valorCasilla);
        jugador.recibirGanancias(valorFicha);
        ruleta.setSaldoJugador(jugador.getSaldo());
        ruleta.setApuesta(controlPartida.getTotalApostado());
        return valorFicha;
    }

    public int verSiguienteApuesta(int tipo, String casilla) {
        int valorCasilla = casilla.equals("00") ? 37 : Integer.parseInt(casilla);
        int valorFicha = controlPartida.verSiguienteApuesta(tipo, valorCasilla);
        return valorFicha;
    }

    public int getValorApuesta() {
        return valorApuesta;
    }
}