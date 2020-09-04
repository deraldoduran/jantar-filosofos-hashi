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
public class Mesa {
     final static int PENSANDO = 1;
   final static int COMENDO = 2;
   final static int FOME = 3;
   final static int NR_FILOSOFOS = 5;
   final static int PRIMEIRO_FILOSOFO = 0;
   final static int ULTIMO_FILOSOFO = NR_FILOSOFOS - 1;
   boolean[] Hashi = new boolean[NR_FILOSOFOS];
   int[] filosofos = new int[NR_FILOSOFOS];
   int[] tentativas = new int[NR_FILOSOFOS];

   public Mesa()
   {
      for (int i = 0; i < 5; i++)
      {
         Hashi[i] = true;
         filosofos[i] = PENSANDO;
         tentativas[i] = 0;
      }
   }

   public synchronized void pegarHashi (int filosofo)
   {
      filosofos[filosofo] = FOME;
      while (filosofos[aEsquerda(filosofo)] == COMENDO || filosofos[aDireita(filosofo)] == COMENDO)
      {
         try
         {
            tentativas[filosofo]++;
            wait();
         }
         catch (InterruptedException e)
         {
         }
      }
      System.out.println("O Filósofo morreu devido a starvation");
      tentativas[filosofo] = 0;
      Hashi[HashiEsquerdo(filosofo)] = false;
      Hashi[HashiDireito(filosofo)] = false;
      filosofos[filosofo] = COMENDO;
      imprimeEstadosFilosofos();
      imprimeHashi();
      imprimeTentativas();
   }

   public synchronized void returningHashi (int filosofo)
   {
     Hashi[HashiEsquerdo(filosofo)] = true;
      Hashi[HashiDireito(filosofo)] = true;
      if (filosofos[aEsquerda(filosofo)] == FOME || filosofos[aDireita(filosofo)] == FOME)
      {
         notifyAll();
      }
      filosofos[filosofo] = PENSANDO;
      imprimeEstadosFilosofos();
      imprimeHashi();
      imprimeTentativas();
   }

   public int aDireita (int filosofo)
   {
      int direito;
      if (filosofo == ULTIMO_FILOSOFO)
      {
         direito = PRIMEIRO_FILOSOFO;
      }
      else
      {
         direito = filosofo + 1;
      }
      return direito;
   }

   public int aEsquerda (int filosofo)
   {
      int esquerdo;
      if (filosofo == PRIMEIRO_FILOSOFO)
      {
         esquerdo = ULTIMO_FILOSOFO;
      }
      else
      {
         esquerdo = filosofo - 1;
      }
      return esquerdo;
   }

   public int HashiEsquerdo (int filosofo)
   {
      int HashiEsquerdo = filosofo;
      return HashiEsquerdo;
   }

   public int HashiDireito (int filosofo)
   {
      int HashiDireito;
      if (filosofo == ULTIMO_FILOSOFO)
      {
         HashiDireito = 0;
      }
      else
      {
         HashiDireito = filosofo + 1;
      }
      return HashiDireito;
   }

   public void imprimeEstadosFilosofos ()
   {
      String texto = "*";
      System.out.print("Filósofos = [ ");
      for (int i = 0; i < NR_FILOSOFOS; i++)
      {
         switch (filosofos[i])
         {
            case PENSANDO :
               texto = "PENSANDO";
               break;
            case FOME :
               texto = "FOME";
               break;
            case COMENDO :
               texto = "COMENDO";
               break;
         }
         System.out.print(texto + " ");
      }
      System.out.println("]");
   }

   public void imprimeHashi ()
   {
      String Hashis = "*";
      System.out.print("Hashi = [ ");
      for (int i = 0; i < NR_FILOSOFOS; i++)
      {
         if (Hashi[i])
         {
            Hashis = "LIVRE";
         }
         else
         {
            Hashis = "OCUPADO";
         }
         System.out.print(Hashis + " ");
      }
      System.out.println("]");
   }

   public void imprimeTentativas ()
   {
      System.out.print("Tentou comer = [ ");
      for (int i = 0; i < NR_FILOSOFOS; i++)
      {
         System.out.print(filosofos[i] + " ");
      }
      System.out.println("]");
   }
}
