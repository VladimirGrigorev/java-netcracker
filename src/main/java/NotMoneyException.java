/**
 * Исключение для нехватки денег во время обращения к кассе
 */
public class NotMoneyException extends Exception {
    public NotMoneyException() {
        System.out.println("Закончились деньги");
    }
}
