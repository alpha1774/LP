import java.util.*;

public class Optimal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Frames: ");
        int F = sc.nextInt();
        System.out.print("Length of reference string: ");
        int n = sc.nextInt();
        int[] ref = new int[n];
        System.out.println("Enter reference string:");
        for (int i = 0; i < n; i++) ref[i] = sc.nextInt();

        List<Integer> frames = new ArrayList<>(F);
        int faults = 0;

        System.out.println("\nOptimal Page Replacement");
        System.out.println("Step\tRef\tAction\t\tFrames");
        int step = 1;

        for (int i = 0; i < n; i++) {
            int p = ref[i];
            if (frames.contains(p)) {
                System.out.printf("%d\t%d\tHIT\t\t%s%n", step++, p, frames.toString());
                continue;
            }
            faults++;
            if (frames.size() < F) {
                frames.add(p);
                System.out.printf("%d\t%d\tFAULT(add)\t%s%n", step++, p, frames.toString());
            } else {
                // choose victim: page used farthest in future (or never)
                int victimIdx = -1, farthest = -1;
                for (int f = 0; f < frames.size(); f++) {
                    int page = frames.get(f);
                    int nextUse = Integer.MAX_VALUE;
                    for (int k = i + 1; k < n; k++) {
                        if (ref[k] == page) { nextUse = k; break; }
                    }
                    if (nextUse > farthest) { farthest = nextUse; victimIdx = f; }
                }
                int victim = frames.get(victimIdx);
                frames.set(victimIdx, p);
                System.out.printf("%d\t%d\tFAULT(%d→%d)\t%s%n", step++, p, victim, p, frames.toString());
            }
        }

        System.out.println("Total Page Faults: " + faults);
        sc.close();
    }
}
