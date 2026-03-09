package cz.uhk.kalkulacka;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class gui extends JFrame {

    private JTextField tfPrvniCislo;
    private JTextField tfDruheCislo;
    private JLabel lbVysledek;

    public gui() {
        setTitle("pridat okna s pocetni operaci");
        setSize(800, 600);
        initGui();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initGui() {
        tfPrvniCislo = new JTextField("0", 8);
        tfDruheCislo = new JTextField("0", 8);
        lbVysledek = new JLabel("0");

        // Oprava: přejmenováno z panel1 na pnlSever
        JPanel pnlSever = new JPanel();

        pnlSever.add(tfPrvniCislo);
        pnlSever.add(new JLabel("+"));
        pnlSever.add(tfDruheCislo);

        // Oprava překlepu v btRovnaSe
        JButton btRovnaSe = new JButton("=");
        pnlSever.add(btRovnaSe);
        pnlSever.add(lbVysledek);

        add(pnlSever, BorderLayout.NORTH);

        btRovnaSe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Bezpečné načtení a převod obou čísel najednou
                    double prvniDouble = Double.parseDouble(tfPrvniCislo.getText());
                    double druheDouble = Double.parseDouble(tfDruheCislo.getText());

                    double vysledek = prvniDouble + druheDouble;
                    lbVysledek.setText(String.valueOf(vysledek));

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(gui.this, "Zadejte platná čísla!");
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Graphics2D g = (Graphics2D) getGraphics();
                g.fillOval(e.getX() - 5, e.getY() - 5, 10, 10);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gui okno = new gui();
                okno.setVisible(true); // Oprava: přidáno zviditelnění okna
            }
        });
    }
}