import entity.Bank;
import entity.Client;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();
        Bank bank = new Bank();

        /* Генерация клиентов */
        while (true) {
            if (random.nextInt(100000) == 1) {
                Client client = new Client(bank);
                client.start();
            }
        }
    }
}
