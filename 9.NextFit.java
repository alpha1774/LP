import java.util.*;

public class NextFit {
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
        int j = 0; // start pointer

        for (int i = 0; i < n; i++) {
            int count = 0;
            while (count < m) {
                if (block[j] >= process[i]) {
                    allocation[i] = j;
                    block[j] -= process[i];
                    break;
                }
                j = (j + 1) % m;
                count++;
            }
        }

        System.out.println("\nNext Fit Allocation:");
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
