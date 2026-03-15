package org.example.editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

public class EditorFrame extends JFrame {
    private DrawingPanel panel;
    private List<AbstractGraphObject> objekty;

    // Proměnná pro zvolený nástroj
    private String aktualniNastroj = "Ctverec";

    // Nové proměnné pro třetí commit (tažení objektů)
    private AbstractGraphObject vybranyObjekt = null;
    private Point pocatecniBodTazeni = null;

    public EditorFrame() {
        setTitle("Vektorový editor");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new DrawingPanel();
        add(panel, BorderLayout.CENTER);

        initToolbar();
        initTestData();
    }

    private void initToolbar() {
        JToolBar toolBar = new JToolBar();
        ButtonGroup bg = new ButtonGroup();

        JToggleButton btnCtverec = new JToggleButton("Čtverec", true);
        JToggleButton btnKruznice = new JToggleButton("Kružnice");
        JToggleButton btnObdelnik = new JToggleButton("Obdélník");
        JToggleButton btnTrojuhelnik = new JToggleButton("Trojúhelník");

        // Nové tlačítko pro Výběr
        JToggleButton btnVyber = new JToggleButton("Výběr / Šipka");

        bg.add(btnCtverec);
        bg.add(btnKruznice);
        bg.add(btnObdelnik);
        bg.add(btnTrojuhelnik);
        bg.add(btnVyber);

        btnCtverec.addActionListener(e -> aktualniNastroj = "Ctverec");
        btnKruznice.addActionListener(e -> aktualniNastroj = "Kruznice");
        btnObdelnik.addActionListener(e -> aktualniNastroj = "Obdelnik");
        btnTrojuhelnik.addActionListener(e -> aktualniNastroj = "Trojuhelnik");

        // Akce pro nový nástroj
        btnVyber.addActionListener(e -> aktualniNastroj = "Vyber");

        toolBar.add(btnCtverec);
        toolBar.add(btnKruznice);
        toolBar.add(btnObdelnik);
        toolBar.add(btnTrojuhelnik);
        toolBar.addSeparator(); // Malá mezera pro oddělení šipky od tvarů
        toolBar.add(btnVyber);

        add(toolBar, BorderLayout.NORTH);

        // Zde jsme upravili chování myši pro kliknutí (MouseListener)
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point p = e.getPoint();

                // Pokud máme zapnutý nástroj "Výběr"
                if (aktualniNastroj.equals("Vyber")) {
                    /*
                     * Procházíme seznam tvarů od konce. Proč od konce?
                     * Protože tvar, který byl přidán jako poslední, se kreslí úplně nahoře.
                     * Takže pokud se tvary překrývají, chceme chytit ten horní.
                     */
                    for (int i = objekty.size() - 1; i >= 0; i--) {
                        if (objekty.get(i).obsahuje(p.x, p.y)) { // Použití metody obsahuje(x,y)
                            vybranyObjekt = objekty.get(i); // Zapamatujeme si ho
                            pocatecniBodTazeni = p;         // Zapamatujeme si, kde jsme začali tahat
                            break; // Jakmile jeden najdeme, nepotřebujeme hledat dál
                        }
                    }
                } else {
                    // Jinak prostě jen nakreslíme nový tvar tak jako předtím
                    if (aktualniNastroj.equals("Ctverec")) {
                        objekty.add(new Ctverec(p, Color.CYAN, 50));
                    } else if (aktualniNastroj.equals("Kruznice")) {
                        objekty.add(new Kruznice(p, Color.ORANGE, 50));
                    } else if (aktualniNastroj.equals("Obdelnik")) {
                        objekty.add(new Obdelnik(p, Color.MAGENTA, 60, 40));
                    } else if (aktualniNastroj.equals("Trojuhelnik")) {
                        objekty.add(new trojuhelnik(p, Color.PINK, 50));
                    }
                    panel.repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Když uživatel pustí tlačítko myši, přestaneme objekt tahat
                vybranyObjekt = null;
            }
        });

        // Nově jsme přidali MouseMotionListener, který reaguje na tažení (pohyb se stisknutým tlačítkem)
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Pokud máme nástroj Výběr a skutečně držíme nějaký objekt...
                if (aktualniNastroj.equals("Vyber") && vybranyObjekt != null) {

                    // 1. Spočítáme rozdíl, o kolik pixelů jsme myší pohnuli
                    int dx = e.getX() - pocatecniBodTazeni.x;
                    int dy = e.getY() - pocatecniBodTazeni.y;

                    // 2. Vezmeme aktuální pozici drženého objektu
                    Point staraPozice = vybranyObjekt.getPozice();

                    // 3. Objektu nastavíme zbrusu novou pozici, posunutou o vypočtený rozdíl
                    vybranyObjekt.setPozice(new Point(staraPozice.x + dx, staraPozice.y + dy));

                    // 4. Přepíšeme počáteční bod na aktuální místo, abychom pro další kousek pohybu nepočítali posun od začátku
                    pocatecniBodTazeni = e.getPoint();

                    // 5. Překreslíme plátno
                    panel.repaint();
                }
            }
        });
    }

    private void initTestData() {
        objekty = new ArrayList<>();
        objekty.add(new Ctverec(new Point(50, 50), Color.RED, 50));
        objekty.add(new Kruznice(new Point(150, 50), Color.BLUE, 50));
        objekty.add(new Obdelnik(new Point(250, 50), Color.GREEN, 50, 60));
        objekty.add(new trojuhelnik(new Point(350, 50), Color.YELLOW, 50));
        panel.setObjekty(objekty);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditorFrame().setVisible(true));
    }
}