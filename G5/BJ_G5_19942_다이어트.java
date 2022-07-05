package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G5_19942_다이어트 {
    static int N;
    static int mp, mf, ms, mv;
    static int[][] arr;
    static int min = Integer.MAX_VALUE;
    static ArrayList<Integer> list = new ArrayList<>();
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[N][5];

        mp = Integer.parseInt(st.nextToken());
        mf = Integer.parseInt(st.nextToken());
        ms = Integer.parseInt(st.nextToken());
        mv = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
            arr[i][3] = Integer.parseInt(st.nextToken());
            arr[i][4] = Integer.parseInt(st.nextToken());
        }

        Combination(0,0, new int[N], 0, 0, 0, 0, 0);

        if(list.size() == 0) {
            ans.append("-1");
        } else {
            ans.append(min + "\n");
            for(int i=0; i<list.size(); i++) {
                ans.append(list.get(i)+1);
                if(i!=list.size()-1) ans.append(" ");
            }
        }


        System.out.printf("%s", ans.toString());
    }

    public static void Combination(int start, int cnt, int[] per, int sum, int p, int f, int s, int v) {
        if(p>=mp && f>=mf && s>=ms && v>=mv && sum < min) {
            list = new ArrayList<>();
            min = sum;
            for(int i=0; i<cnt; i++) {
                list.add(per[i]);
            }
            return;
        }

        for(int i=start; i<N; i++) {
            if(sum + arr[i][4] >= min) continue;
            per[cnt] = i;
            Combination(i+1, cnt+1, per, sum+arr[i][4], p+arr[i][0], f+arr[i][1], s+arr[i][2], v+arr[i][3]);
        }
    }
}
