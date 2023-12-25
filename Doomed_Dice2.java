import java.util.HashMap;
import java.util.Map;

public class Doomed_Dice2 {
    // Initial regular dice
    static int dice_A[] = { 1, 2, 3, 4, 5, 6 };
    static int dice_B[] = { 1, 2, 3, 4, 5, 6 };

    /*
     * The arrays dotts_a and dotts_b give us the number of dots the faces of dice A
     * and dice B can have respectively
     */
    static int dotts_a[] = { 1, 2, 3, 4 };
    static int dotts_b[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
    static Map<Integer, Integer> freq = new HashMap<>();
    static Map<Integer, Integer> new_freq = new HashMap<>();
    static int count = 0;

    public static void main(String[] args) {
        for (int i = 0; i < dice_A.length; i++) {
            for (int j = 0; j < dice_B.length; j++) {
                int sum = dice_A[i] + dice_B[j];
                freq.put(sum, freq.getOrDefault(sum, 0) + 1);
            }
        }
        
        // The smallest and the largest sum are 2 and 12 respectively
        // The dice arrays must have 1,4 and 1,8 at their ends
        // Since the maximum number of dots on a face of Die a is 4
        // So the numbers 1 and 4 must be present at the two ends of die A
        int new_die_A[] = { 1, 0, 0, 0, 0, 4 };
        conf_A(new_die_A, 1);
        System.out.printf("Number of new valid combinations : %d\n", count);
    }

    // Running through all the possible values of Die A through recursion
    private static void conf_A(int[] new_die_A, int indexA) {
        if (indexA == 5) {
            // The smallest and the largest sums are 2 and 12 respectively
            // The dice arrays must have 1,4 and 1,8 at their ends
            // Since the maximum number of dots on a face of Die B is not limited(but anything over 8 will produce results greater than 12)
            // So the numbers 1 and 8 must be present in the ends of die B
            conf_B(new_die_A, new int[] { 1, 0, 0, 0, 0, 8 }, 1);
            return;
        }
        for (int i = 0; i < dotts_a.length; i++) {
            new_die_A[indexA] = dotts_a[i];
            conf_A(new_die_A, indexA + 1);
        }
    }

    // Running through all the possible values of Die B through recursion
    private static void conf_B(int[] new_die_A, int[] new_die_B, int index) {
        if (index == 5) {
            // Function call to check for the combinations
            checkCombination(new_die_A, new_die_B);
            return;
        }
        for (int i = 0; i < dotts_b.length; i++) {
            new_die_B[index] = dotts_b[i];
            conf_B(new_die_A, new_die_B, index + 1);
        }
    }

    // This function is to calculate and compare the combination of the new Dice
    private static void checkCombination(int[] new_die_A, int[] new_die_B) {
        //clearing new_freq hashmap to remove previous values
        new_freq.clear();
        boolean flag = true;
        for (int i : new_die_A) {
            for (int j : new_die_B) {
                new_freq.put(i + j, new_freq.getOrDefault(i + j, 0) + 1);
                if(new_freq.get(i+j)>freq.getOrDefault(i+j,0)){
                    flag=false;
                    break;
                }
            }
            if(!flag){
                break;
            }
        }
// the new set of dice is valid if new_freq matches the freq hashmap 
        if (flag) {
            count++;
            System.out.println("## Valid combination ##");
            System.out.print("New_dice_A : ");
            for (int value : new_die_A) {
                System.out.print(value + " ");
            }
            System.out.println();
            System.out.print("New_dice_B : ");
            for (int value : new_die_B) {
                System.out.print(value + " ");
            }
            System.out.println("\n");
        }
    }
}
