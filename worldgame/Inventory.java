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
public class Inventory {
    public int iron = 0;
    public int copper = 0;
    public int wood = 0;
    public int stone = 0;
    public int coal = 0;
    public int clay = 0;
    public Inventory (){
        
    }

    void clear() {
        iron = 0;
        copper = 0;
        wood = 0;
        stone = 0;
        coal = 0;
        clay = 0;
    }
}
