import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G5_13023_ABCDE {
    static ArrayList<Integer>[] list;
    static boolean[] visited;
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N];
        for(int i=0; i<N; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            list[s].add(e);
            list[e].add(s);
        }

        for(int i=0; i<N; i++) {
            visited = new boolean[N];
            visited[i] = true;
            dfs(i, 0);
        }

        System.out.println(0);
    }

    private static void dfs(int num, int depth) {
        if(depth == 4) {
            System.out.println(1);
            System.exit(0);
        }

        for(int i=0; i<list[num].size(); i++) {
            int nextNum = list[num].get(i);
            if(visited[nextNum]) continue;
            visited[nextNum] = true;
            dfs(nextNum, depth+1);
            visited[nextNum] = false;
        }
    }
}
