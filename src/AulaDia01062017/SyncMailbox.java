package AulaDia01062017;

import java.util.concurrent.Semaphore;

/**
 * Created by Hylson on 01/06/2017.
 */
public class SyncMailbox extends Channel {

    private Object msg = null;
    private final Semaphore enviado = new Semaphore(0);
    private final Semaphore recebido = new Semaphore(0);
    private final Object enviando = new Object();
    private final Object recebendo = new Object();

    @Override
    public void send(Object m) {
        if(m == null){
            throw new NullPointerException("Null pointer passed to send!");
        }
        synchronized (enviando){
            msg = m;
            enviado.release();
            try {
                recebido.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void send() {
        synchronized (enviando){
            msg = new Object();
            enviado.release();
            try {
                recebido.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Object receive() {
        Object msgRec = null;
        synchronized (recebendo){
            try {
                enviado.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            msgRec = msg;
            recebido.release();
        }
        return msgRec;
    }

    /**
     * receive() ara Links e portas
     */
    /*
    public final Object receive(){
        synchronized (recebendo){
            if(receiver == null){
                receiver = Thread.currentThread();
            }

            if (Thread.currentThread() != receiver) throw new InvalidLinkUsage("Attempted to use link with multiple receivers");

            Object msgrec = null;
            try {
                enviado.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            msgrec = msg;
            recebido.release();
            return msgrec;
        }
    }
    */
}
