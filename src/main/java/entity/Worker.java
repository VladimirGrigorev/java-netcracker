package entity;

import exseption.NotEnoughMoneyException;

import java.util.LinkedList;

/**
 * Класс работника банка.
 */
public class Worker extends Thread {

    /** Очередь клиентов к работнику */
    private final LinkedList<Client> queue;

    /** Касса */
    private Cashbox cashbox;

    /**
     * Конструктор класса
     * @param cashbox касса
     */
    public Worker(Cashbox cashbox) {
        this.queue = new LinkedList<>();
        this.cashbox = cashbox;
    }

    /**
     * Метод добавления клиента в очередь
     * @param client клиент
     */
    public void addToQueue(Client client) {
        synchronized(queue) {
            queue.addLast(client);
            queue.notify();
        }
    }

    /**
     * Рабочая функция потока
     * Работник ожидает клиента, если очередь пуста, или обслуживает клиента, если очередь не пуста
     */
    @Override
    public void run() {
        Client client;

        while (true) {
            synchronized(queue) {
                while (queue.isEmpty()) {
                    try
                    {
                        queue.wait();
                    }
                    catch (InterruptedException ignored)
                    {
                        throw new RuntimeException("Что-то пошло не так");
                    }
                }

                client = queue.removeFirst();
            }

            try {
                if (client.getStatus()) {
                    this.getCashbox().addMoney(client.getMoney());
                    System.out.println("Клиент внес " + client.getMoney() + " денег.");
                } else {
                    this.getCashbox().getMoney(client.getMoney());
                    System.out.println("Клиент взял " + client.getMoney() + " денег.");
                }
                System.out.println("Общее количество денег в банке: " + this.getCashbox().getMoney());
                Thread.sleep(client.getServiceTime());
            }
            catch (NotEnoughMoneyException e) {
                addToQueue(client);
            }
            catch (RuntimeException | InterruptedException e) {
                throw new RuntimeException("Что-то пошло не так");
            }
        }
    }

    /* ----- Методы доступа ----- */
    public Cashbox getCashbox() {
        return this.cashbox;
    }

    public LinkedList<Client> getQueue() {
        return this.queue;
    }
}
