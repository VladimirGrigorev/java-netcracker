package entity;

/**
 * Класс банка
 */
public class Bank
{
    /** Общая касса. */
    private final Cashbox cashbox;

    /** Сотрудники */
    private final Worker worker1;
    private final Worker worker2;
    private final Worker worker3;

    /**
     * Конструктор
     */
    public Bank() {

        this.cashbox = new Cashbox(10000);

        this.worker1 = new Worker(cashbox);
        this.worker2 = new Worker(cashbox);
        this.worker3 = new Worker(cashbox);

        this.worker1.start();
        this.worker2.start();
        this.worker3.start();
    }

    /**
     * Метод для обслуживания клиентов, который подбирает очередь с наименьшим
     * количеством человек
     * @param client клиент
     */
    public void getService(Client client) {
        if (worker1.getQueue().isEmpty()) {
            worker1.addToQueue(client);
        } else if (worker2.getQueue().isEmpty()) {
            worker2.addToQueue(client);
        } else if (worker3.getQueue().isEmpty()) {
            worker3.addToQueue(client);
        } else if (worker1.getQueue().size() < worker2.getQueue().size()
                && worker1.getQueue().size() < worker3.getQueue().size()) {
            worker1.addToQueue(client);
        } else if (worker2.getQueue().size() < worker1.getQueue().size()
                && worker2.getQueue().size() < worker3.getQueue().size()) {
            worker2.addToQueue(client);
        } else {
            worker3.addToQueue(client);
        }
    }
}
