import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] stepArray;
	static int[][][] dp;
	static int N;
	
	static public int solution(int left, int right, int i) {
		
		if(i==N) return 0;
		if(dp[left][right][i] != -1) return dp[left][right][i];
		
		
		if(left == 0 && right == 0) {
			return dp[left][right][i] = solution(stepArray[i], right, i + 1) + 2;
		}
		
		if(left == stepArray[i] || right == stepArray[i]) {
			return dp[left][right][i] = solution(left, right, i + 1) + 1;
		}
		
		int nextLeft = 0;
		if(left == 0) {
			nextLeft = solution(stepArray[i], right, i+1) + 2;
		} else if(Math.abs(left - stepArray[i]) == 2) {
			nextLeft = solution(stepArray[i], right, i+1) + 4;
		} else {
			nextLeft = solution(stepArray[i], right, i+1) + 3;
		}
		
		int nextRight = 0;
		if(right == 0) {
			nextRight = solution(left, stepArray[i], i+1) + 2;
		} else if(Math.abs(right - stepArray[i]) == 2) {
			nextRight = solution(left, stepArray[i], i+1) + 4;
		} else {
			nextRight = solution(left, stepArray[i], i+1) + 3;
		}
		return dp[left][right][i] = Math.min(nextLeft, nextRight);
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = st.countTokens()-1;
		stepArray = new int[N];
		dp = new int[5][5][N];
		
		for(int i=0; i<N; i++) {
			stepArray[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		
		System.out.printf("%d", solution(0, 0, 0));
	}
}
