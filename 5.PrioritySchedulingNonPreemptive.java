import java.util.*;

public class PriorityNonPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1) INPUT
        System.out.print("No. of processes: ");
        int n = sc.nextInt();

        int[] at = new int[n];   // arrival
        int[] bt = new int[n];   // burst
        int[] pr = new int[n];   // priority (smaller = higher)
        int[] wt = new int[n];   // waiting
        int[] tat = new int[n];  // turnaround
        boolean[] done = new boolean[n];
        StringBuilder order = new StringBuilder("|");

        System.out.println("Enter AT BT PR for each process (P1..Pn):");
        for (int i = 0; i < n; i++) {
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
            pr[i] = sc.nextInt();
        }
        sc.close();

        // 2) CORE LOGIC: at each time, pick available process with HIGHEST priority
        int time = 0, finished = 0;
        while (finished < n) {
            int idx = -1;
            int best = Integer.MAX_VALUE;    // smaller value = higher priority

            for (int i = 0; i < n; i++) {
                if (!done[i] && at[i] <= time) {
                    if (pr[i] < best) {      // flip sign if larger number = higher priority
                        best = pr[i];
                        idx = i;
                    }
                }
            }

            if (idx == -1) { time++; continue; }  // no job ready → CPU idle, move time

            wt[idx]  = time - at[idx];          // waiting before it starts
            time    += bt[idx];                  // run it to completion (non-preemptive)
            tat[idx] = wt[idx] + bt[idx];
            done[idx] = true;
            finished++;
            order.append(" P").append(idx + 1).append(" |");
        }

        // 3) OUTPUT: per process + averages + simple Gantt order
        int sumWT = 0, sumTAT = 0;
        System.out.println("\nPID\tAT\tBT\tPR\tWT\tTAT");
        for (int i = 0; i < n; i++) {
            sumWT += wt[i]; sumTAT += tat[i];
            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d%n", (i+1), at[i], bt[i], pr[i], wt[i], tat[i]);
        }
        System.out.printf("Average WT  : %.2f%n", sumWT / (double) n);
        System.out.printf("Average TAT : %.2f%n", sumTAT / (double) n);
        System.out.println("Order (Gantt): " + order);
    }
}
