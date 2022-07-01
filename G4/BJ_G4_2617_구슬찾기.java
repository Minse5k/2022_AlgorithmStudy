package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ_G4_2617_구슬찾기 {
    static int N, M;
    static ArrayList<Integer>[] heavyList;
    static ArrayList<Integer>[] lightList;
    static HashSet<Integer> set = new HashSet<>();
    static boolean[] visited;
    static boolean isTrue;
    static int cnt = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        heavyList = new ArrayList[N + 1];
        lightList = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            heavyList[i] = new ArrayList<>();
            lightList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            lightList[s].add(e);
            heavyList[e].add(s);
        }

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            isTrue = false;
            visited[i] = true;
            cnt = 1;
            lightDfs(i);

            cnt = 1;
            visited = new boolean[N + 1];
            visited[i] = true;
            heavyDfs(i);

            if (isTrue) {
                set.add(i);
            }
        }

        System.out.printf("%d", set.size());
    }

    public static void lightDfs(int now) {
        if (isTrue) return;
        if (cnt > (N + 1) / 2) {
            isTrue = true;
            return;
        }

        for (int next : lightList[now]) {
            if (visited[next]) continue;
            visited[next] = true;
            cnt++;
            lightDfs(next);
        }
    }

    public static void heavyDfs(int now) {
        if (isTrue) return;
        if (cnt > (N + 1) / 2) {
            isTrue = true;
            return;
        }

        for (int next : heavyList[now]) {
            if (visited[next]) continue;
            visited[next] = true;
            cnt++;
            heavyDfs(next);
        }
    }
}
