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
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author freakyaxel
 */
public class Pioggia extends Thread {
    private int numero;
    private int attesa;
    BufferedImage img_goccia;
    Gioco main;
    private boolean piove;
    private ArrayList<Goccia> gocce;
    private final int maxVel = 15;
    Random rand;
    
    public Pioggia(BufferedImage image, int numero, int attesa, Gioco main) {
        this.img_goccia = image;
        this.attesa = attesa;
        this.numero = numero;
        this.main = main;
        gocce = new ArrayList();
        rand = new Random();
    }
    
    @Override
    public void run() {
        piove = true;
        while(piove) {
            for(int i=0;i<numero;i++) {
                gocce.add(new Goccia(img_goccia, 20, 50, rand.nextInt(main.getLarghezza()), -50, rand.nextInt(maxVel)+2, main));
            }
            try {
                Thread.sleep(attesa);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pioggia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void disegna(Graphics g) {
        for(int i=0;i<gocce.size();i++) {
            Goccia temp = gocce.get(i);
            temp.disegna(g);
        }
    }
    
    public ArrayList getGocce() {
        return gocce;
    }
}
