/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldgame;

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
    public static void WriteLine(Object text){
        System.out.println(text);
    }
    public static void Clear(){
        System.out.println("\n");
    }
    public static char ReadKey(){
        try {
            return (char)System.in.read();
        } catch (IOException ex) {
            return 'q';
        }
    }
}
