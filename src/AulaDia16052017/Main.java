package AulaDia16052017;


/**
 * Created by Hylson on 16/05/2017.
 */
public class Main {
    static final int NumeroFilosofos = 5;
    static MesaJantar mesa = new MesaJantar(NumeroFilosofos);
    static Filosofo[] filosofos = new Filosofo[NumeroFilosofos];

    public static void main(String[] Args){
        for(int i= 0; i< NumeroFilosofos; i++){
            filosofos[i] = new Filosofo(i);
        }
        for(int i= 0; i< NumeroFilosofos; i++){
            filosofos[i].start();
        }
        for(int i= 0; i< NumeroFilosofos; i++){
            try {
                filosofos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
