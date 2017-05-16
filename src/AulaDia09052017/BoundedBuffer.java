package AulaDia09052017;

import java.util.concurrent.locks.*;

/**
 * Created by Nelson on 09/05/2017.
 */
public class BoundedBuffer {
    private int fullSlots = 0;
    private int in = 0;
    private int out = 0;
    private int capacity;
    private int [] buffer;
    private ReentrantLock mutex;
    private Condition notFull;
    private Condition notEmpty;

    public BoundedBuffer(int bufferSize){
        capacity = bufferSize;
        buffer = new int[bufferSize];
        mutex = new ReentrantLock();
        notFull = mutex.newCondition();
        notEmpty = mutex.newCondition();
    }

    public void deposit(int value) throws InterruptedException{
        mutex.lock();
        try{
            while(fullSlots==capacity){
                notFull.await();
            }
            buffer[in] = value;
            in = (in+1)%capacity;
            fullSlots++;
            notEmpty.signal();
        }finally {
            mutex.unlock();
        }
    }

    public int withdraw() throws InterruptedException{
        mutex.lock();
        try{
            while(fullSlots == 0){
                notEmpty.await();
            }
            int value = buffer[out];
            out = (out+1)%capacity;
            fullSlots
            notFull.signal();
        }finally {
            mutex.unlock();
        }
    }


}
