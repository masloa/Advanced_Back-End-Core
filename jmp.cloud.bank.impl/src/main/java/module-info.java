import com.epam.bank.Bank;
import com.epam.cloud.bank.BankImpl;

module jmp.cloud.bank.impl {
    provides Bank with BankImpl;

    requires jmp.dto;
    requires transitive jmp.bank.api;

    exports com.epam.cloud.bank;
}