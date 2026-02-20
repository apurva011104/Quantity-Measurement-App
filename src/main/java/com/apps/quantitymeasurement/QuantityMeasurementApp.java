package com.apps.quantitymeasurement;

import java.util.Scanner;

public class QuantityMeasurementApp {
    public static class Feet{
        private final double value;
        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj==null || obj.getClass() != this.getClass()){
                return false;
            }
            Feet feetObject = (Feet) obj;
            return Double.compare(feetObject.value, this.value) == 0;

        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter value1 in feet: ");
        double value1 = scanner.nextDouble();
        System.out.print("Enter value2 in feet: ");
        double value2 = scanner.nextDouble();

        Feet feet1 = new Feet(value1);
        Feet feet2 = new Feet(value2);
        System.out.println(feet1.equals(feet2)); 
    }
}
