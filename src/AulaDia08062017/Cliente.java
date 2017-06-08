package AulaDia08062017;

/**
 * Created by Nelson on 08/06/2017.
 */
final class Cliente extends Thread{
    Entry E;
    int numero;


    public Cliente(Entry e, int numero) {
        E = e;
        this.numero = numero;
    }

    @Override
    public void run() {
        try{
            int i = ((Integer)E.call(new Message(numero))).intValue();
            System.out.println("O quadrado do "+numero+" é "+i);
        }catch (InterruptedException e){

        }
    }
}
