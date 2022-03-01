/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joaqu
 */
public class Main {

   

    public static void main(String[] args) throws Exception {
        
         MainService mainSS = new MainService();
         
        try {
            // TODO code application logic here

            mainSS.mainService();
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    

}
