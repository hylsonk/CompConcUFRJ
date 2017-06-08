package AulaDia08062017;

/**
 * Created by Nelson on 08/06/2017.
 */
public final class cliente_e_servidor {
    public static void main(String args[]){
        Entry E = new Entry();
        Cliente c1 = new Cliente(E,2);
        Cliente c2 = new Cliente(E,4);
        Servidor s = new Servidor(E);
        s.setDaemon(true);
        s.start();
        c1.start();
        c2.start();

    }
}
