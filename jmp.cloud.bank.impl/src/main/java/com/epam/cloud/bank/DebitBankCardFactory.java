package com.epam.cloud.bank;

import com.epam.dto.BankCard;
import com.epam.dto.DebitBankCard;
import com.epam.dto.User;

import java.util.UUID;
import java.util.function.Function;

public class DebitBankCardFactory implements Function<User, BankCard> {

    @Override
    public BankCard apply(User user) {
        return new DebitBankCard(UUID.randomUUID().toString(), user);
    }
}
