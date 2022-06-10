package com.epam.main;

import com.epam.bank.Bank;
import com.epam.cloud.bank.BankImpl;
import com.epam.cloud.bank.CreditCardFactory;
import com.epam.cloud.bank.DebitBankCardFactory;
import com.epam.cloud.service.ServiceImpl;
import com.epam.dto.BankCard;
import com.epam.dto.BankCardType;
import com.epam.dto.Subscription;
import com.epam.dto.User;
import com.epm.service.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class Application {

    public static void main(String[] args) {
        System.out.println("Init:");
        Bank bank = new BankImpl(cardFactories());
        Service service = new ServiceImpl();
        var user = new User("John", "Doe", LocalDate.of(1999, Month.JULY, 4));
        System.out.println(user);
        var bankCard = bank.createBankCard(user, BankCardType.CREDIT);
        System.out.println(bankCard);
        service.subscribe(bankCard);
        Optional<Subscription> subscription = service.getSubscriptionByBankCardNumber(bankCard.getNumber());
        System.out.println("-------------------------------------");
        System.out.println("Subscrotion: " + subscription.orElseThrow(RuntimeException::new));
        service.getAllUsers().forEach(System.out::println);
    }

    private static Map<BankCardType, Function<User, BankCard>> cardFactories() {
        var map = new ConcurrentHashMap<BankCardType, Function<User, BankCard>>();
        map.put(BankCardType.CREDIT, new CreditCardFactory());
        map.put(BankCardType.DEBIT, new DebitBankCardFactory());
        return map;
    }
}
