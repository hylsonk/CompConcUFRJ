package AulaDia04052017;

class Consumidor extends Thread{
    int id;
    public Consumidor(int num) {
        id = num;
    }
    public void run(){
        for (int i = 0; i< Main.N; i++){
            MostraMsg(" quer consumir");
            int valor = 0;
            try {
                valor = Main.buff.withdraw();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MostraMsg(" consumiu "+valor);
        }
    }

    private void MostraMsg(String M){
        System.out.println("Consumir "+id+M);
    }
}