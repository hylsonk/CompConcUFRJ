package AulaDia04052017;

/**
 * Created by Nelson on 04/05/2017.
 */
public class Main {
    static final int N = 100;
    static BoundedBufferEmJava buff = new BoundedBufferEmJava(6);
    static Produtor[] produtores = new Produtor[5];
    static Consumidor[] consumidores = new Consumidor[5];

    public static void main(String[] args) throws InterruptedException {
        int i;
        for (i=0;i<5;i++){ // cria os prods e conss
            produtores[i] = new Produtor(i);
            consumidores[i] = new Consumidor(i);
        }
        for (i=0;i<5;i++) { // inicia prods e conss
            produtores[i].start();
            consumidores[i].start();
        }
        for (i=0;i<5;i++) { // espera o final dos prods e conss
            produtores[i].join();
            consumidores[i].join();
        }
    }
}
