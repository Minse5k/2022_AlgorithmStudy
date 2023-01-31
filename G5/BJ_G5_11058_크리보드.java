import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] dp = new long[N+1];

		for(int i=1; i<=N; i++) {
			dp[i] = dp[i-1] + 1;
			for(int j=3; j<=i; j++) {
				dp[i] = Math.max(dp[i], (j-1)*dp[i-j]);
			}
		}
		
		System.out.println(dp[N]);
		
	}
}
