package nqueen;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Board extends JPanel {

    private int size = 8;
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        boolean white = true;
        int squareSize = 50;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                g.setColor(white ? Color.white : Color.black);
                g.fillRect(col * squareSize, row * squareSize, squareSize, squareSize);
                white = !white;
            }
            if (size % 2 == 0) white = !white;
        }
    }

    public void initializeState() {
    }

    public void runAlgorithm(HashMap<String, String> data) {
    }

    public void setBoardSize(int value) {
        this.size = value;
        this.repaint();
    }
}
