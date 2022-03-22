import java.io.BufferedReader;
import java.io.InputStreamReader;

public class bJ_G5_9251_LCS {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        int M = str1.length();
        int N = str2.length();
        int[][]dp = new int[N+1][M+1];

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                if(str1.charAt(j-1) == str2.charAt(i-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        System.out.printf("%d", dp[N][M]);
    }
}
