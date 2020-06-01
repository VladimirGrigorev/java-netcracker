package exseption;

/**
 * Исключение нехватки денег в кассе
 */
public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException() {
        System.out.println("В кассе недостаточно денег!");
    }
}
