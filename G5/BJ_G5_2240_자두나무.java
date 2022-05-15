import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G5_2240_자두나무 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] arr = new int[T+1];
        int[][][] dp = new int[3][T + 1][W + 1];

        for (int i = 1; i <= T; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        if (arr[1] == 1) {
            dp[1][1][0] = 1;
            dp[2][1][1] = 0;
        } else if (arr[1] == 2) {
            dp[1][1][0] = 0;
            dp[2][1][1] = 1;
        }

        for (int i = 2; i <= T; i++) {
            if (arr[i] == 1) {
                dp[1][i][0] = dp[1][i - 1][0] + 1;
                dp[2][i][0] = dp[2][i - 1][0];
                for(int j=1; j<=W; j++) {
                    dp[1][i][j] = Math.max(dp[1][i-1][j], dp[2][i-1][j-1]) + 1;
                    dp[2][i][j] = Math.max(dp[1][i-1][j-1], dp[2][i-1][j]);
                }
            } else if (arr[i] == 2) {
                dp[1][i][0] = dp[1][i - 1][0];
                dp[2][i][0] = dp[2][i - 1][0] + 1;
                for(int j=1; j<=W; j++) {
                    dp[1][i][j] = Math.max(dp[1][i-1][j], dp[2][i-1][j-1]);
                    dp[2][i][j] = Math.max(dp[1][i-1][j-1], dp[2][i-1][j]) + 1;
                }
            }
        }
        int max = 0;
        for (int w = 0; w <= W; w++) {
            max = Math.max(max, Math.max(dp[1][T][w], dp[2][T][w]));
        }
        System.out.printf("%d", max);
    }
}
