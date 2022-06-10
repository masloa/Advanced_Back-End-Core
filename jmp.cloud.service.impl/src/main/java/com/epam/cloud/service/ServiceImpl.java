package com.epam.cloud.service;

import com.epam.dto.BankCard;
import com.epam.dto.Subscription;
import com.epam.dto.User;
import com.epm.service.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class ServiceImpl implements Service {

    private final Map<User, List<BankCard>> userCards = new ConcurrentHashMap<>();
    private final List<Subscription> subscriptions = new CopyOnWriteArrayList<>();

    @Override
    public void subscribe(BankCard bankCard) {
        userCards.computeIfAbsent(bankCard.getUser(), user -> new ArrayList<>()).add(bankCard);
        subscriptions.add(new Subscription(bankCard.getNumber(), LocalDate.now()));
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        return subscriptions.stream()
                .filter(subscription -> subscription.getBankcard().equals(cardNumber))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return userCards.keySet().stream().collect(Collectors.toUnmodifiableList());
    }
}
