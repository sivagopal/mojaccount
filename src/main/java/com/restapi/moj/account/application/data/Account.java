package com.restapi.moj.account.application.data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String secondName;
    private String accountNumber;
    private String errorMessage;

    public Account(String firstName, String secondName, String accountNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.accountNumber= accountNumber;
    }

    public Account() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return  Objects.equals(firstName, account.firstName) &&
                Objects.equals(secondName, account.secondName) &&
                Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, secondName, accountNumber);
    }

    @Override
    public String toString() {
        return "Account{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
