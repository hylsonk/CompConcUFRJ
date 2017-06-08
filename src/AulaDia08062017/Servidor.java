package AulaDia08062017;

/**
 * Created by Nelson on 08/06/2017.
 */
final class Servidor extends Thread{
    Entry E;

    public Servidor(Entry e) {
        E = e;
    }

    @Override
    public void run() {
        Message m;
        int numero;
        while (true){
            try{
                m = ((Message)E.accept());
                numero = m.numero;
                E.reply(new Integer(numero*numero));
            }catch (InterruptedException e){

            }
        }
    }
}
