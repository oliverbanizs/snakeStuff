import java.awt.*;

import javax.swing.*;

public class Main extends JFrame {

    public Main() {
        add(new Canvas());
        setResizable(false);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Main();
                frame.setVisible(true);
            }
        });
    }
}