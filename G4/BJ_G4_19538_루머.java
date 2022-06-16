package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_G4_19538_루머 {
    static int N, M;
    static ArrayList<Integer>[] graph;
    static Queue<Integer> q;
    static int[] time;
    static int[] neighbor;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder ans = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        time = new int[N+1];
        neighbor = new int[N+1];
        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
            time[i] = -1;
        }

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            while(true) {
                int a = Integer.parseInt(st.nextToken());
                if(a==0) break;
                graph[i].add(a);
            }
        }

        M = Integer.parseInt(br.readLine());
        q = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            int node = Integer.parseInt(st.nextToken());
            time[node] = 0;
            q.add(node);
        }

        bfs();
        for(int i=1; i<=N; i++) {
            ans.append(time[i]);
            if(i!=N) ans.append(" ");
        }
        System.out.printf("%s", ans.toString());
    }

    public static void bfs() {
        while(!q.isEmpty()) {
            int nowNode = q.poll();

            for(int next: graph[nowNode]) {
                //이웃에게 난 루머를 알고있다고 전파
                neighbor[next]++;

                //시간이 기록 돼 있지 않고 && 본인 노드 수 / 2 <= 본인 이웃 노드가 알고있는 루머 수
                if(time[next] == -1 && (graph[next].size() + 1)/2 <= neighbor[next]) {
                    q.add(next);
                    time[next] = time[nowNode]+1;
                }
            }
        }
    }
}
