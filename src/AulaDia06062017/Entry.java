package AulaDia06062017;

/**
 * Created by Nelson on 06/06/2017.
 */
public class Entry {
    private Port canalPedido = new Port();
    private MsgChamada msgChama;

    public Object call(Object pedido) throws InterruptedException{
        Link canalResp = new Link();
        canalPedido.send(new MsgChamada(canalResp));
        return canalResp.receive();/*Bloqueante*/
    }

    public Object accept() throws InterruptedException{
        msgChama = (MsgChamada) canalPedido.receive(); //bloqueante
        return msgChama.pedido;
    }

    public void reply(Object resposta) throws InterruptedException{
        msgChama.resposta.send(resposta);

    }

    private class MsgChamada{
        Object pedido;
        Link resposta;
        MsgChamada(Object m, Link c){
            pedido = m;
            resposta = c;
        }
        MsgChamada(Link c){
            pedido = new Object();
            resposta = c;
        }
    }

}
