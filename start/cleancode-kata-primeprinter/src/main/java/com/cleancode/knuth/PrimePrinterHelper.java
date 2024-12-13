package com.cleancode.knuth;

public class PrimePrinterHelper {
    private final int numberOfNumbers; // Number of primes to generate
    private final int linesPerPage; // Number of rows per page
    private final int columns; // Number of columns per row
    private final int maxOrder; // Maximum order for prime generation

    private int[] primes; // Array to hold prime numbers
    private int candidate; // Candidate for the next prime
    private int primeIndex; // Index for the current prime
    private boolean isPrime; // Flag indicating if candidate is prime
    private int order; // Current order of primes
    private int square; // Square of the current prime
    private int multiplesIndex; // Index for multiples
    private int[] multiples; // Multiples of primes

    public PrimePrinterHelper(int numberOfNumbers, int linesPerPage, int columns, int maxOrder) {
        this.numberOfNumbers = numberOfNumbers;
        this.linesPerPage = linesPerPage;
        this.columns = columns;
        this.maxOrder = maxOrder;
        
        this.primes = new int[numberOfNumbers + 1];
        this.multiples = new int[maxOrder + 1];
    }

    public int[] generatePrimes() {
        candidate = 1;
        primeIndex = 1;
        primes[1] = 2;
        order = 2;
        square = 9;

        while (primeIndex < numberOfNumbers) {
            do {
                candidate += 2;
                if (candidate == square) {
                    order++;
                    square = primes[order] * primes[order];
                    multiples[order - 1] = candidate;
                }
                multiplesIndex = 2;
                isPrime = true;
                while (multiplesIndex < order && isPrime) {
                    while (multiples[multiplesIndex] < candidate)
                        multiples[multiplesIndex] += primes[multiplesIndex] + primes[multiplesIndex];
                    if (multiples[multiplesIndex] == candidate)
                        isPrime = false;
                    multiplesIndex++;
                }
            } while (!isPrime);
            primeIndex++;
            primes[primeIndex] = candidate;
        }
        return primes;
    }

    public static void main(String[] args) {
        final int numberOfNumbers = 1000;
        final int linesPerPage = 50;
        final int columns = 4;
        final int maxOrder = 30;

        PrimePrinterHelper helper = new PrimePrinterHelper(numberOfNumbers, linesPerPage, columns, maxOrder);
        int[] primes = helper.generatePrimes();

        // Create NumberPrinter and pass the required arguments to print primes
        NumberPrinter printer = new NumberPrinter(linesPerPage, columns);
        printer.printNumbers(primes, numberOfNumbers);
    }

    // NumberPrinter class moved to the top level and is now independent
    public static class NumberPrinter {
        private final int linesPerPage; // Number of rows per page
        private final int columns; // Number of columns per row

        public NumberPrinter(int linesPerPage, int columns) {
            this.linesPerPage = linesPerPage;
            this.columns = columns;
        }

        public void printNumbers(int[] numbers, int numberOfNumbers) {
            int pageNumber = 1;
            int pageOffset = 1;
            while (pageOffset <= numberOfNumbers) {
                System.out.print("The First ");
                System.out.print(Integer.toString(numberOfNumbers));
                System.out.print(" Prime Numbers === Page ");
                System.out.print(Integer.toString(pageNumber));
                System.out.println("\n");

                for (int rowOffset = pageOffset; rowOffset <= pageOffset + linesPerPage - 1; rowOffset++) {
                    for (int column = 0; column <= columns - 1; column++) {
                        if (rowOffset + column * linesPerPage <= numberOfNumbers)
                            System.out.printf("%10d", numbers[rowOffset + column * linesPerPage]);
                    }
                    System.out.println();
                }
                System.out.println("\f");
                pageNumber++;
                pageOffset += linesPerPage * columns;
            }
        }
    }
}
