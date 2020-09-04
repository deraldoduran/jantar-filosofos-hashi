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
public class Filosofo  extends Thread{
    final static int TEMPO_MAXIMO = 100;
   Mesa mesa;
   int filosofo;

   public Filosofo (String nome, Mesa mesadejantar, int fil)
   {
      super(nome);
      mesa = mesadejantar;
      filosofo = fil;
   }

    Filosofo(int filosofo, Mesa mesa, int filosofo0) {
        throw new UnsupportedOperationException("Nao foi suportado"); //To change body of generated methods, choose Tools | Templates.
    }

   public void run ()
   {
      int tempo = 0;
      while (true)
      {
         tempo = (int) (Math.random() * TEMPO_MAXIMO);
         pensar(tempo);
         getHashi();
         tempo = (int) (Math.random() * TEMPO_MAXIMO);
         comer(tempo);
         returnHashi();
      }
   }

   public void pensar (int tempo)
   {
      try
      {
         sleep(tempo);
      }
      catch (InterruptedException e)
      {
         System.out.println("O Filófoso pensou em demasia");
      }
   }

   public void comer (int tempo)
   {
      try
      {
         sleep(tempo);
      }
      catch (InterruptedException e)
      {
         System.out.println("O Filósofo comeu em demasia");
      }
   }

   public void getHashi()
   {
      mesa.pegarHashi(filosofo);
   }

   public void returnHashi()
   {
      mesa.returningHashi(filosofo);
   }
}
