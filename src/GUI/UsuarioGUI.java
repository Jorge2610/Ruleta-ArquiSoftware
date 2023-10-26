package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Logica.Jugador;

public class UsuarioGUI extends JPanel {

    private CasinoRoyale casino;
    private ImageIcon backgroundImage;
    private final int ANCHO = 880;
    private final int ALTO = 750;

    public UsuarioGUI(CasinoRoyale casino) {
        super(new GridBagLayout());
        setPreferredSize(new Dimension(ANCHO, ALTO));
        backgroundImage = new ImageIcon(getClass().getResource("/Recursos/usuario.jpg"));
        this.casino = casino;
        initComponents();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, getPreferredSize().width + 20, getPreferredSize().height, this);
    }

    private void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel top = new JPanel();
        top.setOpaque(false);
        top.setPreferredSize(new Dimension(ANCHO, ALTO / 2));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(top, gbc);

        JPanel bottom = new JPanel(new GridBagLayout());
        bottom.setPreferredSize(new Dimension(ANCHO, ALTO / 2));
        bottom.setOpaque(false);
        initBottomPanel(bottom);
        gbc.gridy = 1;
        add(bottom, gbc);
    }

    private void initBottomPanel(JPanel bottom) {
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel left = new JPanel(new GridBagLayout());
        left.setOpaque(false);
        left.setPreferredSize(new Dimension(ANCHO / 2, ALTO / 2));
        initLeftPanel(left);
        gbc.gridx = 0;
        gbc.gridy = 0;
        bottom.add(left, gbc);

        JPanel right = new JPanel(new GridBagLayout());
        right.setPreferredSize(new Dimension(ANCHO / 2, ALTO / 2));
        right.setOpaque(false);
        gbc.gridx = 1;
        bottom.add(right, gbc);
    }

    private void initLeftPanel(JPanel left) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.ipadx = 10;
        gbc.ipady = 10;

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 0;
        JLabel titulo = new JLabel("Datos jugador");
        titulo.setFont(new Font("Arial", Font.BOLD, 25));
        left.add(titulo, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel nombreL = new JLabel("Nombre:");
        nombreL.setFont(new Font("Arial", Font.BOLD, 20));
        left.add(nombreL, gbc);

        gbc.gridy = 2;
        JLabel saldoL = new JLabel("Dinero (Bs.):");
        saldoL.setFont(new Font("Arial", Font.BOLD, 20));
        left.add(saldoL, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 1;
        JTextField nombreF = new JTextField();
        nombreF.setFont(new Font("Arial", Font.PLAIN, 20));
        left.add(nombreF, gbc);

        gbc.gridy = 2;
        JTextField saldoF = new JTextField();
        saldoF.setFont(new Font("Arial", Font.PLAIN, 20));
        left.add(saldoF, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 3;
        JButton jugar = new JButton("Jugar");
        jugar.setFont(new Font("Arial", Font.PLAIN, 20));
        jugar.addActionListener(e -> {
            String nombre = nombreF.getText();
            int saldo = Integer.parseInt(saldoF.getText());
            casino.setJugador(new Jugador(nombre, saldo));
            nombreF.setText("");
            saldoF.setText("");
            casino.cambiarCard("mesaDeJuego");
        });
        left.add(jugar, gbc);

        gbc.gridx = 2;
        JButton atras = new JButton("Atras");
        atras.setFont(new Font("Arial", Font.PLAIN, 20));
        atras.addActionListener(e -> {
            casino.cambiarCard("menu");
        });
        left.add(atras, gbc);
    }
}
