package com.bca.vehicleloan.model;

public class CarInstallment extends VechicleInstallment {
    private final boolean isNew;
    private final int createdYear;
    private final float baseInterest;
    private final int downPayment;
    private final int price;
    private final int tenor;

    public CarInstallment(int price, String condition, int createdYear, int downPayment, int tenor) {
        this.createdYear = getCreatedYear(createdYear);
        this.isNew = isNew(condition, this.createdYear);
        this.baseInterest = 0.08f;
        this.price = getPrice(price);
        this.downPayment = getDownPayment(downPayment, price, isNew);
        this.tenor = getTenor(tenor);
    }

    @Override
    public String[] monltyInstallment() {
        int principalLoan = price - downPayment;
        return getMonltyInstallment(tenor, baseInterest, principalLoan);
    }
}
