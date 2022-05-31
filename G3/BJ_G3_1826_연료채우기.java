package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G3_1826_연로채우기 {
    static class GasStation implements Comparable<GasStation>{
        int dist;
        int fuel;

        GasStation(int dist, int fuel) {
            this.dist = dist;
            this.fuel = fuel;
        }

        @Override
        public String toString() {
            return "GasStation{" +
                    "dist=" + dist +
                    ", fuel=" + fuel +
                    '}';
        }

        @Override
        public int compareTo(GasStation o) {
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<GasStation> distPq = new PriorityQueue<>();
        PriorityQueue<Integer> fuelPq = new PriorityQueue<>(Collections.reverseOrder());

        GasStation end = null;
        for(int i=0; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());

            if(i==N) {
                end = new GasStation(d, f);
                distPq.add(new GasStation(d, 0));
                continue;
            }
            distPq.add(new GasStation(d, f));
        }

        int nowFuel = end.fuel;
        int nowDist = 0;
        int stopCnt = 0;
        while(!distPq.isEmpty()) {
            if(distPq.peek().dist <= nowFuel+nowDist) {
                GasStation now = distPq.poll();
                nowFuel -= (now.dist - nowDist);
                nowDist = now.dist;
                fuelPq.add(now.fuel);
            } else if(!fuelPq.isEmpty()){
                nowFuel += fuelPq.poll();
                stopCnt++;
            } else {
                System.out.println("-1");
                System.exit(0);
            }
        }
        System.out.printf("%d", stopCnt);
    }
}
