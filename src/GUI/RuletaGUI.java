package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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

    public RuletaGUI(int ancho, int alto) {
        super(new GridBagLayout());
        this.ancho = ancho;
        this.alto = alto;
        setPreferredSize(new Dimension(ancho, alto));
        initComponents();
    }

    public void setNombreJugador(String nombre) {
        usuario.setText("Jugador: " + nombre);
    }

    public void setSaldoJugador(int dinero) {
        saldo.setText("Saldo: " + dinero + " Bs.");
    }

    public void setApuesta(int monto){
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

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTHWEST;
        usuario = new JLabel("Usuario: ");
        usuario.setFont(new Font("Arial", Font.PLAIN, 15));
        infoUsuario.add(usuario, c);

        c.gridx = 0;
        c.gridy = 1;
        saldo = new JLabel("Saldo: ");
        saldo.setFont(new Font("Arial", Font.PLAIN, 15));
        infoUsuario.add(saldo, c);

        c.gridx = 0;
        c.gridy = 2;
        apuesta = new JLabel("Apuesta: 0 Bs.");
        apuesta.setFont(new Font("Arial", Font.PLAIN, 15));
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

        JLabel turno = new JLabel("Turno: ");
        c.gridx = 0;
        c.gridy = 0;
        ruleta.add(turno, c);

        ruletaDisplay = new JLabel();
        ruletaDisplay.setPreferredSize(new Dimension(alto - 30, alto - 30));
        c.gridx = 0;
        c.gridy = 1;
        setRuletaDisplay(0);
        ruleta.add(ruletaDisplay, c);
    }

    private void initInfoPartida() {
        infoPartida = new JPanel();
        infoPartida.setPreferredSize(new Dimension((int) (ancho * 0.25), HEIGHT));
        GridBagConstraints c = new GridBagConstraints();

        JLabel resultados = new JLabel("Resultados");
        c.gridx = 0;
        c.gridy = 0;
        infoPartida.add(resultados, c);

        c.gridx = 0;
        c.gridy = 1;
        JTextArea infoArea = new JTextArea();
        String cabeceras = "Turno   Apuesta     Numero      Balance";
        infoArea.setText(cabeceras);
        JScrollPane scroll = new JScrollPane(infoArea);
        infoPartida.add(scroll, c);
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
}
