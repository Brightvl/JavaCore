package org.example.lesson_4.hw_4.accounts;

public class CreditAccount extends Account {
    protected CreditAccount(double initialAccountsBalance) {
        super(initialAccountsBalance);
    }

    /**
     * Фабричный метод класса
     * @param initialAccountsBalance Исключение недопустимого аргумента при вводе отрицательного значения
     * @return объект класса CreditAccount
     */
    public static CreditAccount create(double initialAccountsBalance) {
        if (initialAccountsBalance < 0) {
            throw new IllegalArgumentException("Невозможно создать счет с отрицательным начальным балансом");
        }
        return new CreditAccount(initialAccountsBalance);
    }
}
