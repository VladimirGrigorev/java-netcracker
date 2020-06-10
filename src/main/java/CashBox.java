/**
 * Класс кассы
 */
class CashBox {
    /** Занята ли */
    private boolean isBusy = false;
    /** Количество денег */
    public int money = 1000;

    /** Ожидание доступа к кассе */
    public synchronized void accept() {
        while ( isBusy ) {
            try {
                wait();
            }
            catch ( InterruptedException ie ) {
                System.out.println("Ожидание прервано");
            }
        }
        isBusy = true;
    }

    public synchronized void done() {
        isBusy = false;
        notifyAll();
    }
}
