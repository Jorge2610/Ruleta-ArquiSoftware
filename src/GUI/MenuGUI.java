package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Logica.MultiLineCellRenderer;

public class MenuGUI extends JPanel {

    private CasinoRoyale casino;
    private ImageIcon backgroundImage;
    private final int ANCHO = 880;
    private final int ALTO = 750;

    public MenuGUI(CasinoRoyale casino) {
        super(new GridBagLayout());
        setPreferredSize(new Dimension(ANCHO, ALTO));
        backgroundImage = new ImageIcon(getClass().getResource("/Recursos/inicio.png"));
        this.casino = casino;
        initPaneles();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, getPreferredSize().width + 20, getPreferredSize().height, this);
    }

    private void initPaneles() {
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel left = new JPanel();
        left.setOpaque(false);
        left.setPreferredSize(new Dimension(ANCHO / 2, ALTO));
        initLeftPanel(left);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(left, gbc);

        JPanel right = new JPanel();
        right.setOpaque(false);
        right.setPreferredSize(new Dimension(ANCHO / 2, ALTO));
        gbc.gridx = 1;
        add(right, gbc);
    }

    private void initLeftPanel(JPanel left) {
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel top = new JPanel(new GridBagLayout());
        top.setOpaque(false);
        top.setPreferredSize(new Dimension(ANCHO / 2, (int) (ALTO * 0.3)));
        JLabel logo = new JLabel();
        logo.setPreferredSize(new Dimension(250, 250));
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/Recursos/casino.png"));
        Icon icono = new ImageIcon(logoIcon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
        logo.setIcon(icono);
        gbc.gridx = 0;
        gbc.gridy = 0;
        top.add(logo, gbc);
        left.add(top, gbc);

        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        center.setPreferredSize(new Dimension(ANCHO / 2, (int) (ALTO * 0.45)));
        initCenterPanel(center);
        gbc.gridy = 1;
        left.add(center, gbc);

        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.setPreferredSize(new Dimension(ANCHO / 2, (int) (ALTO * 0.25)));
        gbc.gridy = 2;
        left.add(bottom, gbc);
    }

    private void initCenterPanel(JPanel center) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.ipadx = 10;
        gbc.ipady = 10;

        JLabel bienvenida = new JLabel("Bienvenido...");
        bienvenida.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 45));
        bienvenida.setForeground(new Color(210, 210, 210));
        gbc.gridx = 0;
        gbc.gridy = 0;
        center.add(bienvenida, gbc);

        JLabel separador = new JLabel("");
        separador.setFont(new Font("Arial", Font.PLAIN, 25));
        gbc.gridy = 1;
        center.add(separador, gbc);

        JButton iniciarPartida = new JButton("Iniciar partida");
        iniciarPartida.setFont(new Font("Arial", Font.PLAIN, 25));
        iniciarPartida.addActionListener(e -> {
            casino.cambiarCard("usuario");
        });
        gbc.gridy = 2;
        center.add(iniciarPartida, gbc);

        JButton reglas = new JButton("Ver reglas");
        reglas.setFont(new Font("Arial", Font.PLAIN, 25));
        reglas.addActionListener(e -> {
            mostrarReglas();
        });
        gbc.gridy = 3;
        center.add(reglas, gbc);

        JButton apuestas = new JButton("Ver apuestas");
        apuestas.setFont(new Font("Arial", Font.PLAIN, 25));
        apuestas.addActionListener(e -> {
            JScrollPane scroll = new JScrollPane(getTablaApuestas());
            JPanel panel = new JPanel();
            scroll.setPreferredSize(new Dimension(800, 180));
            panel.add(scroll);
            JOptionPane.showMessageDialog(this, panel, "Apuestas y pagos",
                    JOptionPane.PLAIN_MESSAGE);
        });
        gbc.gridy = 4;
        center.add(apuestas, gbc);

        JButton salir = new JButton("Salir");
        salir.setFont(new Font("Arial", Font.PLAIN, 25));
        salir.addActionListener(e -> {
            System.exit(0);
        });
        gbc.gridy = 5;
        center.add(salir, gbc);
    }

    private void mostrarReglas() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 400));
        JTextArea reglasA = new JTextArea();
        String reglas = getReglas();
        reglasA.setText(reglas);
        reglasA.setOpaque(false);
        reglasA.setEditable(false);
        panel.add(reglasA);
        JOptionPane.showMessageDialog(this, panel, "Reglas y Apuestas",
                JOptionPane.PLAIN_MESSAGE);
    }

    private String getReglas() {
        String reglas = "\t\tREGLAS\n\n" +
                "1.- Mesa de juego: La ruleta americana se juega en una mesa con un disco giratorio llamado\n   rueda de la ruleta y un tapete con números y apuestas.\n\n"
                +
                "2.- Números: La rueda de la ruleta americana tiene 38 casillas numeradas del 1 al 36, además\n   de un \"0\" (cero) y un \"00\" (doble cero).\n\n"
                +
                "3.- Apuestas: Los jugadores pueden realizar una variedad de apuestas en la mesa, que incluyen\n   apuestas a números individuales, grupos de números, colores (rojo o negro), pares o impares\n\n"
                +
                "4.- Giro ruleta: El boton \"Girar ruleta\" hace girar la rueda de la ruleta y el número obtenido\n   cuando la rueda se detiene determina el resultado del giro, el cual se muestra en pantalla.\n\n"
                +
                "5.- Ganancias: Las ganancias se otorgan según el tipo de apuesta realizada. Todas las apuestas\n   tienen pagos específicos.\n\n"
                +
                "6.- Próximo giro: Una vez que se hayan pagado las ganancias de la ronda anterior, los jugadores\n   pueden realizar nuevas apuestas para el próximo giro de la rueda.\n\n"
                +
                "7.- Historial: Se puede ver los datos historicos de turnos anteriores en cualquier momento.";
        return reglas;
    }

    private JTable getTablaApuestas() {
        JTable tabla = new JTable(getModeloApuestas());
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(600);
        tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabla.getTableHeader().setFont(new Font("sansserif", Font.PLAIN, 14));
        tabla.setFont(new Font("sansserif", Font.PLAIN, 14));
        tabla.getColumnModel().getColumn(1).setCellRenderer(new MultiLineCellRenderer());
        tabla.setEnabled(false);
        return tabla;
    }

    private DefaultTableModel getModeloApuestas() {
        String[] nombresColumnas = { "Apuesta", "Se juega a", "Premio" };
        Object[][] datos = { { "Rojo/Negro",
                "Se apuesta al color del número ganador, si será rojo o negro.",
                "1 x 1" },
                { "Par/Impar",
                        "Se apuesta a si el número ganador será par o impar.",
                        "1 x 1" },
                { "Pasa/Falta",
                        "Se trata de apostar si el número estará comprendido entre los números del 1 al 18 (falta) o entre los números del 19 al 36 (pasa).",
                        "1 x 1" },
                { "Docena",
                        "Se trata de apostar en que docena estará el número ganador. El tapete se divide en 3 docenas, cada una de ellas abarca 12 números.",
                        "2 x 1" },
                { "Fila",
                        "Se trata de apostar en que fila estará el número ganador. El tapete se divide en 3 filas, cada una de ellas alberga 12 números.",
                        "2 x 1" },
                { "Transversal",
                        "Se trata de apostar a 3 números, con la apuesta se apuesta a los 3 números de una columna.",
                        "11 x 1" },
                { "Pleno", "Se trata de apostar a un solo número.", "35 x 1" } };
        DefaultTableModel modeloTabla = new DefaultTableModel(datos, nombresColumnas);
        return modeloTabla;
    }
}
