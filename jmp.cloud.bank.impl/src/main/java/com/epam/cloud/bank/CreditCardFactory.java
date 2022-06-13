package com.epam.cloud.bank;

import com.epam.dto.BankCard;
import com.epam.dto.CreditBankCard;
import com.epam.dto.User;

import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CreditCardFactory implements Function<User, BankCard> {

    @Override
    public BankCard apply(User user) {
        BiFunction<String, User, CreditBankCard> creditBankCardBiFunction = CreditBankCard::new;
        return creditBankCardBiFunction.apply(UUID.randomUUID().toString(), user);
    }
}
