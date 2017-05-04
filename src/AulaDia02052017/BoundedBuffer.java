package AulaDia02052017;

import javax.management.monitor.Monitor;
import java.util.concurrent.locks.Condition;

/**
 * Created by Hylson on 02/05/2017.
 */
public abstract class BoundedBuffer extends Monitor {
    private int fullSlots = 0;
    private int capacity;
    private int[] buffer;

    private int in = 0;
    private int out = 0;

    /**
     * Perguntar ao Quilular se Condition é o equivalente para ConditionVariable
     *
     */
    private Condition notFull;
    private Condition notEmpty;


    public BoundedBuffer(int size){
        capacity = size;
        buffer = new int[size];
    }

    /**
     * Metodo usado pelas threads produtorass
     * @param value
     * @throws InterruptedException
     */
    public void deposit(int value) throws InterruptedException {
        while (fullSlots == capacity){
            notFull.await();
        }
        buffer[in] = value;
        in =(in + 1)%capacity;
        fullSlots++;

    }


    /**
     * Metodo usado pelas threads consumidoras
     * @throws InterruptedException
     */
    public void withdraw() throws InterruptedException {
        while(fullSlots == 0){
            notEmpty.await();
        }
        int value = buffer[out];
        out = (out + 1)%capacity;
        fullSlots--;
    }
}
