/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldgame;

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

    public static BufferedImage tileset = load();

    public static BufferedImage load() {
        try {
            return tileset = ImageIO.read(new File(TileConverter.class.getResource("Tileset.png").getFile()));
        } catch (IOException ex) {
            return null;
        }
    }

    public static void draw(Graphics2D g, String t, int x, int y) {
        int a = 0;
        if (t.charAt(a) == ' ') {
            if(WorldGame.level){
                g.drawImage(tileset.getSubimage(0, 0, 16, 16), x, y - 8,32,32, null);
            }else{
                g.drawImage(tileset.getSubimage(16 * 7, 0, 16, 16), x, y - 8,32,32, null);
            }
        }
        if (t.charAt(a) == '♣') {
            g.drawImage(tileset.getSubimage(16, 0, 16, 16), x, y - 8,32,32, null);
        }
        if (t.charAt(a) == '▓') {
            g.drawImage(tileset.getSubimage(16 * 2, 0, 16, 16), x, y - 8,32,32, null);
        }
        if (t.charAt(a) == '░') {
            g.drawImage(tileset.getSubimage(16 * 3, 0, 16, 16), x, y - 8,32,32, null);
        }
        if (t.charAt(a) == '▒') {
            g.drawImage(tileset.getSubimage(16 * 4, 0, 16, 16), x, y - 8,32,32, null);
        }
        if (t.charAt(a) == '~') {
            g.drawImage(tileset.getSubimage(16 * 5, 0, 16, 16), x, y - 8,32,32, null);
        }
        if (t.charAt(a) == '▲') {
            g.drawImage(tileset.getSubimage(16 * 6, 0, 16, 16), x, y - 8,32,32, null);
        }
        if (t.charAt(a) == '╬') {
            g.drawImage(tileset.getSubimage(16 * 8, 0, 16, 16), x, y - 8,32,32, null);
        }
        if (t.charAt(a) == '╥') {
            g.drawImage(tileset.getSubimage(16 * 9, 0, 16, 16), x, y - 8,32,32, null);
        }
    }
}
