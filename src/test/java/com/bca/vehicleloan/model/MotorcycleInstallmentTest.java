package com.bca.vehicleloan.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import com.bca.vehicleloan.exception.InputFormatException;

public class MotorcycleInstallmentTest {
    @Test
    public void isNew_shouldThrowInputFormatExceptionConditionMessage_whenConditionIsNotBaruOrBekas() {
        int price = 100000000;
        String condition = "kacau";
        int createdYear = 2022;
        int downPayment = 25000000;
        int tenor = 5;

        assertThrows(InputFormatException.class,
                () -> new MotorcycleInstallment(price, condition, createdYear, downPayment, tenor));
    }

    @Test
    public void isNew_shouldThrowInputFormatExceptionConditionMessage_whenConditionIsBaruButVehicleAge2Years() {
        int price = 100000000;
        String condition = "kacau";
        int createdYear = 2021;
        int downPayment = 25000000;
        int tenor = 5;

        assertThrows(InputFormatException.class,
                () -> new MotorcycleInstallment(price, condition, createdYear, downPayment, tenor));
    }

    @Test
    public void getDownPayment_shouldThrowInputFormatExceptionMinimumDownPaymentMessage_whenDownPaymentMinimumIsNotSufficient() {
        int price = 100000000;
        String condition = "baru";
        int createdYear = 2021;
        int downPayment = 20000000;
        int tenor = 5;

        assertThrows(InputFormatException.class,
                () -> new MotorcycleInstallment(price, condition, createdYear, downPayment, tenor));
    }

    @Test
    public void getTenor_shouldThrowInputFormatExceptionMinimumDownPaymentMessage_whenDownPaymentMinimumIsNotSufficient() {
        int price = 100000000;
        String condition = "baru";
        int createdYear = 2022;
        int downPayment = 20000000;
        int tenor = 7;

        assertThrows(InputFormatException.class,
                () -> new MotorcycleInstallment(price, condition, createdYear, downPayment, tenor));
    }

    @Test
    public void getCreatedYear_shouldThrowInputFormatExceptionTahunKendaraanMessage_whenCreatedDateIsNot4Digits() {
        int price = 100000000;
        String condition = "baru";
        int createdYear = 20225;
        int downPayment = 20000000;
        int tenor = 4;

        assertThrows(InputFormatException.class,
                () -> new MotorcycleInstallment(price, condition, createdYear, downPayment, tenor));
    }

    @Test
    public void getPrice_shouldThrowInputFormatExceptionTotalLoanMessage_whenTotalLoanGreaterThank1Billion() {
        int price = 1000000001;
        String condition = "baru";
        int createdYear = 20225;
        int downPayment = 20000000;
        int tenor = 4;

        assertThrows(InputFormatException.class,
                () -> new MotorcycleInstallment(price, condition, createdYear, downPayment, tenor));
    }

    @Test
    public void getMonltyInstallment_shouldReturnArrayOfMonthlyInstallment_whenAllInputValidAndConditionBaru() {
        String[] expectedResult = { "1,362,500.00", "1,486,487.50", "1,629,190.38", "1,787,221.88", "1,969,518.50" };
        int price = 100000000;
        String condition = "baru";
        int createdYear = 2022;
        int downPayment = 25000000;
        int tenor = 5;
        VechicleInstallment motorInstallment = new MotorcycleInstallment(price, condition, createdYear, downPayment,
                tenor);

        String[] result = motorInstallment.monltyInstallment();

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void getMonltyInstallment_shouldReturnArrayOfMonthlyInstallment_whenAllInputValidAndConditionBekas() {
        String[] expectedResult = { "1,180,833.38", "1,288,289.12", "1,411,964.88", "1,548,925.62", "1,706,916.12" };
        int price = 100000000;
        String condition = "bekas";
        int createdYear = 2000;
        int downPayment = 35000000;
        int tenor = 5;
        VechicleInstallment motorInstallment = new MotorcycleInstallment(price, condition, createdYear, downPayment,
                tenor);

        String[] result = motorInstallment.monltyInstallment();

        assertArrayEquals(expectedResult, result);
    }
}
