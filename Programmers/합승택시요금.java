import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    static class Node implements Comparable<Node>{
        int to;
        int cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }


        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
   public static int solution(int n, int s, int a, int b, int[][] fares) {
        ArrayList<Node>[] list = new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
            list[i] = new ArrayList<Node>();
        }

        for(int i=0; i<fares.length; i++) {
            int st = fares[i][0];
            int end = fares[i][1];
            int c = fares[i][2];

            list[st].add(new Node(end, c));
            list[end].add(new Node(st, c));
        }

        int[] S = dijkstra(s, n, list);
        int[] A = dijkstra(a, n, list);
        int[] B = dijkstra(b, n, list);

        int min = Integer.MAX_VALUE;

        for(int i=1; i<=n; i++) {
            if(min > S[i] + A[i] + B[i]) {
                min = S[i] + A[i] + B[i];
            }
        }

        return min;
    }

    public static int[] dijkstra(int start, int n, ArrayList<Node>[] list) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[n+1];
        boolean[] visited = new boolean[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[start] = 0;
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(visited[now.to]) continue;
            visited[now.to] = true;

            for(Node next : list[now.to]) {
                if(visited[next.to] || dist[next.to] <= now.cost + next.cost) continue;
                dist[next.to] = now.cost + next.cost;
                pq.add(new Node(next.to, dist[next.to]));
            }
        }

        return dist;
    }
}
