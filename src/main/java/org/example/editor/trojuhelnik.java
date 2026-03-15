package org.example.editor;

import java.awt.*;

public class trojuhelnik extends AbstractGraphObject {
    private int velikost;

    public trojuhelnik(Point pozice, Color barva, int velikost) {
        super(pozice, barva);
        this.velikost = velikost;
    }

    // Pomocná metoda pro vytvoření tvaru rovnostranného trojúhelníku
    private Polygon vytvorPolygon() {
        // Výpočet výšky rovnostranného trojúhelníku: v = a * (odmocnina(3) / 2)
        int vyska = (int) (velikost * Math.sqrt(3) / 2);

        // Definice 3 bodů (vrchol nahoře uprostřed, levý dolní, pravý dolní)
        int[] bodyX = { pozice.x + velikost / 2, pozice.x, pozice.x + velikost};
        int[] bodyY = { pozice.y, pozice.y + vyska, pozice.y + vyska };

        return new Polygon(bodyX, bodyY, 3);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(barva);
        Polygon p = vytvorPolygon();
        g.fillPolygon(p);
    }

    @Override
    public boolean obsahuje(int x, int y) {
        Polygon p = vytvorPolygon();
        return p.contains(x, y); // Využijeme hotovou metodu třídy Polygon
    }
}