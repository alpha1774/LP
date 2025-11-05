import java.util.*;

public class SJFPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- INPUT ---
        System.out.print("No. of processes: ");
        int n = sc.nextInt();
        int[] at = new int[n], bt = new int[n], rt = new int[n];
        int[] wt = new int[n], tat = new int[n], ct = new int[n];

        System.out.println("Enter AT BT for P1..Pn:");
        for (int i = 0; i < n; i++) {
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
            rt[i] = bt[i];
        }
        sc.close();

        // --- SRTF CORE ---
        int done = 0, t = 0, last = -1;
        StringBuilder order = new StringBuilder(); // execution order with start times

        while (done < n) {
            // pick arrived process with smallest remaining time
            int idx = -1, min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (at[i] <= t && rt[i] > 0 && rt[i] < min) {
                    min = rt[i];
                    idx = i;
                }
            }

            if (idx == -1) {          // CPU idle
                t++;
                last = -1;            // reset so next real run is recorded
                continue;
            }

            if (idx != last) {        // log context switch
                order.append("t").append(t).append(":P").append(idx + 1).append(" ");
                last = idx;
            }

            rt[idx]--;                // run 1 time unit
            t++;

            if (rt[idx] == 0) {       // finished now
                ct[idx]  = t;
                tat[idx] = ct[idx] - at[idx];
                wt[idx]  = tat[idx] - bt[idx];
                done++;
            }
        }

        // --- OUTPUT ---
        int sumWT = 0, sumTAT = 0;
        System.out.println("\nPID\tAT\tBT\tWT\tTAT");
        for (int i = 0; i < n; i++) {
            sumWT  += wt[i];
            sumTAT += tat[i];
            System.out.printf("%d\t%d\t%d\t%d\t%d%n", (i + 1), at[i], bt[i], wt[i], tat[i]);
        }
        System.out.printf("Average WT  = %.2f%n", sumWT / (double) n);
        System.out.printf("Average TAT = %.2f%n", sumTAT / (double) n);
        System.out.println("Order: " + order.toString());
    }
}
