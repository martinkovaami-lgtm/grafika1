package org.example.editor;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EditorFrame extends JFrame {
    private DrawingPanel panel;
    private List<AbstractGraphObject> objekty;

    public EditorFrame() {
        setTitle("Vektorový editor");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new DrawingPanel();
        add(panel, BorderLayout.CENTER); // Centrální část [cite: 19]

        initTestData(); // Volání metody pro testovací data [cite: 20]
    }

    private void initTestData() {
        objekty = new ArrayList<>();
        objekty.add(new Ctverec(new Point(50, 50), Color.RED, 50));
        objekty.add(new Kruznice(new Point(150, 50), Color.BLUE, 50));
        objekty.add(new Obdelnik(new Point(250, 50), Color.GREEN, 50, 60));
        objekty.add(new trojuhelnik(new Point(350,50), Color.YELLOW, 50));
    panel.setObjekty(objekty);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditorFrame().setVisible(true));
    }
}