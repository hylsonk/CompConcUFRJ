package AulaDia01062017;

/**
 * Created by Hylson on 01/06/2017.
 */
public abstract class Channel {
    public abstract void send(Object m);
    public abstract void send();
    public abstract Object receive();
}

//Mailbox: n enviantes, m receptores.
//Port:    n enviantes, 1 receptor.
//Link:    1 enviante, 1 receptor.