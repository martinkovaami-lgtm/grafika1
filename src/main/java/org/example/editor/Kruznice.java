package org.example.editor;
import java.awt.*;

public class Kruznice extends AbstractGraphObject {
    private int velikost;

    public Kruznice(Point pozice, Color barva, int velikost) {
        super(pozice, barva);
        this.velikost = velikost;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(barva);
        g.fillOval(pozice.x, pozice.y, velikost, velikost);
    }

    @Override
    public boolean obsahuje(int x, int y) {
        return x >= pozice.x && x <= pozice.x + velikost && y >= pozice.y && y <= pozice.y + velikost;}
}