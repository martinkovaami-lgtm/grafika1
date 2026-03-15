package org.example.editor;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class DrawingPanel extends JPanel {
    private List<AbstractGraphObject> objekty = new ArrayList<>();

    public void setObjekty(List<AbstractGraphObject> objekty) {
        this.objekty = objekty;
        repaint();
    }

    public List<AbstractGraphObject> getObjekty() {
        return objekty;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (AbstractGraphObject obj : objekty) {
            obj.draw(g2d);
        }
    }
}