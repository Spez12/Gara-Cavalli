/**
 * Rappresenta un cavallo che partecipa a una gara.
 * Ogni cavallo è un Thread che esegue la propria corsa in parallelo.
 *
 * @author Speziali
 * @version 2.0
 */
public class Cavallo extends Thread {
    private String nome;
    private int distanzaPercorsa;


    public Cavallo(String nome) {
        this.nome = nome;
        this.distanzaPercorsa = 0;
    }


    @Override
    public void run() {
        System.out.println(" Il cavallo " + nome + " parte con priorità " + getPriority());

        for (int i = 1; i <= 10; i++) {
            distanzaPercorsa = i * 10;
            System.out.println(nome + " ha percorso " + distanzaPercorsa + " metri.");

            try {

                Thread.sleep((int) (Math.random() * 500) + 200);
            } catch (InterruptedException e) {
                System.out.println(nome + " è stato interrotto!");
            }
        }

        System.out.println(" Il cavallo " + nome + " ha terminato la gara!");
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDistanzaPercorsa() {
        return distanzaPercorsa;
    }

    public void setDistanzaPercorsa(int distanzaPercorsa) {
        this.distanzaPercorsa = distanzaPercorsa;
    }
}
