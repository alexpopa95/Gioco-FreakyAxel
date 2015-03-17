/*
*****************************************
*          2014(c) Project by           *
*                                       *
*         Popa George Alexandru         *
*      Email: freakyaxel@gmail.com      *
*****************************************
*/

package com.gioco.freakyaxel;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author freakyaxel
 */
public class Gioco extends Canvas implements Runnable, KeyListener, MouseMotionListener {
    
    private static final int larghezza = 1280;
    private static final int altezza = 720;
    private static final String nome_gioco = "Gioco FreakyAxel";
    
    BufferedImage sfondo = null;
    BufferedImage topo = null;
    BufferedImage ombrello = null;
    BufferedImage goccia = null;
    
    private boolean giocoAttivo = false;
    private Topo ogg_topo;
    private Giocatore giocatore;
    private Pioggia pioggia;
    
    public Gioco() {
        caricaRisorse();
        iniziaGioco();
    }

    public static void main(String[] args) {
        
        Gioco gioco = new Gioco();
        
        JFrame finestra_gioco = new JFrame(nome_gioco);
        
        Dimension dimensione_finestra = new Dimension(larghezza, altezza);
        finestra_gioco.setPreferredSize(dimensione_finestra);
        finestra_gioco.setMaximumSize(dimensione_finestra);
        
        finestra_gioco.pack();
        finestra_gioco.setResizable(false);
        finestra_gioco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestra_gioco.setVisible(true);
        
        finestra_gioco.add(gioco);
        
        gioco.addKeyListener(gioco);
        gioco.addMouseMotionListener(gioco);
        
        Thread thread_gioco = new Thread(gioco);
        thread_gioco.start();
    }
    
    private void iniziaGioco() {
        giocatore = new Giocatore(ombrello, 0, 250, 250, this);
        ogg_topo = new Topo(topo, 150, 75, 100, 600, this);
        ogg_topo.start();
        pioggia = new Pioggia(goccia, 5, 500, this);
        pioggia.start();
    }
    
    private void caricaRisorse() {
        CaricatoreImmagini loader = new CaricatoreImmagini();
        sfondo = loader.caricaImmagine("/immagini/sfondo.png");
        topo = loader.caricaImmagine("/immagini/topo.png");
        ombrello = loader.caricaImmagine("/immagini/ombrello.png");
        goccia = loader.caricaImmagine("/immagini/goccia.png");
        System.out.println("Risorse caricate!");
    }
    
    private void disegna() {
        BufferStrategy buffer = this.getBufferStrategy();
        if(buffer == null) {
            createBufferStrategy(2);
            return;
        }
        
        Graphics g = buffer.getDrawGraphics();
        
        g.drawImage(sfondo, 0, 0, larghezza, altezza, this);
        
        ogg_topo.disegna(g);
        giocatore.disegna(g);
        pioggia.disegna(g);
        
        g.drawString("Vita: "+ogg_topo.vita, 25, 25);
        
        if(!giocoAttivo) {
            g.setColor(Color.gray);
            g.clearRect(0, 0, larghezza, altezza);
            g.setColor(Color.red);
            g.drawString("HAI PERSO", 360, 640);
        }
        
        g.dispose();
        buffer.show();
    }
    
    private void aggiorna() {
        ArrayList<Goccia> gocce = pioggia.getGocce();
        for(Goccia goccia : gocce) {
            if(GestoreCollisioni.controllaCollisione(giocatore, goccia)) {
                gocce.remove(goccia);
                break;
            }
            if(GestoreCollisioni.controllaCollisioneTopo(ogg_topo, goccia)) {
                gocce.remove(goccia);
                ogg_topo.vita -= 5;
                break;
            }
        }
        if(controllaSconfitta()) {
            this.giocoAttivo = false;
            disegna();
        }
    }
    
    private boolean controllaSconfitta() {
        if(ogg_topo.vita <= 0) {
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        giocoAttivo = true;
        while(giocoAttivo) {
            aggiorna();
            disegna();
        }
    }
    
    public int getLarghezza() {
        return larghezza;
    }
    
    public int getAltezza() {
        return altezza;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        switch(keycode) {
            case KeyEvent.VK_LEFT:
                giocatore.spostaSinistra();
                break;
            case KeyEvent.VK_RIGHT:
                giocatore.spostaDestra();
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        int posizione = (e.getPoint().x)-(giocatore.getLarghezza()/2);
        
        if(posizione >= 0 && (posizione+giocatore.getLarghezza()) <= larghezza) {
            giocatore.setX(posizione);
        }
    }
}
