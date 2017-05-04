package AulaDia04052017;

class Produtor extends Thread{
    int id;
    public Produtor(int num) {
        id = num;
    }
    public void run(){
        for (int i = 0; i<Main.N; i++){
            MostraMsg(" quer colocar");
            try {
                Main.buff.deposit(id*Main.N+1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MostraMsg(" colocou");
        }
    }

    private void MostraMsg(String M){
        System.out.println("Produtor "+id+M);
    }
}