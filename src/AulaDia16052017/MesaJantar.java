package AulaDia16052017;

/**
 * Created by Hylson on 16/05/2017.
 */
public class MesaJantar extends MonitorSC {
    private int NumFil;
    final int Pensando = 0;  final int ComFome = 1;  final int Comendo = 2;
    private int [] Estado;
    private CondVar [] PodeComer;

    public MesaJantar ( int N ){
        NumFil = N; Estado = new int[N]; PodeComer = new CondVar[N];
        for (int i=0;i<N;i++) {
            Estado[i]=Pensando; PodeComer[1] = new CondVar();
        }
    }

    public void QueroComer ( int id ) throws InterruptedException {
        enterMonitor();
        int Esq = (id+1)%NumFil, Dir = (id+NumFil-1)%NumFil;
        Estado[id] = ComFome;
        while ( ( Estado[Esq]==Comendo ) || ( Estado[Dir]==Comendo) ) {
            PodeComer[id].await();
        }
        Estado[id] = Comendo;
        exitMonitor();
    }
    public void AcabeiDeComer ( int id ) throws InterruptedException {
        enterMonitor();
        Estado[id] = Pensando;
        Testa((id+1)%NumFil);           //Pode fazer um signal
        Testa((id+NumFil-1)%NumFil);    //Pode fazer outro signal
        exitMonitor();
    }

    private void Testa(int id) {
        int Esq = (id+1)%NumFil, Dir = (id+NumFil-1)%NumFil;
        if ((Estado[id] == ComFome)&&(Estado[Esq]<Comendo)&&(Estado[Dir]<Comendo)){
            PodeComer[id].signal();
        }
    }
}
