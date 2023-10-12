package com.bca.vehicleloan;

import java.io.BufferedReader;
import java.io.FileReader;

import com.bca.vehicleloan.model.ILoan;
import com.bca.vehicleloan.model.VehicleLoan;

public class App {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java -jar NamaAplikasi.jar <nama_file>");
            System.exit(1);
        }
        String fileName = args[0];
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            System.out.println("Isi file " + fileName + ":");
            String[] inputs = new String[6];
            int idx = 0;
            while ((line = bufferedReader.readLine()) != null) {
                inputs[idx] = line;
                idx++;
            }
            bufferedReader.close();
            String format = "%-15s%s%n";
            String price = inputs[0];
            System.out.printf(format, "price", price);
            String vechicleType = inputs[1];
            System.out.printf(format, "vechicleType", vechicleType);
            String condition = inputs[2];
            System.out.printf(format, "condition", condition);
            String createdYear = inputs[3];
            System.out.printf(format, "createdYear", createdYear);
            String downPayment = inputs[4];
            System.out.printf(format, "downPayment", downPayment);
            String tenor = inputs[5];
            System.out.printf(format, "tenor", tenor);
            ILoan loan = new VehicleLoan(Integer.parseInt(price), vechicleType, condition,
                    Integer.parseInt(createdYear), Integer.parseInt(downPayment), Integer.parseInt(tenor));
            loan.getInstallment().monltyInstallment();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
