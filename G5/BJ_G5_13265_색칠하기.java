package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_G5_13265_색칠하기 {
    static int N, M;
    static ArrayList<Integer>[] graph;
    static int[] color;
    static boolean isPossible = true;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder ans = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        for(int t=1; t<=TC; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            color = new int[N+1];
            graph = new ArrayList[N+1];
            isPossible = true;

            for(int i=1; i<=N; i++) {
                graph[i] = new ArrayList<>();
            }

            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());

                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());

                graph[s].add(e);
                graph[e].add(s);
            }

            for(int i=1; i<=N; i++) {
                if(!isPossible) break;
                if(color[i] == 0) {
                    color[i] = 1;
                    setColor(i);
                }
            }

            ans.append(isPossible? "possible" : "impossible");
            ans.append(t!=TC ? "\n" : "");
        }
        System.out.printf("%s", ans.toString());
    }

    public static void setColor(int node) {
        if(!isPossible) return;

        for(int i=0; i<graph[node].size(); i++) {
            int nextNode = graph[node].get(i);
            if(color[nextNode] == 0) {
                color[nextNode] = 3 - color[node];
                setColor(nextNode);
            }
            if(color[nextNode] == color[node]) {
                isPossible = false;
                return;
            }
        }
    }
}
