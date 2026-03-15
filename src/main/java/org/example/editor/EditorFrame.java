package org.example.editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class EditorFrame extends JFrame {
    private DrawingPanel panel;
    private List<AbstractGraphObject> objekty;

    // Proměnná, která si pamatuje, co máme zrovna vybráno na liště
    private String aktualniNastroj = "Ctverec";

    public EditorFrame() {
        setTitle("Vektorový editor");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new DrawingPanel();
        add(panel, BorderLayout.CENTER); // Centrální část

        initToolbar(); // Volání metody pro vytvoření nástrojové lišty
        initTestData(); // Volání metody pro testovací data
    }

    private void initToolbar() {
        JToolBar toolBar = new JToolBar();
        ButtonGroup bg = new ButtonGroup(); // Slouží k tomu, aby šlo zmáčknout vždy jen jedno tlačítko

        // Vytvoření přepínacích tlačítek pro tvary
        JToggleButton btnCtverec = new JToggleButton("Čtverec", true); // true = předvybráno
        JToggleButton btnKruznice = new JToggleButton("Kružnice");
        JToggleButton btnObdelnik = new JToggleButton("Obdélník");
        JToggleButton btnTrojuhelnik = new JToggleButton("Trojúhelník");

        // Přidání do logické skupiny
        bg.add(btnCtverec);
        bg.add(btnKruznice);
        bg.add(btnObdelnik);
        bg.add(btnTrojuhelnik);

        // Nastavení akcí - co se stane, když na tlačítko klikneme
        btnCtverec.addActionListener(e -> aktualniNastroj = "Ctverec");
        btnKruznice.addActionListener(e -> aktualniNastroj = "Kruznice");
        btnObdelnik.addActionListener(e -> aktualniNastroj = "Obdelnik");
        btnTrojuhelnik.addActionListener(e -> aktualniNastroj = "Trojuhelnik");

        // Fyzické přidání tlačítek do grafické lišty
        toolBar.add(btnCtverec);
        toolBar.add(btnKruznice);
        toolBar.add(btnObdelnik);
        toolBar.add(btnTrojuhelnik);

        add(toolBar, BorderLayout.NORTH); // Vložení lišty na horní okraj okna

        // Obsluha kliknutí myší na kreslící plochu
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point p = e.getPoint(); // Získáme souřadnice, kam uživatel klikl

                // Podle zvoleného nástroje vytvoříme konkrétní tvar a přidáme ho do seznamu
                if (aktualniNastroj.equals("Ctverec")) {
                    objekty.add(new Ctverec(p, Color.CYAN, 50));
                } else if (aktualniNastroj.equals("Kruznice")) {
                    objekty.add(new Kruznice(p, Color.ORANGE, 50));
                } else if (aktualniNastroj.equals("Obdelnik")) {
                    objekty.add(new Obdelnik(p, Color.MAGENTA, 60, 40));
                } else if (aktualniNastroj.equals("Trojuhelnik")) {
                    objekty.add(new trojuhelnik(p, Color.PINK, 50));
                }

                // Řekneme panelu, ať se překreslí, aby byl nový objekt hned vidět
                panel.repaint();
            }
        });
    }

    private void initTestData() {
        objekty = new ArrayList<>();
        objekty.add(new Ctverec(new Point(50, 50), Color.RED, 50));
        objekty.add(new Kruznice(new Point(150, 50), Color.BLUE, 50));
        objekty.add(new Obdelnik(new Point(250, 50), Color.GREEN, 60, 40));
        objekty.add(new trojuhelnik(new Point(350, 50), Color.YELLOW, 50));
        panel.setObjekty(objekty);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditorFrame().setVisible(true));
    }
}