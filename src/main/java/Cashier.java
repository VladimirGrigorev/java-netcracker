import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.concurrent.Exchanger;

/**
 * Класс кассира
 */
public class Cashier implements Runnable {
    /** Имя */
    private String name;
    /** Клиент */
    private Customer customer;
    /** Работает ли */
    private boolean isWork = false;
    /** Касса */
    private CashBox cashbox;
    /** Очередь клиентов */
    private LinkedList<Customer> customers;
    /** Обменник */
    private Exchanger<Customer> exchanger;

    Cashier(String name, CashBox cashBox, LinkedList<Customer> customers, Exchanger<Customer> exchanger) {
        this.name = name;
        this.cashbox = cashBox;
        this.customers = customers;
        this.exchanger = exchanger;
    }

    private void addInQueue(Customer customer){
        customers.addLast(customer);
    }

    public void getExchange() throws InterruptedException {
        customer=exchanger.exchange(customer);
        if(customer != null){
            isWork = true;
            addInQueue(customer);
            System.out.println(customer.getName() + " добавлен в очередь к " + name);
        }

    }

    /** Метод обслуживания клиента */
    public void serveClients() {
        Customer customer = customers.getFirst();
        System.out.println(name + " Обслуживает " + customer.getName());
        System.out.println(name + " Обращается к кассе");
        cashbox.accept();
        try {
            if(customer.getIsPut()) {
                cashbox.money += customer.getCash();
            }
            else{
                if (cashbox.money <= 0)
                    throw new NotMoneyException();

                cashbox.money -= customer.getCash();
            }

            Thread.sleep(customer.getDealTime());
        }
        catch (InterruptedException | NotMoneyException ie ) {
            System.out.println("Общение с кассой прервано");
        }

        System.out.println(name + " закончил работу с кассой.");
        System.out.println("В кассе " + cashbox.money);
        System.out.println(name + " закончил работу с " + customer.getName());

        cashbox.done();
        customers.removeFirst();
    }

    @Override
    public void run() {
        try{
            getExchange();
            while(isWork){
                serveClients();
                getExchange();
            }
        }

        catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }

    }

    public String getName(){
        return name;
    }
}
