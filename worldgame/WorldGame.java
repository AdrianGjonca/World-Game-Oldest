/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldgame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.text.Font;

public class WorldGame {

    static int playerX = 0;
    static int playerY = 0;
    static int health = 10;
    static int iron = 0;
    static int copper = 0;
    static int wood = 0;
    static int stone = 0;
    static char[][] screen = new char[40][40];
    static char[][] tileU = new char[5000][5000];
    static char[][] tileO = new char[5000][5000];
    static char[][] tile = new char[5000][5000];
    static char[][] world = new char[10000][10000];

    static int[][] light = new int[10000][10000];

    static boolean handy = true;

    static List<entity> entitiesO = new ArrayList<>();
    static List<entity> entitiesU = new ArrayList<>();
    static List<entity> entities = new ArrayList<>();

    static boolean level = true;

    static final char ironT = '▓';
    static final char copperT = '░';
    static final char woodT = '♣';

    static final char spawnerT = '▲';
    static String walkthrough = "╬~";

    public static Window win;

    public static void lighter(int x, int y, int val) {
        light[x * 2][y * 2] += val;
        light[x * 2 + 1][y * 2] += val;
        light[x * 2][y * 2 + 1] += val;
        light[x * 2 + 1][y * 2 + 1] += val;
    }

    public static void SRender(char key) {

        BufferedImage toDraw = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) toDraw.getGraphics();
        g.setBackground(Color.black);
        g.setColor(Color.red);
        //Clear Screan
        g.clearRect(0, 0, 1200, 800);

        for (int x2 = playerX / 2 - 100; x2 < playerX / 2 + 100; x2++) {
            for (int y2 = playerY / 2 - 100; y2 < playerY / 2 + 100; y2++) {
                light[x2 * 2][y2 * 2] = 0;
                light[x2 * 2 + 1][y2 * 2] = 0;
                light[x2 * 2][y2 * 2 + 1] = 0;
                light[x2 * 2 + 1][y2 * 2 + 1] = 0;

            }
        }
        //Process World
        for (int x = playerX / 2 - 100; x < playerX / 2 + 100; x++) {
            for (int y = playerY / 2 - 100; y < playerY / 2 + 100; y++) {
                world[x * 2][y * 2] = tile[x][y];
                world[x * 2 + 1][y * 2] = tile[x][y];
                world[x * 2][y * 2 + 1] = tile[x][y];
                world[x * 2 + 1][y * 2 + 1] = tile[x][y];
                int radi = 6;
                if (tile[x][y] == '╥') {
                    for (int ax = -radi; ax <= radi; ax++) {
                        for (int ay = -radi; ay <= radi; ay++) {
                            lighter(x + ax, y + ay, (((radi - Math.abs(Math.abs(radi - ay) - radi)) + (radi - Math.abs(Math.abs(radi - ax) - radi))) / 2) * 2);
                        }
                    }

                } else {

                }
            }
        }

        //Draw World
        for (int x = playerX - 40; x < playerX + 40; x++) {
            for (int y = playerY - 40; y < playerY + 40; y++) {
                int sx = x - playerX + 20;
                int sy = -y + playerY + 20;
                if (sx > 0 && sx < 40 && sy > 0 && sy < 40 && sx % 2 == 0 && sy % 2 == 0) {
                    screen[sx][sy] = world[x][y];
                    //g.drawString("" + world[x][y], sx * 16, sy * 16);
                    if (!level) {
                        TileConverter.draw(g, "" + world[x][y], sx * 16, sy * 16, light[x][y]);
                    } else {
                        TileConverter.draw(g, "" + world[x][y], sx * 16, sy * 16, 15);
                    }

                    //g.drawString("" + light[x][y], sx * 16, sy * 16);
                }
            }
        }

        for (entity creature : entities) {
            g.fillOval((creature.x - playerX + 20) * 16 + 16, (-creature.y + playerY + 20) * 16 - 24, 32, 32);
        }
        BufferedImage im = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        Graphics2D gi = (Graphics2D) im.getGraphics();

