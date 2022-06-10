package com.epm.service;

import com.epam.dto.BankCard;
import com.epam.dto.Subscription;
import com.epam.dto.User;

import java.util.List;
import java.util.Optional;

public interface Service {

    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

    List<User> getAllUsers();

}
