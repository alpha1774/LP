import java.util.*;

public class LRU {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Frames: ");
        int F = sc.nextInt();
        System.out.print("Length of reference string: ");
        int n = sc.nextInt();
        int[] ref = new int[n];
        System.out.println("Enter reference string:");
        for (int i = 0; i < n; i++) ref[i] = sc.nextInt();

        // LinkedHashMap with accessOrder=true gives LRU ordering automatically
        LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>(F, 0.75f, true);
        int faults = 0;

        System.out.println("\nLRU Page Replacement");
        System.out.println("Step\tRef\tFrames");
        int step = 1;

        for (int p : ref) {
            if (!cache.containsKey(p)) {
                faults++;
                if (cache.size() == F) {
                    // remove least-recently-used (head entry)
                    int lruKey = cache.entrySet().iterator().next().getKey();
                    cache.remove(lruKey);
                }
                cache.put(p, 1);
            } else {
                // touch to update recency
                cache.get(p);
            }

            // print current frame contents (left→right oldest→newest)
            List<Integer> frames = new ArrayList<>(cache.keySet());
            while (frames.size() < F) frames.add(-1); // pad for display
            System.out.printf("%d\t%d\t%s%n", step++, p, frames.toString());
        }

        System.out.println("Total Page Faults: " + faults);
        sc.close();
    }
}
