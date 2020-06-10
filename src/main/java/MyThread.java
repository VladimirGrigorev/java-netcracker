import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * Класс потока клиентов
 */
public class MyThread implements Runnable {
    /** Обменник */
    private Exchanger<Customer> exchanger;
    /** Клиент */
    private Customer customer;
    /** Пременная для генерации случайных значений */
    private Random random = new Random();
    /** Номер клиента */
    private int number = 0;

    MyThread(Exchanger<Customer> ex){
        this.exchanger=ex;
    }

    @Override
    public void run() {
        while(true){
            try{
                boolean isPut = random.nextBoolean();
                Thread.sleep( 1000);
                customer = new Customer("Клиент " + ++number, random.nextInt(3000) + 50,
                        random.nextInt(100), isPut);
                System.out.println(customer.getName() + " пришел");

                exchanger.exchange(customer);
            }
            catch(InterruptedException ex){
                System.out.println(ex.getMessage());
            }
        }

    }
}
