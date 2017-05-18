package AulaDia18052017;

/**
 * Created by Hylson on 18/05/2017.
 */
public class LeEsc  extends MonitorSC{ // Usei SC, mas na aula foi usado o SE e não tenho esse algoritmo
    int espLe = 0;
    int lendo = 0;
    int espEsc = 0;
    int escrevendo = 0;
    CondVar podeLer = new CondVar();
    CondVar podeEsc = new CondVar();

    public void IniLe() throws InterruptedException {
        enterMonitor();
        if(escrevendo>0){
            espLe++;
            podeLer.await();
        }
        lendo++;
        if (espLe>0){
            espLe--;
            podeLer.signal();
            return;
        }
        exitMonitor();
    }

    public void FimLe() throws InterruptedException {
        enterMonitor();
        lendo--;
        if ((lendo==0)&&(espEsc>0)){
            espEsc--;
            podeEsc.signal();
            return;
        }
        exitMonitor();
    }

    public void IniEsc() throws InterruptedException {
        enterMonitor();
        if ((escrevendo+lendo)>0){
            espEsc++;
            podeEsc.await();
        }
        escrevendo++;
        exitMonitor();
    }

    public void FimEsc() throws InterruptedException{
        enterMonitor();
        if (espLe>0){
            espLe--;
            podeLer.signal();
            return;
        }
        if (espEsc>0){
            espEsc--;
            podeEsc.signal();
            return;
        }
        exitMonitor();
    }


}
