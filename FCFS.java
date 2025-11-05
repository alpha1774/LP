import java.util.*;

class FCFS {
    static class P {
        int id, at, bt, wt, tat;
        P(int id, int at, int bt){ this.id=id; this.at=at; this.bt=bt; }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<P> ps = new ArrayList<>();
        for(int i=0;i<n;i++){
            int at = sc.nextInt(), bt = sc.nextInt();
            ps.add(new P(i+1, at, bt));
        }
        // FCFS: sort by arrival
        ps.sort(Comparator.comparingInt(p->p.at));

        int t=0, tw=0, tt=0;
        StringBuilder gantt = new StringBuilder("|");
        for(P p: ps){
            if(t < p.at) t = p.at;             // CPU idle
            p.wt = t - p.at;
            t += p.bt;
            p.tat = p.wt + p.bt;
            tw += p.wt; tt += p.tat;
            gantt.append(" P").append(p.id).append(" |");
        }

        System.out.println("PID\tAT\tBT\tWT\tTAT");
        for(P p: ps) System.out.printf("%d\t%d\t%d\t%d\t%d%n", p.id,p.at,p.bt,p.wt,p.tat);
        System.out.printf("Avg WT = %.2f%n", tw/(double)n);
        System.out.printf("Avg TAT = %.2f%n", tt/(double)n);
        System.out.println("Gantt: " + gantt);
    }
}
