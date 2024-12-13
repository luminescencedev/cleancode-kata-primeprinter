package com.cleancode.knuth;

public class PrimePrinterHelper {
    private final int M; // Number of primes to generate
    private final int RR; // Number of rows per page
    private final int CC; // Number of columns per row
    private final int ORDMAX; // Maximum order for prime generation

    private int[] P; // Array to hold prime numbers
    private int PAGENUMBER; // Page number for printing
    private int PAGEOFFSET; // Page offset for printing
    private int ROWOFFSET; // Row offset for printing
    private int C; // Column index for printing
    private int J; // Candidate for the next prime
    private int K; // Index for the current prime
    private boolean JPRIME; // Flag indicating if J is prime
    private int ORD; // Current order of primes
    private int SQUARE; // Square of the current prime
    private int N; // Index for multiples
    private int[] MULT; // Multiples of primes

    public PrimePrinterHelper(int M, int RR, int CC, int ORDMAX) {
        this.M = M;
        this.RR = RR;
        this.CC = CC;
        this.ORDMAX = ORDMAX;
        
        this.P = new int[M + 1];
        this.MULT = new int[ORDMAX + 1];
    }

    public void invoke() {
        generatePrimes(); // Generate the primes
        printPrimes();    // Print the primes
    }

    private void generatePrimes() {
        J = 1;
        K = 1;
        P[1] = 2;
        ORD = 2;
        SQUARE = 9;

        while (K < M) {
            do {
                J += 2;
                if (J == SQUARE) {
                    ORD++;
                    SQUARE = P[ORD] * P[ORD];
                    MULT[ORD - 1] = J;
                }
                N = 2;
                JPRIME = true;
                while (N < ORD && JPRIME) {
                    while (MULT[N] < J)
                        MULT[N] += P[N] + P[N];
                    if (MULT[N] == J)
                        JPRIME = false;
                    N++;
                }
            } while (!JPRIME);
            K++;
            P[K] = J;
        }
    }

    private void printPrimes() {
        PAGENUMBER = 1;
        PAGEOFFSET = 1;
        while (PAGEOFFSET <= M) {
            System.out.print("The First ");
            System.out.print(Integer.toString(M));
            System.out.print(" Prime Numbers === Page ");
            System.out.print(Integer.toString(PAGENUMBER));
            System.out.println("\n");

            for (ROWOFFSET = PAGEOFFSET; ROWOFFSET <= PAGEOFFSET + RR - 1; ROWOFFSET++) {
                for (C = 0; C <= CC - 1; C++) {
                    if (ROWOFFSET + C * RR <= M)
                        System.out.printf("%10d", P[ROWOFFSET + C * RR]);
                }
                System.out.println();
            }
            System.out.println("\f");
            PAGENUMBER++;
            PAGEOFFSET += RR * CC;
        }
    }

    public static void main(String[] args) {
        final int M = 1000;
        final int RR = 50;
        final int CC = 4;
        final int ORDMAX = 30;

        PrimePrinterHelper helper = new PrimePrinterHelper(M, RR, CC, ORDMAX);
        helper.invoke();
    }
}
