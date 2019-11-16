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
    public boolean moving = false;
    @Override
    public void keyPressed(KeyEvent e) {
        if(moving == false){
            moving = true;
            lastkey = e.getKeyChar();
            
            if(e.getKeyCode() != KeyEvent.VK_BACK_SPACE){
                TakeTurn(e.getKeyChar());
            }else{
                TakeTurn('%');
            }
            Zombify();
            if(!level){
                tile = tileU;
                entities = entitiesU;
            }else{
                tile = tileO;
                entities = entitiesO;
            }
            if(!level){
                tileU = tile;
                entitiesU = entities;
            }else{
                tileO = tile;
                entitiesO = entities;
            } 
            SRender(e.getKeyChar());
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(lastkey == e.getKeyChar()) moving = false;
    }

}
