package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ_G3_2026_소풍 {
    static int K, N, F;
    static ArrayList<Integer>[] graph;
    static HashSet<Integer>[] isFriend;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        isFriend = new HashSet[N+1];
        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
            isFriend[i] = new HashSet<>();
        }

        for(int i=0; i<F; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            graph[s].add(e);
            graph[e].add(s);
            isFriend[s].add(e);
            isFriend[e].add(s);
        }

        combination(1, 0, new int[K]);
        System.out.println("-1");
    }

    public static void combination(int start, int cnt, int[] arr) {
        if(cnt == K) {
            if(isCycle(arr)) {
                for(int i=0; i<arr.length; i++) {
                    System.out.println(arr[i]);
                }
                System.exit(0);
            }
            return;
        }

        for(int i=start; i<=N; i++) {
            if(graph[i].size() < K-1) continue;
            arr[cnt] = i;
            combination(i+1, cnt+1, arr);
        }
    }

    public static boolean isCycle(int[] arr) {
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr.length; j++) {
                if(i==j) continue;
                if(!isFriend[arr[i]].contains(arr[j])) {
                    return false;
                }
            }
        }
        return true;
    }
}
