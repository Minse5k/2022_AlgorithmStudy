package 백준.jlkj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_G3_11437_LCA {
    static int N, M, K;
    static int[][] parent;
    static int[] depth;
    static boolean[] visited;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder ans = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        // 2^K > N인 K 찾기
        K = 0;
        for (int i = 1; i <= N; i *= 2) {
            K++;
        }
        graph = new ArrayList[N+1];
        parent = new int[K][N+1];
        depth = new int[N+1];
        visited = new boolean[N+1];

        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            graph[s].add(e);
            graph[e].add(s);
        }
        getDepth(1, 0);
        getParent();
        M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            ans.append(lca(s, e));
            if(i!=M-1) ans.append("\n");
        }

        System.out.printf("%s", ans.toString());
    }

    public static void getDepth(int node, int nowDepth) {
        visited[node] = true;
        depth[node] = nowDepth;

        for(int next : graph[node]) {
            if(visited[next]) continue;
            parent[0][next] = node;
            getDepth(next, nowDepth+1);
        }
    }

    public static void getParent() {
        for (int i = 1; i<K; i++) {
            for (int j = 1; j<=N; j++) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }
    }

    static int lca(int a, int b) {
        // 1. depth[a] >= depth[b] 이도록 조정하기
        if (depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        // 2. 더 깊은 a를 2^K승 점프하여 depth를 맞추기
        for (int i = K-1; i>=0; i--) {
            if (Math.pow(2, i) <= depth[a] - depth[b]) {
                a = parent[i][a];
            }
        }

        // 3. depth를 맞췄는데 같다면 종료
        if (a == b) return a;

        // 4. a-b는 같은 depth이므로 2^K승 점프하며 공통부모 바로 아래까지 올리기
        for (int i = K-1; i >= 0; i--) {
            if (parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }
        return parent[0][a];
    }
}
