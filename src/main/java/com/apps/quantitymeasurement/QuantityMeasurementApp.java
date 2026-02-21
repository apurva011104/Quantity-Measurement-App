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

    public static class Inches{
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj==null || obj.getClass() != this.getClass()){
                return false;
            }
            Inches inchObject = (Inches) obj;
            return Double.compare(inchObject.value, this.value) == 0;
        }
        
    }

    public static void demonstrateFeetEquality(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter value1 in feet: ");
        double value1 = scanner.nextDouble();
        System.out.print("Enter value2 in feet: ");
        double value2 = scanner.nextDouble();

        Feet feet1 = new Feet(value1);
        Feet feet2 = new Feet(value2);
        System.out.println("Are value1 in feets and value2 in feets equal?: "+feet1.equals(feet2)); 
    }

    public static void demonstrateInchesEquality(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter value1 in inches: ");
        double value1 = scanner.nextDouble();
        System.out.print("Enter value2 in inches: ");
        double value2 = scanner.nextDouble();

        Inches inches1 = new Inches(value1);
        Inches inches2 = new Inches(value2);

        System.out.println("Are value1 in inches and value2 in inches equal?: "+inches1.equals(inches2)); 
    }

    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
    }
}
