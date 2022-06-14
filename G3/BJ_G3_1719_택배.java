package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G3_1719_택배 {
    static class Node{
        int v; //간선
        int cost; //가중치

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "v=" + v +
                    ", cost=" + cost +
                    '}';
        }
    }
    static int N, M;
    static ArrayList<Node>[] graph;
    static int[] dist, path;
    static boolean[] visited;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[s].add(new Node(e, c));
            graph[e].add(new Node(s, c));
        }

        for(int i=1; i<=N; i++) {
            dijkstra(i);
        }
        System.out.println(ans.toString());
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        q.add(new Node(start, 0));
        dist = new int[N+1];
        visited = new boolean[N+1];
        path = new int[N+1];

        for(int i=1; i<=N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        path[start] = start;
        dist[start] = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (visited[now.v]) {
                continue;
            }
            visited[now.v] = true;

            for (Node next : graph[now.v]) {
                if (!visited[next.v] && dist[next.v] > now.cost + next.cost) {
                    dist[next.v] = now.cost + next.cost;
                    path[next.v] = now.v;
                    q.add(new Node(next.v, dist[next.v]));
                }
            }
        }

        for(int i=1; i<=N; i++) {
            if(i==start) ans.append("-");
            else ans.append(dfs(start, i));

            if(i != N) ans.append(" ");
            else if(start != N) ans.append("\n");
        }
    }

    public static int dfs(int start, int i) {
        if(path[i] == start) return i;
        return dfs(path[i], start);
    }
}
