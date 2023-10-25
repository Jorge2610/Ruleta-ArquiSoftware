package Logica;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Historial {

    private DefaultTableModel modeloTabla;
    private JTable tabla;

    public Historial() {
        initModelo();
        initTabla();
    }

    private void initModelo() {
        String[] nombresColumnas = { "Turno", "Nro Ganador", "Apuesta", "Pago", "Balance", "Saldo" };
        Object[][] datos = {};
        modeloTabla = new DefaultTableModel(datos, nombresColumnas);
    }

    private void initTabla() {
        tabla = new JTable(modeloTabla);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(4).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);
        tabla.getTableHeader().setFont(new Font("sansserif", Font.PLAIN, 15));
        tabla.setFont(new Font("sansserif", Font.PLAIN, 15));
    }

    public void insertTurno(int turno, String nroGanador, int apuesta, int pago, int saldo) {
        Object[] row = new Object[] { turno, nroGanador, apuesta, pago, pago - apuesta, saldo };
        modeloTabla.addRow(row);
    }

    public JTable getTable() {
        return tabla;
    }

}
