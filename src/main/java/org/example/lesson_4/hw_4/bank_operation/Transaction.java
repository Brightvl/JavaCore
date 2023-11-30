package org.example.lesson_4.hw_4.bank_operation;

import org.example.lesson_4.hw_4.accounts.Account;
import org.example.lesson_4.hw_4.custom_exeptions.InsufficientFundsException;

public class Transaction {

    /**
     * Позволяет проводить транзакции между счетами
     * @param from счет списания
     * @param to счет начисление
     * @param amount сумма
     * @throws InsufficientFundsException Исключение нехватки средств
     */
    public static void transfer(Account from, Account to, double amount) throws InsufficientFundsException {
        if (from.getAccountBalance() - amount < 0) {
            throw new InsufficientFundsException("Недостаточно средств для совершения перевода",
                    from.getAccountBalance(),
                    amount);
        }
        from.setAccountBalance(from.getAccountBalance() - amount);
        to.setAccountBalance(to.getAccountBalance() + amount);
    }
}
