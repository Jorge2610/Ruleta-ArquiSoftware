package GUI;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Logica.Jugador;

public class CasinoRoyale {

    private JFrame frame;
    private JPanel panel;
    private JPanel menu;
    private UsuarioGUI usuario;
    private MesaDeJuego mesaDeJuego;

    public CasinoRoyale() {
        frame = new JFrame("Casino Royale");
        panel = new JPanel(new CardLayout());
        initComponents();
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initComponents(){
        usuario = new UsuarioGUI(this);
        panel.add("usuario", usuario);

        mesaDeJuego = new MesaDeJuego();
        panel.add("mesaDeJuego", mesaDeJuego);
    }

    public void cambiarCard(String card){
        CardLayout cl = (CardLayout)(panel.getLayout());
        cl.show(panel, card);
    }

    public void setJugador(Jugador jugador){
        mesaDeJuego.setJugador(jugador);
    }
}
