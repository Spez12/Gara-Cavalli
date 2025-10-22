import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

 public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Thread> threads = new ArrayList<>();
        List<Cavallo> cavalli = new ArrayList<>();
        List<String> classifica = new ArrayList<>();

        System.out.println("=== GARA DEI CAVALLI (Runnable + Classifica) ===");
        System.out.print("Quanti cavalli partecipano? ");
        int n = input.nextInt();
        input.nextLine();


        for (int i = 0; i < n; i++) {
            System.out.print("Inserisci il nome del cavallo " + (i + 1) + ": ");
            String nome = input.nextLine();
            Cavallo cavallo = new Cavallo(nome, classifica);
            cavalli.add(cavallo);
            threads.add(new Thread(cavallo));
        }


        if (threads.size() >= 3) {
            threads.get(0).setPriority(Thread.MAX_PRIORITY);
            threads.get(1).setPriority(Thread.NORM_PRIORITY);
            threads.get(2).setPriority(Thread.MIN_PRIORITY);
        }

        System.out.println("\nLa gara sta per iniziare\n");


        for (Thread t : threads) {
            t.start();
        }


        Random rnd = new Random();
        int indiceInterrotto = rnd.nextInt(threads.size());
        int ritardo = rnd.nextInt(1500) + 1000;

        try {
            Thread.sleep(ritardo);
            Thread daInterrompere = threads.get(indiceInterrotto);
            System.out.println("\n Il cavallo " +
                    cavalli.get(indiceInterrotto).getNome() + " si infortuna \n");
            daInterrompere.interrupt();
        } catch (InterruptedException exception) {
            System.out.println("Il thread principale è stato interrotto.");
        }


        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Errore durante l'attesa di un cavallo.");
            }
        }

        System.out.println("\n=== GARA TERMINATA ===");


        if (!classifica.isEmpty()) {
            String vincitore = classifica.get(0);
            String ultimo = classifica.get(classifica.size() - 1);

            System.out.println("\n Vincitore: " + vincitore);
            if (classifica.size() > 1) {
                System.out.println(" Ultimo: " + ultimo);
            }
        } else {
            System.out.println("Nessun cavallo ha terminato la gara");
        }


        System.out.println("\nClassifica finale:");
        for (Cavallo c : cavalli) {
            String stato = c.isFinito() ? "Arrivato " : "Infortunato ";
            System.out.println("- " + c.getNome() + " → " + stato);
        }

        input.close();
    }
}
