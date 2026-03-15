package org.example.editor;
import java.awt.*;

public abstract class AbstractGraphObject {
    protected Point pozice;
    protected Color barva;


    //Atributy
    public AbstractGraphObject(Point pozice, Color barva) {
        this.pozice = pozice;
        this.barva = barva;
    }

    //Getry, Setry
    public Point getPozice() { return pozice; }
    public void setPozice(Point pozice) { this.pozice = pozice; }
    public Color getBarva() { return barva; }
    public void setBarva(Color barva) { this.barva = barva; }


    //Abstraktní metody
    public abstract void draw(Graphics2D g);
    public abstract boolean obsahuje(int x, int y);
}
