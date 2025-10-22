import java.util.List;

/**
 * Rappresenta un cavallo che partecipa a una gara.
 * Implementa Runnable per poter essere eseguito da un Thread.
 * Ogni cavallo registra il proprio ordine d'arrivo in modo sincronizzato.
 *
 * @author Speziali
 * @version 4.0
 */
public class Cavallo implements Runnable {
    private final String nome;
    private int distanzaPercorsa;
    private boolean finito;
    private final List<String> classifica;

    /**
     * Costruttore della classe Cavallo.
     *
     * @param nome       nome del cavallo
     * @param classifica lista condivisa in cui i cavalli registrano l'ordine di arrivo
     */
    public Cavallo(String nome, List<String> classifica) {
        this.nome = nome;
        this.classifica = classifica;
        this.distanzaPercorsa = 0;
        this.finito = false;
    }

    /**
     * Simula la corsa del cavallo con pause (sleep) e possibile interruzione.
     * Gestisce InterruptedException e registra l'ordine di arrivo.
     */
    @Override
    public void run() {
        System.out.println(" Il cavallo " + nome + " è pronto a partire");

        try {
            for (int i = 1; i <= 10; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(" Il cavallo " + nome + " si è infortunato e si ferma");
                    return;
                }

                distanzaPercorsa = i * 10;
                System.out.println(nome + " ha percorso " + distanzaPercorsa + " metri.");


                Thread.sleep((int) (Math.random() * 400) + 200);
            }

            finito = true;


            synchronized (classifica) {
                classifica.add(nome);
            }

            System.out.println(" Il cavallo " + nome + " ha tagliato il traguardo");
        } catch (InterruptedException exception) {
            System.out.println(" Il cavallo " + nome + " esce dalla gara ");
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.out.println("Errore inatteso per il cavallo " + nome + ": " + e.getMessage());
        }
    }

    public String getNome() {
        return nome;
    }

    public boolean isFinito() {
        return finito;
    }
}
