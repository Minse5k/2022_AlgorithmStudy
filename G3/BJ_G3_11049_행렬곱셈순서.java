package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G3_11049_행렬곱셈순서 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] mat = new int[N+1];
        int[][] dp = new int[N+1][N+1];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            mat[i] = Integer.parseInt(st.nextToken());
            mat[i+1] = Integer.parseInt(st.nextToken());
        }

        for(int len=1; len<N; len++) {
            for(int i=1; i<=N-len; i++) {
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;

                for(int k=i; k<j; k++) {
                    int cost = dp[i][k] + dp[k+1][j] + mat[i-1] * mat[k] * mat[j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        System.out.println(dp[1][N]);
    }
}
