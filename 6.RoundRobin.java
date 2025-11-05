import java.util.*;

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Take inputs
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        System.out.print("Enter time quantum: ");
        int tq = sc.nextInt();

        int[] at = new int[n];  // arrival time
        int[] bt = new int[n];  // burst time
        int[] rt = new int[n];  // remaining time
        int[] wt = new int[n];  // waiting time
        int[] tat = new int[n]; // turnaround time
        int[] ct = new int[n];  // completion time

        System.out.println("Enter Arrival Time and Burst Time:");
        for (int i = 0; i < n; i++) {
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
            rt[i] = bt[i];
        }

        int time = 0, done = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);

        String gantt = "";
        boolean[] visited = new boolean[n];
        visited[0] = true;

        // Step 2: Round Robin logic
        while (!q.isEmpty()) {
            int i = q.poll();

            // execute process for time quantum or remaining time
            if (rt[i] > tq) {
                gantt += "P" + (i + 1) + " ";
                time += tq;
                rt[i] -= tq;
            } else {
                gantt += "P" + (i + 1) + " ";
                time += rt[i];
                rt[i] = 0;
                done++;
                ct[i] = time;
                tat[i] = ct[i] - at[i];
                wt[i] = tat[i] - bt[i];
            }

            // check newly arrived processes
            for (int j = 0; j < n; j++) {
                if (at[j] <= time && rt[j] > 0 && !visited[j]) {
                    q.add(j);
                    visited[j] = true;
                }
            }

            // re-add same process if it’s not finished
            if (rt[i] > 0) q.add(i);

            // if queue empty, jump to next process
            if (q.isEmpty()) {
                for (int j = 0; j < n; j++) {
                    if (rt[j] > 0) {
                        q.add(j);
                        visited[j] = true;
                        break;
                    }
                }
            }
        }

        // Step 3: Print results
        System.out.println("\nPID\tAT\tBT\tWT\tTAT\tCT");
        int totalWT = 0, totalTAT = 0;
        for (int i = 0; i < n; i++) {
            totalWT += wt[i];
            totalTAT += tat[i];
            System.out.println((i + 1) + "\t" + at[i] + "\t" + bt[i] + "\t" + wt[i] + "\t" + tat[i] + "\t" + ct[i]);
        }

        System.out.printf("\nAverage WT = %.2f\n", totalWT / (float) n);
        System.out.printf("Average TAT = %.2f\n", totalTAT / (float) n);
        System.out.println("\nGantt Chart: " + gantt);
    }
}
