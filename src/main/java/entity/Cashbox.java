package entity;

import exseption.NotEnoughMoneyException;

/**
 * Класс кассы банка
 */
public class Cashbox {

    /** Количество денег */
    private int money;

    /**
     * Конструктор
     * @param money количество денег
     */
    public Cashbox(int money) {
        this.money = money;
    }

    /**
     * Метод добавления денег
     * @param money количество денег для изъятия
     */
    public synchronized void addMoney(int money) {
        this.money += money;
    }

    /**
     * Метод изъятия денег
     * @param amount количество денег для изъятия
     * @throws NotEnoughMoneyException не хватает денег для выдачи
     */
    public synchronized void getMoney(int amount) throws NotEnoughMoneyException {
        if (amount > this.money) {
            throw new NotEnoughMoneyException();
        } else {
            money -= amount;
        }
    }

    /**
     * Получить количество денег
     * @return количество денег
     */
    public synchronized int getMoney() {
        return this.money;
    }
}
