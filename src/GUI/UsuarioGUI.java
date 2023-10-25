package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Logica.Jugador;

public class UsuarioGUI extends JPanel{

    private CasinoRoyale casino;

    public UsuarioGUI(CasinoRoyale casino){
        super(new GridBagLayout());
        setPreferredSize(new Dimension(850, 750));
        this.casino = casino;
        initComponents();
    }

    private void initComponents(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.ipadx = 10;
        gbc.ipady = 10;

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 0;
        JLabel titulo = new JLabel("Datos jugador");
        titulo.setFont(new Font("Arial", Font.BOLD, 25));
        add(titulo, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel nombreL = new JLabel("Nombre:");
        nombreL.setFont(new Font("Arial", Font.BOLD, 20));
        add(nombreL, gbc);

        gbc.gridy = 2;
        JLabel saldoL = new JLabel("Dinero (Bs.):");
        saldoL.setFont(new Font("Arial", Font.BOLD, 20));
        add(saldoL, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 1;
        JTextField nombreF = new JTextField();
        nombreF.setFont(new Font("Arial", Font.PLAIN, 20));
        add(nombreF, gbc);

        gbc.gridy = 2;
        JTextField saldoF = new JTextField();
        saldoF.setFont(new Font("Arial", Font.PLAIN, 20));
        add(saldoF, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 3;
        JButton atras = new JButton("Atras");
        atras.setFont(new Font("Arial", Font.PLAIN, 20));
        add(atras, gbc);

        gbc.gridx = 2;
        JButton jugar = new JButton("Jugar");
        jugar.setFont(new Font("Arial", Font.PLAIN, 20));
        jugar.addActionListener(e -> {
            String nombre = nombreF.getText();
            int saldo = Integer.parseInt(saldoF.getText());
            casino.setJugador(new Jugador(nombre, saldo));
            casino.cambiarCard("mesaDeJuego");
        });
        add(jugar, gbc);
    }
    
}
