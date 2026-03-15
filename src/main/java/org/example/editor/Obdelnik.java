package org.example.editor;
import java.awt.*;

public class Obdelnik extends AbstractGraphObject {
    private int velikost1;
    private int velikost2;


    public Obdelnik(Point pozice, Color barva, int velikost1, int velikost2) {
        super(pozice, barva);
        this.velikost1 = velikost1;
        this.velikost2 = velikost2;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(barva);
        g.fillRect(pozice.x, pozice.y, velikost1, velikost2);
    }

    @Override
    public boolean obsahuje(int x, int y) {
        return x >= pozice.x && x <= pozice.x + velikost1 &&
                y >= pozice.y && y <= pozice.y + velikost2;
    }
}