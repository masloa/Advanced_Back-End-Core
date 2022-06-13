package com.epam.cloud.bank;

import com.epam.bank.Bank;
import com.epam.dto.BankCard;
import com.epam.dto.BankCardType;
import com.epam.dto.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class BankImpl implements Bank {

    private final Map<BankCardType, Function<User, BankCard>> factories;

    public BankImpl() {
        this.factories = cardFactories();
    }

    @Override
    public BankCard createBankCard(User user, BankCardType bankCardType) {
        return factories
                .getOrDefault(bankCardType, u -> {throw new UnsupportedOperationException("Card type is not supported: " + bankCardType);})
                .apply(user);
    }

    private static Map<BankCardType, Function<User, BankCard>> cardFactories() {
        var map = new ConcurrentHashMap<BankCardType, Function<User, BankCard>>();
        map.put(BankCardType.CREDIT, new CreditCardFactory());
        map.put(BankCardType.DEBIT, new DebitBankCardFactory());
        return map;
    }
}
