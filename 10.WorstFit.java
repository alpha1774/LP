import java.util.*;

public class WorstFit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of memory blocks: ");
        int m = sc.nextInt();
        int[] block = new int[m];
        System.out.println("Enter block sizes:");
        for (int i = 0; i < m; i++) block[i] = sc.nextInt();

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        int[] process = new int[n];
        System.out.println("Enter process sizes:");
        for (int i = 0; i < n; i++) process[i] = sc.nextInt();

        int[] allocation = new int[n];
        Arrays.fill(allocation, -1);

        for (int i = 0; i < n; i++) {
            int worstIdx = -1;
            for (int j = 0; j < m; j++) {
                if (block[j] >= process[i]) {
                    if (worstIdx == -1 || block[j] > block[worstIdx])
                        worstIdx = j;
                }
            }
            if (worstIdx != -1) {
                allocation[i] = worstIdx;
                block[worstIdx] -= process[i];
            }
        }

        System.out.println("\nWorst Fit Allocation:");
        System.out.println("Process\tSize\tBlock");
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + "\t" + process[i] + "\t");
            if (allocation[i] != -1)
                System.out.println(allocation[i] + 1);
            else
                System.out.println("Not Allocated");
        }
        sc.close();
    }
}
