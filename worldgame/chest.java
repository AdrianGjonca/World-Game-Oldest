/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldgame;

/**
 *
 * @author Adrian
 */
public class chest {
    public Inventory inventory = new Inventory ();
    public int x,y;
    public chest(int x,int y){
        this.x = x;
        this.y = y;
    }
}
