package com.epam.main;

import com.epam.bank.Bank;
import com.epam.cloud.bank.BankImpl;
import com.epam.cloud.service.ServiceImpl;
import com.epam.dto.BankCard;
import com.epam.dto.BankCardType;
import com.epam.dto.Subscription;
import com.epm.exception.BankCardNorFoundException;
import com.epm.service.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class Application {

    public static void main(String[] args) {
        System.out.println("Init:");
        Bank bank = new BankImpl();
        Service service = new ServiceImpl();
        var users = AppUtil.generateUsers();
        var cards = new ArrayList<BankCard>();
        users.forEach(user -> {
            System.out.println(user);
            var bankCard = bank.createBankCard(user, BankCardType.CREDIT);
            service.subscribe(bankCard);
            cards.add(bankCard);
            System.out.println(bankCard);
        });
        System.out.println("-------------------------------------");
        cards.forEach(card -> {
            Optional<Subscription> subscription = service.getSubscriptionByBankCardNumber(card.getNumber());
            System.out.println("Subscrotion: " + subscription.orElseThrow(BankCardNorFoundException::new));

        });
        service.getAllUsers().forEach(System.out::println);
        System.out.println("********AVG************");
        System.out.println(service.getAverageUsersAge());
        System.out.println("*************Payable Users***********");
        service.getAllUsers()
                .forEach(user -> System.out.println(user.getName() + " " + user.getSurname() + " is payable: " + Service.isPayableUser(user)));
        System.out.println("**************Subs by condition*******************");
        service.getAllSubscriptionsByCondition(subscription -> subscription.getStartDate().equals(LocalDate.now()))
                .forEach(System.out::println);

        System.out.println("****************Card not found*******************");
        System.out.println(service.getSubscriptionByBankCardNumber("12300").orElseThrow(BankCardNorFoundException::new));

    }

}
