/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Adrian
 */
public class TileConverter {

    public static BufferedImage tileset = load1();
    private static BufferedImage load1() {
        try {
            return tileset = ImageIO.read(new File(TileConverter.class.getResource("Tileset.png").getFile()));
        } catch (IOException ex) {
            return null;
        }
    }
    
    public static BufferedImage col(BufferedImage a, int bright) {
        BufferedImage i = new BufferedImage(a.getWidth(), a.getHeight(), BufferedImage.TYPE_INT_RGB);
        for(int x=0; x<a.getWidth(); x++){
            for(int y=0; y<a.getWidth(); y++){
                i.setRGB(x, y, a.getRGB(x, y));
            }
        }
        Graphics g = i.getGraphics();
        if(bright>10){
            g.setColor(new Color(0,0,0,0));
        }else if(bright>8){
            g.setColor(new Color(0,0,0,40));
        }else if(bright>6){
            g.setColor(new Color(0,0,0,70));
        }else if(bright>3){
            g.setColor(new Color(0,0,0,100));
        }else if(bright>0){
            g.setColor(new Color(0,0,0,130));
        }else{
            g.setColor(new Color(0,0,0,150));
        }
        g.fillRect(0, 0, i.getWidth(), i.getHeight());
        return i;
    }

    public static void draw(Graphics2D g, String t, int x, int y, int light) {
        int a = 0;
        if (t.charAt(a) == ' ') {
            if (WorldGame.level) {
                g.drawImage(col(tileset.getSubimage(0, 0, 16, 16), light), x, y - 8, 32, 32, null);
            } else {
                g.drawImage(col(tileset.getSubimage(16 * 7, 0, 16, 16), light), x, y - 8, 32, 32, null);
            }
        }
        if (t.charAt(a) == '♣') {
            g.drawImage(col(tileset.getSubimage(16, 0, 16, 16), light), x, y - 8, 32, 32, null);
        }
        if (t.charAt(a) == '▓') {
            g.drawImage(col(tileset.getSubimage(16 * 2, 0, 16, 16), light), x, y - 8, 32, 32, null);
        }
        if (t.charAt(a) == '░') {
            g.drawImage(col(tileset.getSubimage(16 * 3, 0, 16, 16), light), x, y - 8, 32, 32, null);
        }
        if (t.charAt(a) == '▒') {
            g.drawImage(col(tileset.getSubimage(16 * 4, 0, 16, 16), light), x, y - 8, 32, 32, null);
        }
        if (t.charAt(a) == '~') {
            g.drawImage(col(tileset.getSubimage(16 * 5, 0, 16, 16), light), x, y - 8, 32, 32, null);
        }
        if (t.charAt(a) == '▲') {
            g.drawImage(col(tileset.getSubimage(16 * 6, 0, 16, 16), light), x, y - 8, 32, 32, null);
        }
        if (t.charAt(a) == '╬') {
            g.drawImage(col(tileset.getSubimage(16 * 8, 0, 16, 16), light), x, y - 8, 32, 32, null);
        }
        if (t.charAt(a) == '╥') {
            g.drawImage(col(tileset.getSubimage(16 * 9, 0, 16, 16), light), x, y - 8, 32, 32, null);
        }
        if (t.charAt(a) == '%') {
            g.drawImage(col(tileset.getSubimage(16 * 10, 0, 16, 16), light), x, y - 8, 32, 32, null);
        }
        if (t.charAt(a) == '#') {
            g.drawImage(col(tileset.getSubimage(16 * 11, 0, 16, 16), light), x, y - 8, 32, 32, null);
        }
    }
}
