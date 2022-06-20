package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G2_1781_컵라면 {
    static class Cup implements Comparable<Cup> {
        int deadLine;
        int cupCnt;

        Cup(int deadLine, int cupCnt) {
            this.deadLine = deadLine;
            this.cupCnt = cupCnt;
        }

        @Override
        public int compareTo(Cup o) {
            return this.deadLine - o.deadLine;
        }

    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Cup> pq = new PriorityQueue<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int cupCnt = Integer.parseInt(st.nextToken());

            pq.add(new Cup(deadLine, cupCnt));
        }

        PriorityQueue<Integer> tmpPq = new PriorityQueue<>();
        while(!pq.isEmpty()) {
            Cup now = pq.poll();
            tmpPq.add(now.cupCnt);
            while(tmpPq.size() > now.deadLine) {
                tmpPq.poll();
            }
        }

        int sum = 0;
        while(!tmpPq.isEmpty()) {
            sum += tmpPq.poll();
        }

        System.out.println(sum);
    }
}
