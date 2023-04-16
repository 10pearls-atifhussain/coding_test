package com.smallworld.entity;

public class Beneficiary {
    User beneficiary;

    public Beneficiary(String beneficiaryFullName) {
        this.beneficiary = new User(beneficiaryFullName);
    }

    public Beneficiary(String beneficiaryFullName, int age) {
        this.beneficiary = new User(beneficiaryFullName, age);
    }

    public User getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(User beneficiary) {
        this.beneficiary = beneficiary;
    }

    @Override
    public String toString() {
        return "Beneficiary [beneficiary=" + beneficiary + "]";
    }
}
