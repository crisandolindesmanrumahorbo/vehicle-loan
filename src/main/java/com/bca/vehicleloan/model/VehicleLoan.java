package com.bca.vehicleloan.model;

import com.bca.vehicleloan.constant.Constant;
import com.bca.vehicleloan.exception.InputFormatException;

public class VehicleLoan implements ILoan {
    private final IInstallment installment;

    public VehicleLoan(int price, String vehicleType, String condition, int createdYear, int downPayment, int tenor) {
        this.installment = vehicle(price, vehicleType, condition, createdYear, downPayment, tenor);
    }

    private VechicleInstallment vehicle(int price, String vehicleType, String condition, int createdYear,
            int downPayment,
            int tenor) {
        if (vehicleType.equalsIgnoreCase(Constant.CAR)) {
            return new CarInstallment(price, condition, createdYear, downPayment, tenor);
        }
        if (vehicleType.equalsIgnoreCase(Constant.BIKE)) {
            return new MotorcycleInstallment(price, condition, createdYear, downPayment, tenor);
        }
        throw new InputFormatException("Vehicle Type Exception");
    }

    @Override
    public IInstallment getInstallment() {
        return installment;
    }
}
