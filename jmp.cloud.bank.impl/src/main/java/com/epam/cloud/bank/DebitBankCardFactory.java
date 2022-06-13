package com.epam.cloud.bank;

import com.epam.dto.BankCard;
import com.epam.dto.DebitBankCard;
import com.epam.dto.User;

import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

public class DebitBankCardFactory implements Function<User, BankCard> {

    @Override
    public BankCard apply(User user) {
        BiFunction<String, User, DebitBankCard> debitBankCardBiFunction = DebitBankCard::new;
        return debitBankCardBiFunction.apply(UUID.randomUUID().toString(), user);
    }
}
