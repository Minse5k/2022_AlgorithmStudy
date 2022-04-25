import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G4_1504_특정한최단경로 {
    static class Node implements Comparable<Node>{
        int end;
        int cost;

        Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    static ArrayList<Node>[] list;
    static int N, E;
    static final int INF = 200000000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dist1, distV1, distV2;
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        list = new ArrayList[N+1];
        dist1 = new int[N+1];
        distV1 = new int[N+1];
        distV2 = new int[N+1];

        for(int i=1; i<=N; i++) {
            list[i] = new ArrayList<>();
            dist1[i] = INF;
            distV1[i] = INF;
            distV2[i] = INF;
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }
        st = new StringTokenizer(br.readLine());

        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        dijkstra(1, dist1);
        dijkstra(v1, distV1);
        dijkstra(v2, distV2);

        // 1 > v1 > v2 > N
        int sum1 =  dist1[v1] + distV1[v2] + distV2[N];
        // 1 > v2 > v1 > N
        int sum2 =   dist1[v2] + distV2[v1] + distV1[N];
        int ans = Math.min(sum1, sum2);

        if(ans >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }
    private static void dijkstra(int start, int[] dist) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if(now.cost > dist[now.end]) {
                continue;
            }

            int len = list[now.end].size();

            for(int i=0; i<len; i++) {
                Node next = list[now.end].get(i);

                if(dist[next.end] <= now.cost + next.cost) continue;
                dist[next.end] = now.cost + next.cost;
                pq.add(new Node(next.end, now.cost+next.cost));
            }
        }

    }
}
