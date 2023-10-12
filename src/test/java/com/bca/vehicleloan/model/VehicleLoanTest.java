package com.bca.vehicleloan.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import com.bca.vehicleloan.exception.InputFormatException;

public class VehicleLoanTest {
    @Test
    public void vehicleLoan_shouldThrowInputFormatExceptionVehicleTypeMessage_whenVehicleTypeIsNotMotorOrMobil() {
        int price = 100000000;
        String condition = "baru";
        int createdYear = 2022;
        int downPayment = 25000000;
        int tenor = 5;
        String vehicleType = "skateboard";

        assertThrows(InputFormatException.class,
                () -> new VehicleLoan(price, vehicleType, condition, createdYear, downPayment, tenor));
    }

    @Test
    public void getMonltyInstallment_shouldReturnArrayOfMonthlyInstallment_whenAllInputValidAndVehicleTypeMobil() {
        String[] expectedResult = { "1,350,000.00", "1,459,350.00", "1,584,854.12", "1,722,736.38", "1,881,228.12" };
        int price = 100000000;
        String condition = "baru";
        int createdYear = 2022;
        int downPayment = 25000000;
        int tenor = 5;
        String vehicleType = "mobil";
        VehicleLoan vehicleLoan = new VehicleLoan(price, vehicleType, condition, createdYear,
                downPayment,
                tenor);

        String[] result = vehicleLoan.getInstallment().monltyInstallment();

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
        String vehicleType = "motor";
        VehicleLoan vehicleLoan = new VehicleLoan(price, vehicleType, condition, createdYear, downPayment,
                tenor);

        String[] result = vehicleLoan.getInstallment().monltyInstallment();

        assertArrayEquals(expectedResult, result);
    }
}
