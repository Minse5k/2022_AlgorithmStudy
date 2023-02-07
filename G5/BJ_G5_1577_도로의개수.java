import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	final static int RIGHT = 0;
	final static int DOWN = 1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		long[][] dp = new long[N+1][M+1];
		boolean[][][] isConstruction = new boolean[N+1][M+1][2];
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int b = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a==c) {
				isConstruction[a][Math.max(b,  d)][RIGHT] = true;
			} else {
				isConstruction[Math.max(a,  c)][b][DOWN] = true;
			}
		}
		
		for(int i=1; i<=N; i++) {
			if(isConstruction[i][0][DOWN]) break;
			dp[i][0] = 1;
		}
		
		for(int i=1; i<=M; i++) {
			if(isConstruction[0][i][RIGHT]) break;
			dp[0][i] = 1;
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(!isConstruction[i][j][DOWN]) dp[i][j] += dp[i-1][j];
				if(!isConstruction[i][j][RIGHT]) dp[i][j] += dp[i][j-1];
			}
		}
//		for(int i=0; i<=N; i++) {
//			for(int j=0; j<=M; j++) {
//				System.out.printf("%d ", dp[i][j]);
//			}
//			System.out.println();
//		}
		System.out.println(dp[N][M]);
	}
}
