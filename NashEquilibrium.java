import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class NashEquilibrium {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        // input
        int n = scanner.nextInt();  // rows
        int m = scanner.nextInt();  // columns

        int[] data = new int[n * m * 2];
        for (int i = 0; i < n * m * 2; i++) {
            data[i] = scanner.nextInt();
        }

        Matrix a = new Matrix(n, m, data);
        a.NashEquilibrium();
    }
}

/**
 * This class represents the combination of strategies chosen by Player 1 and Player 2.
 * It should store four values: the strategy names (format is specified below) and the payoff values
 * for each player. The payoffs are integer numbers.
 */

class StrategyProfile {

    // strategy names
    private String p1Strategy;
    private String p2Strategy;

    // payoff values
    private int p1Payoff;
    private int p2Payoff;

    public StrategyProfile(String p1S, String p2S, int p1P, int p2P) {
        p1Strategy = p1S;
        p2Strategy = p2S;
        p1Payoff = p1P;
        p2Payoff = p2P;
    }

    public int getP1Payoff() { return p1Payoff; }
    public int getP2Payoff() { return p2Payoff; }

    @Override
    public String toString() {
        return "Strategy profile: (" + p1Strategy +
                ", " + p2Strategy + ")" +
                ", Payoff: " + "("+ p1Payoff +
                ", " + p2Payoff +
                ')';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        StrategyProfile that = (StrategyProfile) object;
        return p1Payoff == that.p1Payoff && p2Payoff == that.p2Payoff && Objects.equals(p1Strategy, that.p1Strategy) && Objects.equals(p2Strategy, that.p2Strategy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p1Strategy, p2Strategy, p1Payoff, p2Payoff);
    }
}

/**
 * This class represents the n × m matrix of StrategyProfiles.
 */

class Matrix {

    // instances
    private int n;  // number of rows
    private int m;  // number of columns
    private StrategyProfile[][] matrix;

    // constructor
    public Matrix(int n, int m, int[] data) {
        this.n = n;
        this.m = m;
        matrix = new StrategyProfile[n][m];

        int index = 0;
        for (int row = 0; row < n; row++) {
            for (int column = 0; column < m; column++) {
                matrix[row][column] = new StrategyProfile("X" + row, "Y" + column, data[index], data[index + 1]);
                index += 2;
            }
        }

        // test
        System.out.println("Matrix:");
        for (int row = 0; row < n; row++) {
            for (int column = 0; column < m; column++) {
                System.out.print("(" + matrix[row][column].getP1Payoff() + " " +  matrix[row][column].getP2Payoff() + ") ");
            }
            System.out.println();
        }
    }

    public void NashEquilibrium() {

        HashSet<StrategyProfile> p1maxPayoffNames = new HashSet<>();

        // finding the maximum payoff for player 1 in each column
        int[] p1maxPayoffs = new int[m];
        for (int column = 0; column < m; column++) {

            int currentMaxP1 = matrix[0][column].getP1Payoff();

            // finding the max
            for (int row = 1; row < n; row++) {
                if (matrix[row][column].getP1Payoff() > currentMaxP1) {
                    currentMaxP1 = matrix[row][column].getP1Payoff();
                }
            }
            p1maxPayoffs[column] = currentMaxP1;

            for (int i = 0; i < n; i++) {
                if (matrix[i][column].getP1Payoff() == currentMaxP1)
                    p1maxPayoffNames.add(matrix[i][column]);
            }
        }

        HashSet<StrategyProfile> p2maxPayoffNames = new HashSet<>();

        // finding the maximum payoff for player 2 in each row
        int[] p2maxPayoffs = new int[n];
        for (int row = 0; row < n; row++) {

            int currentMaxP2 = matrix[row][0].getP2Payoff();

            // finding the max
            for (int column = 1; column < m; column++) {
                if (matrix[row][column].getP2Payoff() > currentMaxP2) {
                    currentMaxP2 = matrix[row][column].getP2Payoff();
                }
            }
            p2maxPayoffs[row] = currentMaxP2;

            for (int i = 0; i < m; i++) {
                if (matrix[row][i].getP2Payoff() == currentMaxP2)
                    p2maxPayoffNames.add(matrix[row][i]);
            }
        }

        p1maxPayoffNames.retainAll(p2maxPayoffNames);

        // test

        System.out.println("Nash Equilibrium: ");

        for (StrategyProfile i : p1maxPayoffNames)
            System.out.println(i + " ");
        System.out.println();

        if (p1maxPayoffNames.isEmpty())
            System.out.println("No Nash Equilibrium.");
    }

}
