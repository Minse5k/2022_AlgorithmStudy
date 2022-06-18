package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_G4_19542_전단지돌리기 {
    static ArrayList<Integer>[] graph;
    static int N, S, D;
    static int[] depth;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        depth = new int[N+1];
        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            graph[s].add(e);
            graph[e].add(s);
        }
        getChildrenCnt(S, 0);

        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (depth[i] >= D && i != S) {
                cnt++;
            }
        }
        System.out.printf("%d", cnt*2);
    }

    public static int getChildrenCnt(int now, int before) {
        int max = 0;
        for (int next : graph[now]) {
            if(next == before) continue;
            max = Math.max(max, getChildrenCnt(next, now) + 1);
        }
        depth[now] = max;
        return max;
    }
}
