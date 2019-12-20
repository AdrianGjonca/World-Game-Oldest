/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static worldgame.WorldGame.*;

/**
 *
 * @author Adrian
 */
public class Window extends JFrame implements KeyListener {

    public JPanel panel = new JPanel();
    public Graphics g;

    public Window() {
        this.setSize(900, 800);
        this.setResizable(false);
        this.setName("World Game");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.addKeyListener(this);
        panel.setFocusable(true);
        this.add(panel);
        panel.setBackground(Color.BLACK);
        g = this.getGraphics();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public char lastkey = 'q';
    //public boolean moving = false;
    public long lasttime = System.currentTimeMillis();

    @Override
    public void keyPressed(KeyEvent e) {
        if (HasChestOpen == false) {
            if (System.currentTimeMillis() > lasttime + 10 || e.getKeyChar() != lastkey) {
                //System.out.println(moving);
                //moving = true;
                if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                    TakeTurn(e.getKeyChar());
                } else {
                    TakeTurn('%');
                }
                Zombify();
                if (!level) {
                    tile = tileU;
                    entities = entitiesU;
                    chests = chestsU;
                } else {
                    tile = tileO;
                    entities = entitiesO;
                    chests = chestsO;
                }
                if (!level) {
                    tileU = tile;
                    entitiesU = entities;
                    chestsU = chests;
                } else {
                    tileO = tile;
                    entitiesO = entities;
                    chestsO = chests;
                }
                if (!HasChestOpen) {
                    SRender(e.getKeyChar());
                }
                lastkey = e.getKeyChar();
                lasttime = System.currentTimeMillis();
            }
        } else {
            if (System.currentTimeMillis() > lasttime + 10) {
                if (e.getKeyChar() == 'q') {
                    HasChestOpen = false;
                    SRender(e.getKeyChar());
                }
                if (e.getKeyChar() == 'p') {
                    chests.remove(INVENTORY_CHEST);
                    HasChestOpen = false;
                    SRender(e.getKeyChar());
                }
                if (e.getKeyChar() == 'w' && INVENTORY_X > 0) {
                    INVENTORY_X -= 1;
                } else if (e.getKeyChar() == 's' && INVENTORY_X < 5) {
                    INVENTORY_X += 1;
                }
                if (e.getKeyChar() == 'a') {
                    switch (INVENTORY_X) {
                        case 0:
                            if (inventory.iron > 0) {
                                inventory.iron -= 1;
                                INVENTORY_CHEST.inventory.iron += 1;
                            }
                            break;
                        case 1:
                            if (inventory.copper > 0) {
                                inventory.copper -= 1;
                                INVENTORY_CHEST.inventory.copper += 1;
                            }
                            break;
                        case 2:
                            if (inventory.wood > 0) {
                                inventory.wood -= 1;
                                INVENTORY_CHEST.inventory.wood += 1;
                            }
                            break;
                        case 3:
                            if (inventory.stone > 0) {
                                inventory.stone -= 1;
                                INVENTORY_CHEST.inventory.stone += 1;
                            }
                            break;
                        case 4:
                            if (inventory.coal > 0) {
                                inventory.coal -= 1;
                                INVENTORY_CHEST.inventory.coal += 1;
                            }
                            break;
                        case 5:
                            if (inventory.clay > 0) {
                                inventory.clay -= 1;
                                INVENTORY_CHEST.inventory.clay += 1;
                            }
                            break;
                    }
                } else if (e.getKeyChar() == 'd') {
                    switch (INVENTORY_X) {
                        case 0:
                            if (INVENTORY_CHEST.inventory.iron > 0) {
                                inventory.iron += 1;
                                INVENTORY_CHEST.inventory.iron -= 1;
                            }
                            break;
                        case 1:
                            if (INVENTORY_CHEST.inventory.copper > 0) {
                                inventory.copper += 1;
                                INVENTORY_CHEST.inventory.copper -= 1;
                            }
                            break;
                        case 2:
                            if (INVENTORY_CHEST.inventory.wood > 0) {
                                inventory.wood += 1;
                                INVENTORY_CHEST.inventory.wood -= 1;
                            }
                            break;
                        case 3:
                            if (INVENTORY_CHEST.inventory.stone > 0) {
                                inventory.stone += 1;
                                INVENTORY_CHEST.inventory.stone -= 1;
                            }
                            break;
                        case 4:
                            if (INVENTORY_CHEST.inventory.coal > 0) {
                                inventory.coal += 1;
                                INVENTORY_CHEST.inventory.coal -= 1;
                            }
                            break;
                        case 5:
                            if (INVENTORY_CHEST.inventory.clay > 0) {
                                inventory.clay += 1;
                                INVENTORY_CHEST.inventory.clay -= 1;
                            }
                            break;
                    }
                }
                lasttime = System.currentTimeMillis();
                if (HasChestOpen) {
                    Invetory(INVENTORY_CHEST);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //if(lastkey == e.getKeyChar()) moving = false;
    }

}
