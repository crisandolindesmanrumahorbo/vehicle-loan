package com.bca.vehicleloan.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import com.bca.vehicleloan.exception.InputFormatException;

public class CarInstallmentTest {
    @Test
    public void isNew_shouldThrowInputFormatExceptionConditionMessage_whenConditionIsNotBaruOrBekas() {
        int price = 100000000;
        String condition = "kacau";
        int createdYear = 2022;
        int downPayment = 25000000;
        int tenor = 5;

        assertThrows(InputFormatException.class,
                () -> new CarInstallment(price, condition, createdYear, downPayment, tenor));
    }

    @Test
    public void isNew_shouldThrowInputFormatExceptionConditionMessage_whenConditionIsBaruButVehicleAge2Years() {
        int price = 100000000;
        String condition = "kacau";
        int createdYear = 2021;
        int downPayment = 25000000;
        int tenor = 5;

        assertThrows(InputFormatException.class,
                () -> new CarInstallment(price, condition, createdYear, downPayment, tenor));
    }

    @Test
    public void getDownPayment_shouldThrowInputFormatExceptionMinimumDownPaymentMessage_whenDownPaymentMinimumIsNotSufficient() {
        int price = 100000000;
        String condition = "baru";
        int createdYear = 2021;
        int downPayment = 20000000;
        int tenor = 5;

        assertThrows(InputFormatException.class,
                () -> new CarInstallment(price, condition, createdYear, downPayment, tenor));
    }

    @Test
    public void getTenor_shouldThrowInputFormatExceptionMinimumDownPaymentMessage_whenDownPaymentMinimumIsNotSufficient() {
        int price = 100000000;
        String condition = "baru";
        int createdYear = 2022;
        int downPayment = 20000000;
        int tenor = 7;

        assertThrows(InputFormatException.class,
                () -> new CarInstallment(price, condition, createdYear, downPayment, tenor));
    }

    @Test
    public void getCreatedYear_shouldThrowInputFormatExceptionTahunKendaraanMessage_whenCreatedDateIsNot4Digits() {
        int price = 100000000;
        String condition = "baru";
        int createdYear = 20225;
        int downPayment = 20000000;
        int tenor = 4;

        assertThrows(InputFormatException.class,
                () -> new CarInstallment(price, condition, createdYear, downPayment, tenor));
    }

    @Test
    public void getPrice_shouldThrowInputFormatExceptionTotalLoanMessage_whenTotalLoanGreaterThank1Billion() {
        int price = 1000000001;
        String condition = "baru";
        int createdYear = 20225;
        int downPayment = 20000000;
        int tenor = 4;

        assertThrows(InputFormatException.class,
                () -> new CarInstallment(price, condition, createdYear, downPayment, tenor));
    }

    @Test
    public void getMonltyInstallment_shouldReturnArrayOfMonthlyInstallment_whenAllInputValidAndConditionBaru() {
        String[] expectedResult = { "1,350,000.00", "1,459,350.00", "1,584,854.12", "1,722,736.38", "1,881,228.12" };
        int price = 100000000;
        String condition = "baru";
        int createdYear = 2022;
        int downPayment = 25000000;
        int tenor = 5;
        VechicleInstallment carInstallment = new CarInstallment(price, condition, createdYear, downPayment, tenor);

        String[] result = carInstallment.monltyInstallment();

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void getMonltyInstallment_shouldReturnArrayOfMonthlyInstallment_whenAllInputValidAndConditionBekas() {
        String[] expectedResult = { "1,170,000.00", "1,264,770.00", "1,373,540.25", "1,493,038.12", "1,630,397.62" };
        int price = 100000000;
        String condition = "bekas";
        int createdYear = 2000;
        int downPayment = 35000000;
        int tenor = 5;
        VechicleInstallment carInstallment = new CarInstallment(price, condition, createdYear, downPayment, tenor);

        String[] result = carInstallment.monltyInstallment();

        assertArrayEquals(expectedResult, result);
    }
}
