/*
 *****************************************
 *          2014(c) Project by           *
 *                                       *
 *         Popa George Alexandru         *
 *      Email: freakyaxel@gmail.com      *
 *****************************************
 */
package com.gioco.freakyaxel;

/**
 *
 * @author freakyaxel
 */
public class GestoreCollisioni {
    
    public static boolean controllaCollisione(Giocatore ombrello, Goccia goccia) {
        return ombrello.getBordi().intersects(goccia.getBordi());
    }
    
    public static boolean controllaCollisioneTopo(Topo topo, Goccia goccia) {
        return topo.getBordi().intersects(goccia.getBordi());
    }
}
