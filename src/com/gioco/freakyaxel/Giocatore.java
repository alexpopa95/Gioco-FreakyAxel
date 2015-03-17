/*
 *****************************************
 *          2014(c) Project by           *
 *                                       *
 *         Popa George Alexandru         *
 *      Email: freakyaxel@gmail.com      *
 *****************************************
 */
package com.gioco.freakyaxel;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author freakyaxel
 */
public class Giocatore {
    private int x;
    private int y;
    private int larghezza;
    private int altezza;
    private final int velocita = 30;
    BufferedImage img_ombrello;
    Gioco main;
    
    public Giocatore() {}
    public Giocatore(BufferedImage image, int x, int larghezza, int altezza, Gioco main) {
        this.x = x;
        this.altezza = altezza;
        this.larghezza = larghezza;
        this.img_ombrello = image;
        y = 400;
        this.main = main;
    }
    
    public void disegna(Graphics g) {
        g.drawImage(img_ombrello, x, y, larghezza, altezza, null);
    }
    
    public void setX(int valore) {
        this.x = valore;
    }
    
    public void setY(int valore) {
        this.y = valore;
    }
    
    public void setLarghezza(int valore) {
        this.larghezza = valore;
    }
    
    public void setAltezza(int valore) {
        this.altezza = valore;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getLarghezza() {
        return larghezza;
    }
    
    public int getAltezza() {
        return altezza;
    }
    
    public void spostaDestra() {
        if((x+larghezza)<main.getLarghezza()) {
            x += velocita;
        }
    }
    
    public void spostaSinistra() {
        if(x>0) {
            x -= velocita;
        }
    }
    
    public Rectangle getBordi() {
        return new Rectangle(x, y, larghezza, altezza);
    }
}
