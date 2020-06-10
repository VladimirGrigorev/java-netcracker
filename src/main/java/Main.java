import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;

public class Main {

    public static void main(String[] args) throws Exception {
        Random rand = new Random();

        Exchanger<Customer> ex = new Exchanger<Customer>();

        CashBox cashbox = new CashBox();

        LinkedList<Customer> queue1 = new LinkedList<Customer>();
        LinkedList<Customer> queue2 = new LinkedList<Customer>();
        LinkedList<Customer> queue3 = new LinkedList<Customer>();

        Cashier cashier1 = new Cashier("Кассир 1", cashbox, queue1, ex);
        Cashier cashier2 = new Cashier("Кассир 2", cashbox, queue2, ex);
        Cashier cashier3 = new Cashier("Кассир 3", cashbox, queue3, ex);

        List<Cashier> cashierList = new ArrayList<Cashier>();

        Thread[] cashiers = new Thread[3];

        cashierList.add(cashier1);
        cashierList.add(cashier2);
        cashierList.add(cashier3);

        Thread thread = new Thread(new MyThread(ex));
        /* Запуск потока клиентов */
        thread.start();

        cashiers[0] = new Thread(cashier1);
        cashiers[1] = new Thread(cashier2);
        cashiers[2] = new Thread(cashier3);

        /* Запуски потоков кассиров */
        for ( int i = 0; i < cashiers.length; ++i ) {
            Thread.sleep(rand.nextInt(3000) + 100);
            System.out.println(cashierList.get(i).getName() + " приступает к работе");
            cashiers[i].start();
        }
        for (Thread cashier : cashiers) cashier.join();
    }
}
