package AulaDia16052017;

import java.util.Random;

/**
 * Created by Hylson on 16/05/2017.
 */
public class Filosofo extends Thread{
    private int id;
    public int NumVezes = 100;
    private Random gen = new Random();

    public Filosofo(int id) {
        this.id = id;
    }

    public void run(){
        for (int i = 0; i<NumVezes; i++){
            msg("Pensando");
            EsperaAleat(100);
            msg("Quer comer");
            try {
                Main.mesa.QueroComer(id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            msg("Comendo");
            EsperaAleat(100);
            msg("Acabou de comer");
            try {
                Main.mesa.AcabeiDeComer(id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    private void msg(String M){
        System.out.println("Filosofo "+id+": "+M+".");
    }

    private void EsperaAleat(int TempoMax){
        try {
            sleep(gen.nextInt(TempoMax));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
