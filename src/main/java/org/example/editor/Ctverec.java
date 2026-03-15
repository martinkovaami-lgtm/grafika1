package org.example.editor;
import java.awt.*;

public class Ctverec extends AbstractGraphObject{
    private int velikost;

    public Ctverec(Point pozice, Color barva, int velikost){
        super(pozice, barva);
        this.velikost=velikost;
    }

    //Abstraktní metody
    @Override
    public void draw(Graphics2D g) {
        g.setColor(barva);
        g.fillRect(pozice.x, pozice.y, velikost, velikost);
    }

    @Override
    public boolean obsahuje(int x, int y) {
        return x>= pozice.x && x <= pozice.x + velikost && y>= pozice.y && y <= pozice.y + velikost;
    }
}
