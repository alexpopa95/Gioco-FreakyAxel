/*
 *****************************************
 *          2014(c) Project by           *
 *                                       *
 *         Popa George Alexandru         *
 *      Email: freakyaxel@gmail.com      *
 *****************************************
 */
package com.gioco.freakyaxel;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author freakyaxel
 */
public class CaricatoreImmagini {
    BufferedImage image;
    
    public BufferedImage caricaImmagine(String posizione) {
        try {
            image = ImageIO.read(getClass().getResource(posizione));
        } catch (IOException ex) {
            System.out.println("Immagine alla posizione:"+posizione+" caricata correttamente!");
            Logger.getLogger(CaricatoreImmagini.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return image;
    }
}
