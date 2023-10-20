package GUI;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TimerRuleta extends Thread {

    private JLabel ruletaDisplay;
    private int sleepTime;

    public TimerRuleta(JLabel ruletaDisplay, int sleepTime){
        this.ruletaDisplay = ruletaDisplay;
        this.sleepTime = sleepTime;
    }

    public void run() {
        try {
            Thread.sleep(sleepTime);
            ImageIcon ruletaIcon = new ImageIcon(getClass().getResource("/Recursos/ruleta.png"));
            Icon icono = new ImageIcon(ruletaIcon.getImage().getScaledInstance(ruletaDisplay.getPreferredSize().width - 5, ruletaDisplay.getPreferredSize().height - 5, Image.SCALE_DEFAULT));
            ruletaDisplay.setIcon(icono);
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}
