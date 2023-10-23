package GUI;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FichasGUI extends JPanel {

    private MesaDeJuego mesaDeJuego;
    private int ancho;
    private int alto;
    private JPanel probabilidades;
    private JPanel fichas;
    private JPanel controlPartida;

    public FichasGUI(int ancho, int alto, MesaDeJuego mesaDeJuego) {
        super(new GridBagLayout());
        setPreferredSize(new Dimension(ancho, alto));
        this.mesaDeJuego = mesaDeJuego;
        this.ancho = ancho;
        this.alto = alto;
        initComponents();
    }

    private void initComponents() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;

        c.gridx = 0;
        c.gridy = 2;
        initProbabilidades();
        add(probabilidades, c);

        c.gridx = 1;
        initFichas();
        add(fichas, c);

        c.gridx = 2;
        initControlPartida();
        add(controlPartida, c);
    }

    private void initProbabilidades() {
        probabilidades = new JPanel(new GridBagLayout());
        probabilidades.setPreferredSize(new Dimension((int)(ancho * 0.2), alto));
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        JLabel titulo = new JLabel("Probabilidades");
        probabilidades.add(titulo, c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        JLabel rojo = new JLabel("Rojo: ");
        probabilidades.add(rojo, c);

        c.gridx = 1;
        JLabel negro = new JLabel("negro: ");
        probabilidades.add(negro, c);

        c.gridx = 0;
        c.gridy = 2;
        JLabel par = new JLabel("Par: ");
        probabilidades.add(par, c);

        c.gridx = 1;
        JLabel impar = new JLabel("impar: ");
        probabilidades.add(impar, c);
    }

    private void initFichas() {
        fichas = new JPanel();
        fichas.setPreferredSize(new Dimension((int)(ancho * 0.6), alto));
        JButton[] botonesFichas = new JButton[5];
        try {
            for (int i = 0; i < botonesFichas.length; i++) {
                botonesFichas[i] = new JButton();
                initFicha(botonesFichas[i], i);
                fichas.add(botonesFichas[i]);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
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
            mesaDeJuego.setCursor(cur);
            mesaDeJuego.setValorApuesta(valoresFicha[i]);
        });
        mesaDeJuego.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent me) { }
            public void mouseReleased(MouseEvent me) { }
            public void mouseEntered(MouseEvent me) { }
            public void mouseExited(MouseEvent me) { }
            public void mouseClicked(MouseEvent me) { 
              if(me.getButton() == MouseEvent.BUTTON3) {
                mesaDeJuego.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                mesaDeJuego.setValorApuesta(0);
              }
            }
        });
    }

    private void initControlPartida() {
        GridBagConstraints cons = new GridBagConstraints();
        controlPartida = new JPanel(new GridBagLayout());
        controlPartida.setPreferredSize(new Dimension((int)(ancho * 0.2), alto));
        JButton girarRuleta = new JButton("Girar ruleta");
        girarRuleta.addActionListener(e -> {
            mesaDeJuego.girarRuleta();
        });
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
    }

}
