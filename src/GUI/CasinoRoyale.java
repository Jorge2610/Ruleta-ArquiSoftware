package GUI;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CasinoRoyale {

    private JFrame frame;
    private JPanel menu;
    private JPanel usuario;
    private MesaDeJuego mesaDeJuego;

    public CasinoRoyale() {
        frame = new JFrame("Casino Royale");
        frame.setLayout(new CardLayout());
        initComponents();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void initComponents(){
        mesaDeJuego = new MesaDeJuego();
        frame.add("mesaDeJuego", mesaDeJuego);
    }

}
