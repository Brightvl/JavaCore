package org.example.lesson_4.hw_4.accounts;

import org.example.lesson_4.hw_4.custom_exeptions.InsufficientFundsException;


public abstract class Account {

    //region Поля
    private double accountBalance;
    //endregion

    //region Конструктор


    protected Account(double initialAccountsBalance) {
        this.accountBalance = initialAccountsBalance;
    }
    //endregion

    //region Методы

    /**
     * Вносит переданную сумму в аккаунт
     *
     * @param depositAmount Сумма депозита
     */
    public void increaseDepositAmount(double depositAmount) {
        if (depositAmount < 0) {
            throw new IllegalArgumentException("Нельзя внести депозит с отрицательной суммой");
        }
        this.accountBalance += depositAmount;
    }

    /**
     * Вывод денег с аккаунта
     *
     * @param withdrawalAmount Сумма вывода
     * @throws InsufficientFundsException Исключение нехватки средств на балансе
     */
    public void withdrawMoney(double withdrawalAmount) throws InsufficientFundsException {
        if (accountBalance - withdrawalAmount < 0) {
            throw new InsufficientFundsException("Недостаточно средств на счету", accountBalance, withdrawalAmount);
        }
        this.accountBalance -= withdrawalAmount;
    }

    //endregion

    //region Геттеры и сеттеры


    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
    //endregion
}
