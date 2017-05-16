package AulaDia16052017;

import java.util.concurrent.Semaphore;

/**
 * Created by Hylson on 16/05/2017.
 */
public abstract class MonitorSC {
    private Semaphore mutex = new Semaphore(1, true);

    void enterMonitor() throws InterruptedException {
        mutex.acquire();
    }

    void exitMonitor() {
        mutex.release();
    }

    class CondVar {
        Semaphore Queue = new Semaphore ( 0, true );
        int WaitCount = 0;
        void await () throws InterruptedException
        {  WaitCount++;  mutex.release();
            Queue.acquire();  mutex.acquire();  }
        void signal ()
        {  if (WaitCount>0) { WaitCount--; Queue.release();  } }
    }
}
