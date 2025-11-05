import java.util.*;

public class FIFO {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Frames: ");
        int F = sc.nextInt();
        System.out.print("Length of reference string: ");
        int n = sc.nextInt();
        int[] ref = new int[n];
        System.out.println("Enter reference string:");
        for (int i = 0; i < n; i++) ref[i] = sc.nextInt();

        Set<Integer> inFrames = new HashSet<>();
        Queue<Integer> q = new ArrayDeque<>();
        int faults = 0;

        for (int p : ref) {
            if (!inFrames.contains(p)) {
                faults++;
                if (inFrames.size() < F) {
                    inFrames.add(p);
                    q.add(p);
                } else {
                    int victim = q.remove();
                    inFrames.remove(victim);
                    inFrames.add(p);
                    q.add(p);
                }
            }
        }

        System.out.println("\nFIFO Page Replacement");
        System.out.println("Total Page Faults: " + faults);
        sc.close();
    }
}
