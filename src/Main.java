import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Cavallo> cavalli = new ArrayList<>();

        System.out.println("=== GARA DEI CAVALLI ===");
        System.out.print("Quanti cavalli partecipano? ");
        int n = input.nextInt();
        input.nextLine();


        for (int i = 0; i < n; i++) {
            System.out.print("Inserisci il nome del cavallo " + (i + 1) + ": ");
            String nome = input.nextLine();
            cavalli.add(new Cavallo(nome));
        }


        if (cavalli.size() >= 3) {
            cavalli.get(0).setPriority(Thread.MAX_PRIORITY);
            cavalli.get(1).setPriority(Thread.NORM_PRIORITY);
            cavalli.get(2).setPriority(Thread.MIN_PRIORITY);
        }

        System.out.println("\nLa gara sta per iniziare!\n");


        for (Cavallo c : cavalli) {
            c.start();
        }


        for (Cavallo c : cavalli) {
            try {
                c.join();
            } catch (InterruptedException e) {
                System.out.println("Errore nell'attesa del cavallo " + c.getNome());
            }
        }

        System.out.println("\n=== La gara è terminata! ===");
        System.out.println("Tutti i cavalli hanno completato il percorso!");
    }
}
