package AulaDia01062017;

import java.util.concurrent.Semaphore;

/**
 * Created by Hylson on 01/06/2017.
 */
public class AsyncMailbox extends Channel{

    private final int capacidade;
    private Object msgs[];
    private Semaphore msgDisp = new Semaphore(0);
    private Semaphore espDisp;
    private Semaphore mutexEnv = new Semaphore(1);
    private Semaphore mutexRec = new Semaphore(1);
    private int aptEnt = 0, aptSai = 0;


    public AsyncMailbox(int tamanho) {
        this.capacidade = tamanho;
        msgs = new Object[capacidade];
        espDisp = new Semaphore(capacidade);
    }

    public void send(Object m) {
        if (m == null)throw new NullPointerException("Null message passed to send");
        try {
            espDisp.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            mutexEnv.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        msgs[aptEnt] = m;
        aptEnt = (aptEnt+1)%capacidade;
        mutexEnv.release();
        msgDisp.release();
    }

    @Override
    public void send() {

    }

    @Override
    public Object receive() {
        try {
            msgDisp.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            mutexRec.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object m = msgs[aptSai];
        aptSai = (aptSai+1)%capacidade;
        mutexRec.release();
        espDisp.release();
        return m;
    }
}
