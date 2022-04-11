import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G5_2293_동전1 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] dp = new int[K+1];
        dp[0] = 1;
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            for(int j=num; j<=K; j++) {
                dp[j] += dp[j-num];
            }
        }
        System.out.println(dp[K]);
    }
}
