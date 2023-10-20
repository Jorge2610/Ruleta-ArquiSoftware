import javax.swing.SwingUtilities;
import com.formdev.flatlaf.FlatDarkLaf;
import GUI.CasinoRoyale;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FlatDarkLaf.setup();
                new CasinoRoyale();
            }
        });
    }
}