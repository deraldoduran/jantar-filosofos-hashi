/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jantarfilosofos;

/**
 *
 * @author duran
 */
public class JantarFilosofos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
     {
      Mesa mesa = new Mesa ();
      for (int filosofo = 0; filosofo < 5; filosofo++)
      {
         new Filosofo("Filosofo_" + filosofo, mesa, filosofo).start();
      }
   }
}

    

