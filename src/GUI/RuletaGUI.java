package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Logica.Historial;

public class RuletaGUI extends JPanel {

    private int ancho;
    private int alto;
    private JPanel infoUsuario;
    private JLabel usuario;
    private JLabel saldo;
    private JLabel apuesta;
    private JPanel ruleta;
    private JLabel ruletaDisplay;
    private JPanel infoPartida;
    private JLabel turno;
    private Historial historial;

    public RuletaGUI(int ancho, int alto) {
        super(new GridBagLayout());
        this.ancho = ancho;
        this.alto = alto;
        setPreferredSize(new Dimension(ancho, alto));
        initComponents();
        historial = new Historial();
    }

    public void setNombreJugador(String nombre) {
        usuario.setText("Jugador: " + nombre);
    }

    public void setSaldoJugador(int dinero) {
        saldo.setText("Saldo: " + dinero + " Bs.");
    }

    public void setApuesta(int monto) {
        apuesta.setText("Apuesta: " + monto + " Bs.");
    }

    private void initComponents() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;

        c.gridx = 0;
        c.gridy = 0;
        initInfoUsuario();
        add(infoUsuario, c);

        c.gridx = 1;
        initRuleta();
        add(ruleta, c);

        c.gridx = 2;
        initInfoPartida();
        add(infoPartida, c);
    }

    private void initInfoUsuario() {
        infoUsuario = new JPanel(new GridBagLayout());
        infoUsuario.setPreferredSize(new Dimension((int) (ancho * 0.25), alto));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2, 0, 2, 0);

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTHWEST;
        usuario = new JLabel("Usuario: ");
        usuario.setFont(new Font("Arial", Font.PLAIN, 18));
        infoUsuario.add(usuario, c);

        c.gridx = 0;
        c.gridy = 1;
        saldo = new JLabel("Saldo: ");
        saldo.setFont(new Font("Arial", Font.PLAIN, 18));
        infoUsuario.add(saldo, c);

        c.gridx = 0;
        c.gridy = 2;
        apuesta = new JLabel("Apuesta: 0 Bs.");
        apuesta.setFont(new Font("Arial", Font.PLAIN, 18));
        infoUsuario.add(apuesta, c);

        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        JLabel espacio = new JLabel();
        infoUsuario.add(espacio, c);
    }

    private void initRuleta() {
        ruleta = new JPanel(new GridBagLayout());
        ruleta.setPreferredSize(new Dimension((int) (ancho * 0.5), alto));
        GridBagConstraints c = new GridBagConstraints();

        turno = new JLabel("Turno: 1");
        turno.setFont(new Font("Arial", Font.PLAIN, 20));
        c.gridx = 0;
        c.gridy = 0;
        ruleta.add(turno, c);

        ruletaDisplay = new JLabel();
        ruletaDisplay.setPreferredSize(new Dimension(alto - 25, alto - 25));
        c.gridx = 0;
        c.gridy = 1;
        setRuletaDisplay(0);
        ruleta.add(ruletaDisplay, c);
    }

    private void initInfoPartida() {
        infoPartida = new JPanel();
        infoPartida.setPreferredSize(new Dimension((int) (ancho * 0.25), HEIGHT));
        GridBagConstraints c = new GridBagConstraints();

        JButton resultados = new JButton("Ver historial");
        resultados.setFont(new Font("Arial", Font.PLAIN, 18));
        resultados.addActionListener(e -> {
            JScrollPane scroll = new JScrollPane(historial.getTable());
            JPanel panel = new JPanel();
            scroll.setPreferredSize(new Dimension(550, 150));
            panel.add(scroll);
            JOptionPane.showMessageDialog(getParent(), panel, "Historial de partida",
                    JOptionPane.PLAIN_MESSAGE);
        });
        c.gridx = 0;
        c.gridy = 0;
        infoPartida.add(resultados, c);
    }

    public void setRuletaDisplay(int control) {
        try {
            String ruta = "/Recursos/ruleta.png";
            if (control == 1) {
                ruta = "/Recursos/ruleta-gif.gif";
            }
            ImageIcon ruletaIcon = new ImageIcon(getClass().getResource(ruta));
            int lado = ruletaDisplay.getPreferredSize().height;
            Icon icono = new ImageIcon(ruletaIcon.getImage().getScaledInstance(lado, lado, Image.SCALE_DEFAULT));
            ruletaDisplay.setIcon(icono);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setTurno(int turno) {
        this.turno.setText("Turno:  " + turno);
    }

    public void insertTurnoHistorial(int turno, String nroGanador, int apuesta, int pago, int saldo){
        historial.insertTurno(turno, nroGanador, apuesta, pago, saldo);
    }

    public void resetHistorial(){
        historial.resetTabla();
    }
}
