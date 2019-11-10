/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldgame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrian
 */
class Console {

    public static int CursorLeft = 0;
    public static int CursorTop = 0;
    public static Window window;

    public static void init() {
        window = new Window();
    }
    static BufferedImage screen = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
    static BufferedImage old = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);

    public static void WriteLine(Object text) {
        String str = (String) text;
        for (int x = 0; x < str.length(); x++) {
            if (str.charAt(x) != '\n') {
                screen.getGraphics().drawString("" + str.charAt(x), CursorLeft * 12, CursorTop * 12);
                CursorLeft++;
            } else {
                CursorLeft = 0;
                CursorTop++;
            }
        }
        CursorLeft = 0;
        CursorTop++;
    }

    public static void Clear() {
        CursorLeft = 0;
        CursorTop = 0;
        screen = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
    }

    public static void Refresh() {
        window.panel.getGraphics().drawImage(screen, 0, 0, null);
        old = screen;
    }

    public static void SRefresh(char d) {
        BufferedImage news = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        if (d == 'd') {
            for (int c = 0; c < 24; c++) {
                news.getGraphics().drawImage(old, -c, 0, null);
                news.getGraphics().fillRect(0, 0, 12, 800);
                news.getGraphics().fillRect(800-12, 0, 800, 800);
                news.getGraphics().fillRect(0, 486, 800, 800);
                window.panel.getGraphics().drawImage(news, 0, 0, null);
                try {
                    Thread.sleep(500 / 24);
                } catch (InterruptedException ex) {

                }
            }
        }else if(d == 'a'){
            for (int c = 23; c >= 0; c--) {
                window.panel.getGraphics().drawImage(old, -c+24, 0, null);
                try {
                    Thread.sleep(500 / 24);
                } catch (InterruptedException ex) {

                }
            }
        }else if (d == 's') {
            for (int c = 0; c < 24; c++) {
                window.panel.getGraphics().drawImage(old, 0, -c, null);
                try {
                    Thread.sleep(500 / 24);
                } catch (InterruptedException ex) {

                }
            }
        }else if(d == 'w'){
            for (int c = 23; c >= 0; c--) {
                window.panel.getGraphics().drawImage(old, 0, -c+24, null);
                try {
                    Thread.sleep(500 / 24);
                } catch (InterruptedException ex) {

                }
            }
        }
        
        window.panel.getGraphics().drawImage(screen, 0, 0, null);
        Refresh();
    }

    public static char key = '`';

    public static char ReadKey() {
        while (key == '`') {
            System.out.println(key);
        }
        char z = key;
        return z;
    }
}
