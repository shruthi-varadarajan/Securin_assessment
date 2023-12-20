import java.util.HashMap;
import java.util.Map;

class Doomed_Dice1 {
    public static void main(String[] args) {
        int A[] = { 1, 2, 3, 4, 5, 6 };
        int B[] = { 1, 2, 3, 4, 5, 6 };

        /*
         * The total number of combinations is the product of the possibile outcomes
         * of each dice
         */
        System.out.printf("\n## ## Total Combinations : %d ## ## \n", A.length * B.length);

        int comb[][] = combinations(A, B);
        System.out.println("\n## ## The Combination Sum Array ## ##\n");
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                System.out.printf("%4d", comb[i][j]);
            }
            System.out.println();
        }

        // Map is used to store the frequencies of the compantions int the 2D array
        // Probablility of a combination is the frequency / total combinations
        Map<Integer, Integer> prob = probability(comb);
        System.out.println("\n## ## The Probability of each combination ## ##\n");
        prob.forEach((key, value) -> System.out.printf("%2d %4d/%d\n", key, value, A.length * B.length));
    }

    // A 2D array is used to stores the sum of each combination
    private static int[][] combinations(int A[], int B[]) {
        int comb[][] = new int[6][6];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                comb[i][j] = A[i] + B[j];
            }
        }

        return comb;
    }

    // Used to find the frequency of the combinations
    private static Map<Integer, Integer> probability(int[][] comb) {
        Map<Integer, Integer> prob = new HashMap<>();
        for (int[] row : comb) {
            for (int element : row) {
                prob.put(element, prob.getOrDefault(element, 0) + 1);
            }
        }
        return prob;
    }
}