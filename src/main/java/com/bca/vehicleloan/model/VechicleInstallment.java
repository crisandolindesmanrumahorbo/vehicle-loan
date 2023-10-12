package com.bca.vehicleloan.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.Year;

import com.bca.vehicleloan.constant.Constant;
import com.bca.vehicleloan.exception.InputFormatException;

public abstract class VechicleInstallment implements IInstallment {
    protected String[] getMonltyInstallment(int tenor, float baseInterest, int principalLoan) {
        String[] monthlyInstallments = new String[tenor];
        float principalLoanAggregate = principalLoan;
        for (int year = 1; year <= tenor; year++) {
            float totalLoan = principalLoanAggregate + (principalLoanAggregate * baseInterest);
            float monltyInstallment = totalLoan / (12 * (tenor - year + 1));
            BigDecimal montlyInstallmentDecimal = BigDecimal.valueOf(monltyInstallment);
            float installmentYearly = monltyInstallment * 12;
            principalLoanAggregate = totalLoan - installmentYearly;
            DecimalFormat df = new DecimalFormat("#,###.00");
            String monthlyInstallment = df.format(montlyInstallmentDecimal.setScale(2, RoundingMode.HALF_EVEN))
                    .toString();
            monthlyInstallments[year - 1] = monthlyInstallment;
            System.out.printf(Constant.MONTHLY_STRING, year,
                    monthlyInstallment,
                    baseInterest * 100);
            System.out.print("%");
            System.out.println();
            if (year % 2 == 0) {
                baseInterest += Constant.INTEREST_YOY_TWO_YEARS;
                continue;
            }
            baseInterest += Constant.INTEREST_YOY_ONE_YEARS;
        }
        return monthlyInstallments;
    }

    protected boolean isNew(String condition, int createdYear) {
        if (condition.equalsIgnoreCase(Constant.CONDITION_SECOND)) {
            return false;
        }
        if (condition.equalsIgnoreCase(Constant.CONDITION_NEW)) {
            int currentYear = Year.now().getValue();
            int vechicleAge = currentYear - createdYear;
            if (vechicleAge > 1) {
                throw new InputFormatException("Condition to Vehicle Age Exception");
            }
            return true;
        }
        throw new InputFormatException("Condition Exception");
    }

    protected int getDownPayment(int downPayment, int price, boolean isNew) {
        float multiplier;
        if (isNew) {
            multiplier = (float) Constant.MINIMUM_DP_PERCENTAGE_NEW_CONDITION / (float) 100;
        } else {
            multiplier = (float) Constant.MINIMUM_DP_PERCENTAGE_SECOND_CONDITION / (float) 100;
        }
        float minimumDownPayment = (float) price * (float) multiplier;
        if (minimumDownPayment > downPayment) {
            throw new InputFormatException("Minimum Down Payment Exception");
        }
        return downPayment;
    }

    protected int getTenor(int tenor) {
        if (tenor > Constant.MAX_TENOR || tenor < 1) {
            throw new InputFormatException("Maximum Tenor Exception");
        }
        return tenor;
    }

    protected int getCreatedYear(int createdYear) {
        int length = (int) (Math.log10(createdYear) + 1);
        if (length == Constant.CREATED_YEAR_LENGTH) {
            return createdYear;
        }
        throw new InputFormatException("Tahun Kendaraan Exception");
    }

    protected int getPrice(int totalLoan) {
        if (totalLoan > Constant.MAX_TOTAL_LOAN) {
            throw new InputFormatException("Total Loan Exception");
        }
        return totalLoan;
    }
}
