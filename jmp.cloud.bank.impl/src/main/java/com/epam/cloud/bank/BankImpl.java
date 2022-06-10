package com.epam.cloud.bank;

import com.epam.bank.Bank;
import com.epam.dto.BankCard;
import com.epam.dto.BankCardType;
import com.epam.dto.User;

import java.util.Map;
import java.util.function.Function;

public class BankImpl implements Bank {

    private final Map<BankCardType, Function<User, BankCard>> factories;

    public BankImpl(Map<BankCardType, Function<User, BankCard>> factories) {
        this.factories = factories;
    }

    @Override
    public BankCard createBankCard(User user, BankCardType bankCardType) {
        return factories
                .getOrDefault(bankCardType, u -> {throw new UnsupportedOperationException("Card type is not supported: " + bankCardType);})
                .apply(user);
    }
}
