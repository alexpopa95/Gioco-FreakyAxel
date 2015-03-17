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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author freakyaxel
 */
public class Topo extends Thread {
    private int x;
    private int y;
    private int larghezza;
    private int altezza;
    private boolean attivo;
    private int velocita = -1;
    private final int max_velocita = 15;
    BufferedImage img_topo;
    private Gioco main;
    public int vita;
    
    public Topo(BufferedImage image, int larghezza, int altezza, int x, int y, Gioco main) {
        this.x = x;
        this.y = y;
        this.altezza = altezza;
        this.larghezza = larghezza;
        this.img_topo = image;
        attivo = true;
        this.main = main;
        vita = 100;
    }
    
    @Override
    public void run() {
        attivo = true;
        while(attivo) {
            aggiorna();
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Topo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void aggiorna(){
        //aggiorna
        Random rnd = new Random();
        if(this.x <= 0) {
            velocita = rnd.nextInt(max_velocita) + 1;
        }
        if(this.x >= main.getLarghezza()-this.larghezza) {
            velocita = rnd.nextInt(max_velocita) + 1;
            velocita *= -1;
        }
        x += velocita;
    }
    
    public Rectangle getBordi() {
        return new Rectangle(x, y, larghezza, altezza);
    }
    
    public void disegna(Graphics g) {
        g.drawImage(img_topo, x, y, larghezza, altezza, main);
    }
    
    public boolean getAttivo() {
        return attivo;
    }
    
    public void setAttivo(boolean valore) {
        this.attivo = valore;
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
}
