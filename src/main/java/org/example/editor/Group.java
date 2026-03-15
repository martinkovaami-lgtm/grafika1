package org.example.editor;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Group extends AbstractGraphObject {
    private List<AbstractGraphObject> objekty;

    public Group(Point pozice, Color barva) {
        super(pozice, barva);
        this.objekty = new ArrayList<>();
    }

    public void pridajObjekt(AbstractGraphObject obj) {
        objekty.add(obj);
    }

    @Override
    public void draw(Graphics2D g) {
        for (AbstractGraphObject obj : objekty) {
            obj.draw(g);
        }
    }

    @Override
    public boolean obsahuje(int x, int y) {
        for (AbstractGraphObject obj : objekty) {
            if (obj.obsahuje(x, y)) return true;
        }
        return false;
    }
}