        if (key == 'w') {
            for (int i = 0; i < 32; i++) {
                gi.drawImage(toDraw, 0, -32 + i, null);
                gi.clearRect(0, 0, 64, 800);
                gi.clearRect(0, 0, 1200, 64);
                gi.clearRect(0, 600, 1200, 800);
                gi.clearRect(600, 0, 1200, 800);
                gi.fillRect(320, 320 - 8, 32, 32);
                drawUI(gi);
                win.g.drawImage(im.getSubimage(72, 86, 600 - 72, 600 - 72), 0, 0, 900, 900, null);
                try {
                    Thread.sleep(3);
                } catch (InterruptedException ex) {
                }
            }
        } else if (key == 's') {
            for (int i = 0; i < 32; i++) {
                gi.drawImage(toDraw, 0, 32 - i, null);
                gi.clearRect(0, 0, 64, 800);
                gi.clearRect(0, 0, 1200, 64);
                gi.clearRect(0, 600, 1200, 800);
                gi.clearRect(600, 0, 1200, 800);
                gi.fillRect(320, 320 - 8, 32, 32);
                drawUI(gi);
                win.g.drawImage(im.getSubimage(72, 86, 600 - 72, 600 - 72), 0, 0, 900, 900, null);
                try {
                    Thread.sleep(3);
                } catch (InterruptedException ex) {
                }
            }
        } else if (key == 'a') {
            for (int i = 0; i < 32; i++) {
                gi.drawImage(toDraw, -32 + i, 0, null);
                gi.clearRect(0, 0, 64, 800);
                gi.clearRect(0, 0, 1200, 64);
                gi.clearRect(0, 600, 1200, 800);
                gi.clearRect(600, 0, 1200, 800);
                gi.fillRect(320, 320 - 8, 32, 32);
                drawUI(gi);
                win.g.drawImage(im.getSubimage(72, 86, 600 - 72, 600 - 72), 0, 0, 900, 900, null);
                try {
                    Thread.sleep(3);
                } catch (InterruptedException ex) {
                }
            }
        } else if (key == 'd') {
            for (int i = 0; i < 32; i++) {
                gi.drawImage(toDraw, 32 - i, 0, null);
                gi.clearRect(0, 0, 64, 800);
                gi.clearRect(0, 0, 1200, 64);
                gi.clearRect(0, 600, 1200, 800);
                gi.clearRect(600, 0, 1200, 800);
                gi.fillRect(320, 320 - 8, 32, 32);
                drawUI(gi);
                win.g.drawImage(im.getSubimage(72, 86, 600 - 72, 600 - 72), 0, 0, 900, 900, null);
                try {
                    Thread.sleep(3);
                } catch (InterruptedException ex) {
                }
            }
        } else {
            gi.drawImage(toDraw, 0, 0, null);
            gi.clearRect(0, 0, 64, 800);
            gi.clearRect(0, 0, 1200, 64);
            gi.clearRect(0, 600, 1200, 800);
            gi.clearRect(600, 0, 1200, 800);
            gi.fillRect(320, 320 - 8, 32, 32);
            drawUI(gi);
            win.g.drawImage(im.getSubimage(72, 86, 600 - 72, 600 - 72), 0, 0, 900, 900, null);
            for (int i = 0; i < 32; i++) {
                try {
                    Thread.sleep(3);
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    public static void drawUI(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(78, 110, 100, 150);
        g.setColor(Color.red);
        g.drawString("HP: " + health, 82, 124);
        g.setColor(Color.white);
        g.drawString("Iron: " + iron, 82, 124 + 12 * 1);//( )
        g.drawString("Copper: " + copper, 82, 124 + 12 * 2);//(0)
        g.drawString("Wood: " + wood, 82, 124 + 12 * 3);//(-)
        g.drawString("Stone: " + stone, 82, 124 + 12 * 4);//(=)
        g.setColor(Color.PINK);
        g.drawString("[  ]", 140, 124 + 12 * 1);//( )
        g.drawString("[0]", 140, 124 + 12 * 2);//(0)
        g.drawString("[=/Bk]", 140, 124 + 12 * 3);//(-)
        g.drawString("[-]", 140, 124 + 12 * 4);//(=)
        if (wood >= 6 && iron >= 2) {
            g.setColor(Color.yellow);
            g.drawString("Door", 82, 124 + 12 * 6);
            g.setColor(Color.magenta);
            g.drawString("1", 140, 124 + 12 * 6);
        }
        if (wood >= 4 && stone >= 2) {
            g.setColor(Color.yellow);
            g.drawString("Torch", 82, 124 + 12 * 7);
            g.setColor(Color.magenta);
            g.drawString("2", 140, 124 + 12 * 7);
        }

        g.setColor(Color.white);
    }

    public static void TakeTurn(char key) {
        if (health < 1) {
            health = 10;
            playerY = 5009;
            playerX = 5009;
            stone = 0;
            copper = 0;
            iron = 0;
            wood = 0;
        }
        switch (key) {
            case 'a':
                if (world[playerX - 3][playerY] == ' ' || walkthrough.contains("" + world[playerX - 3][playerY])) {
                    playerX -= 2;
                }
                break;
            case 'd':
                if (world[playerX + 2][playerY] == ' ' || walkthrough.contains("" + world[playerX + 2][playerY])) {
                    playerX += 2;
                }
                break;
            case 'w':
                if (world[playerX][playerY + 2] == ' ' || walkthrough.contains("" + world[playerX][playerY + 2])) {
                    playerY += 2;
                }
                break;
            case 's':
                if (world[playerX][playerY - 3] == ' ' || walkthrough.contains("" + world[playerX][playerY - 3])) {
                    playerY -= 2;
                }
                break;
            case '1':
                if (wood >= 6 && iron >= 2) {
                    tile[(playerX - 1) / 2][(playerY - 1) / 2] = '╬';
                    iron -= 2;
                    wood -= 6;
                }
                break;
            case '2':
                if (wood >= 4 && stone >= 2) {
                    tile[(playerX - 1) / 2][(playerY - 1) / 2] = '╥';
                    wood -= 4;
                    stone -= 2;
                }
                break;
            case ' ':
                if (iron > 0) {
                    tile[(playerX - 1) / 2][(playerY - 1) / 2] = ironT;
                    iron -= 1;
                }
                break;
            case '0':
                if (copper > 0) {
                    tile[(playerX - 1) / 2][(playerY - 1) / 2] = copperT;
                    copper -= 1;
                } //▒
                break;
            case '-':
                if (stone > 0) {
                    tile[(playerX - 1) / 2][(playerY - 1) / 2] = '▒';
                    stone -= 1;
                }
                break;
            case '=':
                if (wood > 0) {
                    tile[(playerX - 1) / 2][(playerY - 1) / 2] = woodT;
                    wood -= 1;
                }
                break;
            case '%':
                if (wood > 0) {
                    tile[(playerX - 1) / 2][(playerY - 1) / 2] = '%';
                    wood -= 1;
                }
                break;
            case 'j':
                Attack('j');
                if (tile[(playerX - 1) / 2 - 1][(playerY - 1) / 2] != '~') {
                    if (tile[(playerX - 1) / 2 - 1][(playerY - 1) / 2] == ironT) {
                        iron += 1;
                    }
                    if (tile[(playerX - 1) / 2 - 1][(playerY - 1) / 2] == copperT) {
                        copper += 1;
                    }
                    if (tile[(playerX - 1) / 2 - 1][(playerY - 1) / 2] == woodT || tile[(playerX - 1) / 2 - 1][(playerY - 1) / 2] == '%') {
                        wood += 1;
                    }
                    if (tile[(playerX - 1) / 2 - 1][(playerY - 1) / 2] == '▒') {
                        stone += 1;
                    }
                    tile[(playerX - 1) / 2 - 1][(playerY - 1) / 2] = ' ';
                }
                handy = !handy;
                break;
            case 'l':
                Attack('l');
                if (tile[(playerX - 1) / 2 + 1][(playerY - 1) / 2] != '~') {
                    if (tile[(playerX - 1) / 2 + 1][(playerY - 1) / 2] == ironT) {
                        iron += 1;
                    }
                    if (tile[(playerX - 1) / 2 + 1][(playerY - 1) / 2] == copperT) {
                        copper += 1;
                    }
                    if (tile[(playerX - 1) / 2 + 1][(playerY - 1) / 2] == woodT || tile[(playerX - 1) / 2 + 1][(playerY - 1) / 2] == '%') {
                        wood += 1;
                    }
                    if (tile[(playerX - 1) / 2 + 1][(playerY - 1) / 2] == '▒') {
                        stone += 1;
                    }
                    tile[(playerX - 1) / 2 + 1][(playerY - 1) / 2] = ' ';
                }
                handy = !handy;
                break;
            case 'i':
                Attack('i');
                if (tile[(playerX - 1) / 2][(playerY - 1) / 2 + 1] != '~') {
                    if (tile[(playerX - 1) / 2][(playerY - 1) / 2 + 1] == ironT) {
                        iron += 1;
                    }
                    if (tile[(playerX - 1) / 2][(playerY - 1) / 2 + 1] == copperT) {
                        copper += 1;
                    }
                    if (tile[(playerX - 1) / 2][(playerY - 1) / 2 + 1] == woodT || tile[(playerX - 1) / 2][(playerY - 1) / 2 + 1] == '%') {
                        wood += 1;
                    }
                    if (tile[(playerX - 1) / 2][(playerY - 1) / 2 + 1] == '▒') {
                        stone += 1;
                    }
                    tile[(playerX - 1) / 2][(playerY - 1) / 2 + 1] = ' ';
                }
                handy = !handy;
                break;
            case 'k':
                Attack('k');
                if (tile[(playerX - 1) / 2][(playerY - 1) / 2 - 1] != '~') {
                    if (tile[(playerX - 1) / 2][(playerY - 1) / 2 - 1] == ironT) {
                        iron += 1;
                    }
                    if (tile[(playerX - 1) / 2][(playerY - 1) / 2 - 1] == copperT) {
                        copper += 1;
                    }
                    if (tile[(playerX - 1) / 2][(playerY - 1) / 2 - 1] == woodT || tile[(playerX - 1) / 2][(playerY - 1) / 2 - 1] == '%') {
                        wood += 1;
                    }
                    if (tile[(playerX - 1) / 2][(playerY - 1) / 2 - 1] == '▒') {
                        stone += 1;
                    }
                    tile[(playerX - 1) / 2][(playerY - 1) / 2 - 1] = ' ';
                }
                handy = !handy;
                break;
            case 'q':
                handy = !handy;
                break;
            case 'e':
                level = !level;
                break;
            case 'm':
                Save();
                break;
        }
    }

    public static void Attack(char direction) {
        int x1, x2, y1, y2;
        x1 = 0;
        x2 = 0;
        y1 = 0;
        y2 = 0;
        switch (direction) {
            case 'i':
                x1 = -4;
                x2 = 2;
                y1 = 0;
                y2 = 3;
                break;
            case 'j':
                x1 = -4;
                x2 = -2;
                y1 = -4;
                y2 = 2;
                break;
            case 'k':
                x1 = -4;
                x2 = 2;
                y1 = -5;
                y2 = -2;
                break;
            case 'l':
                x1 = 0;
                x2 = 3;
                y1 = -4;
                y2 = 2;
                break;
        }
        List<entity> remove = new ArrayList<entity>();
        for (entity creature : entities) {
            if (creature.x > playerX + x1 && creature.x < playerX + x2) {
                if (creature.y > playerY + y1 && creature.y < playerY + y2) {
                    remove.add(creature);
                    if ((int) ((3 * Math.random()) + 1) == 1) {
                        wood += 1;
                    }
                    if ((int) ((4 * Math.random()) + 1) == 1) {
                        stone += 1;
                    }
                    if ((int) ((12 * Math.random()) + 1) == 1) {
                        copper += 1;
                    }
                    if ((int) ((15 * Math.random()) + 1) == 1) {
                        iron += 1;
                    }
                }
            }
        }
        for (entity creature : remove) {
            entities.remove(creature);
        }
    }

    public static void main(String[] args) {
        win = new Window();
        win.g.setColor(Color.white);
        win.g.drawString("Generating World. Please wait...", 10, 10);
        WorldGen();
        playerY = 5009;
        playerX = 5009;
        tile = tileO;
        entities = entitiesO;
        SRender('q');
        /////Main Loop

        ///////End of Main loop
    }

    public static void Zombify() {

        List<entity> remove = new ArrayList<entity>();
        for (entity creature : entities) {
            ////Ai Start
            int bestX = 0;
            int bestY = 0;
            int difX = playerX - creature.x;
            int difY = playerY - creature.y;
            if (Math.abs(difX) > Math.abs(difY)) {
                if (playerX < creature.x) {
                    bestX -= 2;
                }
                if (playerX > creature.x) {
                    bestX += 2;
                }
            } else {
                if (playerY < creature.y) {
                    bestY -= 2;
                }
                if (playerY > creature.y) {
                    bestY += 2;
                }
            }

            if (Math.abs(playerY - creature.y) < 2 && Math.abs(playerX - creature.x) < 2) {
                if (tile[(playerX - 1) / 2][(playerY - 1) / 2] == ' ') {
                    remove.add(creature);
                    health -= 1;
                }
            }

            if (tile[(creature.x) / 2][(creature.y + bestY) / 2] == ' ' || tile[(creature.x) / 2][(creature.y + bestY) / 2] == spawnerT) {
                boolean a = true;
                for (entity thing : entities) {
                    if (creature.y + bestY == thing.y) {
                        if (creature.x == thing.x) {
                            a = false;
                        }
                    }
                }
                if (a) {
                    creature.y += bestY;
                }
            }
            if (tile[(creature.x + bestX) / 2][(creature.y) / 2] == ' ' || tile[(creature.x + bestX) / 2][(creature.y) / 2] == spawnerT) {
                boolean a = true;
                for (entity thing : entities) {
                    if (creature.y == thing.y) {
                        if (creature.x + bestX == thing.x) {
                            a = false;
                        }
                    }
                }
                if (a) {
                    creature.x += bestX;
                }
            }
            ////Ai End
            creature.life += 1;
            if (creature.life > 500) {
                remove.add(creature);
            }
        }
        for (entity creature : remove) {
            entities.remove(creature);
        }
        if ((int) ((10 * Math.random()) + 1) == 1) {
            for (int x = (playerX / 2) - 40; x < (playerX / 2) + 40; x++) {
                for (int y = (playerY / 2) - 40; y < (playerY / 2) + 40; y++) {
                    if (tile[x][y] == spawnerT) {
                        entity z = new entity();
                        z.x = x * 2;
                        z.y = y * 2;
                        entities.add(z);
                    }
                }
            }
        }
    }

    public static void WorldGen() {
        //Console.Clear();
        //Console.WriteLine("---Generating New World---");
        //Console.Refresh();

        /////Caves
        //Console.WriteLine("---Underground---");
        //Console.WriteLine("Filling in stone...");
        //Console.Refresh();
        for (int x = 0; x < 5000; x++) {
            for (int y = 0; y < 5000; y++) {
                tileU[x][y] = '▒';
            }
        }

        //Console.WriteLine("Generating cave systems...");
        //Console.Refresh();
        //Caves
        for (int x = 100; x < 4900; x += 2) {
            for (int y = 100; y < 4900; y += 2) {
                if ((int) ((60 * Math.random()) + 1) == 1) {
                    int ax = 0;
                    int ay = 0;
                    while ((int) ((10 * Math.random()) + 1) != 1) {
                        tileU[x + ax][y + ay] = ' ';
                        tileU[x + ax + 1][y + ay] = ' ';
                        tileU[x + ax - 1][y + ay] = ' ';
                        tileU[x + ax][y + ay + 1] = ' ';
                        tileU[x + ax][y + ay - 1] = ' ';
                        if ((int) ((2 * Math.random()) + 1) == 1) {
                            ax += 1;
                        } else {
                            ax -= 1;
                        }
                        if ((int) ((2 * Math.random()) + 1) == 1) {
                            ay += 1;
                        } else {
                            ay -= 1;
                        }

                        if ((int) ((20 * Math.random()) + 1) == 1) {
                            tileU[x][y] = spawnerT;
                        }

                    }
                }
            }
        }

        //Console.WriteLine("Planting ores:");
        //Console.WriteLine("of iron...");
        //Console.Refresh();
        //Ores
        for (int x = 100; x < 4900; x += 2) {
            for (int y = 100; y < 4900; y += 2) {
                if ((int) ((60 * Math.random()) + 1) == 1) {
                    int ax = 0;
                    int ay = 0;
                    while ((int) ((6 * Math.random()) + 1) != 1) {
                        tileU[x + ax][y + ay] = ironT;
                        tileU[x + ax + 1][y + ay] = ironT;
                        tileU[x + ax - 1][y + ay] = ironT;
                        tileU[x + ax][y + ay + 1] = ironT;
                        tileU[x + ax][y + ay - 1] = ironT;

                        if ((int) ((2 * Math.random()) + 1) == 1) {
                            ax += 1;
                        } else {
                            ax -= 1;
                        }

                        if ((int) ((2 * Math.random()) + 1) == 1) {
                            ay += 1;
                        } else {
                            ay -= 1;
                        }
                    }
                }
            }
        }
        //Console.WriteLine("of copper...");
        //Console.Refresh();
        for (int x = 100; x < 4900; x += 2) {
            for (int y = 100; y < 4900; y += 2) {
                if ((int) ((60 * Math.random()) + 1) == 1) {
                    int ax = 0;
                    int ay = 0;
                    while ((int) ((6 * Math.random()) + 1) != 1) {
                        tileU[x + ax][y + ay] = copperT;
                        tileU[x + ax + 1][y + ay] = copperT;
                        tileU[x + ax - 1][y + ay] = copperT;
                        tileU[x + ax][y + ay + 1] = copperT;
                        tileU[x + ax][y + ay - 1] = copperT;

                        if ((int) ((2 * Math.random()) + 1) == 1) {
                            ax += 1;
                        } else {
                            ax -= 1;
                        }

                        if ((int) ((2 * Math.random()) + 1) == 1) {
                            ay += 1;
                        } else {
                            ay -= 1;
                        }
                    }
                }
            }
        }

        //Console.WriteLine("Building house...");
        //Console.Refresh();
        //House
        for (int ax = 0; ax < 10; ax++) {
            for (int ay = 0; ay < 10; ay++) {
                tileU[2500 + ax][2500 + ay] = ' ';
                if (ax == 0 || ax == 9) {
                    tileU[2500 + ax][2500 + ay] = '%';
                } else if (ay == 0 && ax != 5 && ax != 4) {
                    tileU[2500 + ax][2500 + ay] = '%';
                } else if (ay == 9) {
                    tileU[2500 + ax][2500 + ay] = '%';
                }
            }
        }

        //////World
        //Console.WriteLine("---Overground---");
        //Console.WriteLine("Planting trees...");
        //Console.Refresh();
        for (int x = 0; x < 5000; x++) {
            for (int y = 0; y < 5000; y++) {
                tileO[x][y] = woodT;
            }
        }

        //Clearings
        //Console.WriteLine("Deforestation...");
        //Console.Refresh();
        for (int x = 100; x < 4900; x += 2) {
            for (int y = 100; y < 4900; y += 2) {
                if ((int) ((30 * Math.random()) + 1) == 1) {
                    int ax = 0;
                    int ay = 0;
                    while ((int) ((20 * Math.random()) + 1) != 1) {
                        tileO[x + ax][y + ay] = ' ';
                        tileO[x + ax + 1][y + ay] = ' ';
                        tileO[x + ax - 1][y + ay] = ' ';
                        tileO[x + ax][y + ay + 1] = ' ';
                        tileO[x + ax][y + ay - 1] = ' ';

                        tileO[x + ax + 1][y + ay + 1] = ' ';
                        tileO[x + ax - 1][y + ay + 1] = ' ';
                        tileO[x + ax + 1][y + ay - 1] = ' ';
                        tileO[x + ax - 1][y + ay - 1] = ' ';

                        tileO[x + ax + 2][y + ay] = ' ';
                        tileO[x + ax - 2][y + ay] = ' ';
                        tileO[x + ax][y + ay + 2] = ' ';
                        tileO[x + ax][y + ay - 2] = ' ';

                        tileO[x + ax + 2][y + ay + 1] = ' ';
                        tileO[x + ax - 2][y + ay - 1] = ' ';
                        tileO[x + ax + 1][y + ay + 2] = ' ';
                        tileO[x + ax - 1][y + ay - 2] = ' ';
                        if ((int) ((2 * Math.random()) + 1) == 1) {
                            ax += 1;
                        } else {
                            ax -= 1;
                        }
                        if ((int) ((2 * Math.random()) + 1) == 1) {
                            ay += 1;
                        } else {
                            ay -= 1;
                        }
                        if ((int) ((30 * Math.random()) + 1) == 1) {
                            tileO[x][y] = spawnerT;
                        }

                    }
                }
            }
        }
        //Lakes
        //Console.WriteLine("Lakes...");
        //Console.Refresh();
        for (int x = 100; x < 4900; x += 2) {
            for (int y = 100; y < 4900; y += 2) {
                if ((int) ((160 * Math.random()) + 1) == 1) {
                    int ax = 0;
                    int ay = 0;
                    while ((int) ((9 * Math.random()) + 1) != 1) {
                        tileO[x + ax][y + ay] = '~';
                        tileO[x + ax + 1][y + ay] = '~';
                        tileO[x + ax - 1][y + ay] = '~';
                        tileO[x + ax][y + ay + 1] = '~';
                        tileO[x + ax][y + ay - 1] = '~';
                        if ((int) ((2 * Math.random()) + 1) == 1) {
                            ax += 1;
                        } else {
                            ax -= 1;
                        }
                        if ((int) ((2 * Math.random()) + 1) == 1) {
                            ay += 1;
                        } else {
                            ay -= 1;
                        }
                    }
                }
            }
        }

        /////Structures
        //Console.WriteLine("---Structures---");
        //Console.WriteLine("Aincient Temples of Zomb...");
        //Console.Refresh();
        for (int i = 0; i < 5000; i++) {
            int sx = (int) ((4500 * Math.random()) + 100);
            int sy = (int) ((4500 * Math.random()) + 100);
            for (int ax = 0; ax < 12; ax++) {
                for (int ay = 0; ay < 10; ay++) {
                    tileO[sx + ay][sy + -ax] = Dungeons.AincientDungeon[ax][ay].charAt(0);
                }
            }
        }

        //House
        //Console.WriteLine("House...");
        //Console.Refresh();
        for (int ax = 0; ax < 10; ax++) {
            for (int ay = 0; ay < 10; ay++) {
                tileO[2500 + ax][2500 + ay] = ' ';
                if (ax == 0 || ax == 9) {
                    tileO[2500 + ax][2500 + ay] = '%';
                } else if (ay == 0 && ax != 5 && ax != 4) {
                    tileO[2500 + ax][2500 + ay] = '%';
                } else if (ay == 9) {
                    tileO[2500 + ax][2500 + ay] = '%';
                }
            }
        }
        //Console.WriteLine("---Finished---");
        //Console.Refresh();
        //Console.ReadKey();
    }

    public static void Save() {

    }

    public static void Load() {

    }
}
