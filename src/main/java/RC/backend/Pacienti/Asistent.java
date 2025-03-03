package RC.backend.Pacienti;

import java.util.Scanner;

public class Asistent {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Tu: ");
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }

            // Logică simplă pentru a răspunde la câteva întrebări
            if (input.contains("cum te cheamă")) {
                System.out.println("Mă numesc Assistentul tău Virtual!");
            } else if (input.contains("ce faci")) {
                System.out.println("Mă simt bine, mulțumesc că întrebi!");
            } else {
                System.out.println("Nu am înțeles. Poți repeta?");
            }
        }
    }
}
