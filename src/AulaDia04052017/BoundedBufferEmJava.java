package AulaDia04052017;

/**
 * Created by Hylson on 02/05/2017.
 * Em Java
 */
public class BoundedBufferEmJava {
    private int fullSlots = 0;
    private int capacity;
    private int[] buffer;

    private int in = 0;
    private int out = 0;


    public BoundedBufferEmJava(int size){
        capacity = size;
        buffer = new int[size];
    }

    /**
     * Metodo usado pelas threads produtorass
     * @param value
     * @throws InterruptedException
     */
    public synchronized void deposit(int value) throws InterruptedException {
        while (fullSlots == capacity){
            this.wait();
        }
        buffer[in] = value;
        in =(in + 1)%capacity;
        fullSlots++;
        if (fullSlots==capacity){
            this.notifyAll();
        }
    }


    /**
     * Metodo usado pelas threads consumidoras
     * @throws InterruptedException
     */
    public synchronized int withdraw() throws InterruptedException {
        while(fullSlots == 0){
            this.wait();
        }
        int value = buffer[out];
        out = (out + 1)%capacity;
        fullSlots--;
        if (fullSlots==(capacity-1)){
            this.notifyAll();
        }
        return value;
    }
}

