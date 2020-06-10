/**
 * Класс клиента
 */
class Customer {
    /** Имя */
    private String name;
    /** Время обслуживания */
    private long dealTime;
    /** Сумма */
    private int cash;
    /** Собирается ли положить */
    private boolean isPut;

    Customer(String name, long dealTime, int cash, boolean isPut) {
        this.name = name;
        this.dealTime = dealTime;
        this.cash = cash;
        this.isPut = isPut;
    }

    public boolean getIsPut(){ return isPut; }

    public String getName(){
        return name;
    }

    public int getCash(){
        return cash;
    }

    public long getDealTime() {
        return dealTime;
    }
}