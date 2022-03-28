import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static boolean[][] ladder;
	static int N, M, H;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = toInt(st.nextToken());
		M = toInt(st.nextToken());
		H = toInt(st.nextToken());
		ladder = new boolean[H+1][N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			ladder[toInt(st.nextToken())][toInt(st.nextToken())] = true;
		}
		if(solve()) {
			System.out.println(0);
		} else {
			for(int i=1; i<=3; i++) {
				permutation(1, 0, i);
			}		
			System.out.println(-1);
			
		}
		
		 
	}
	
	
	private static void permutation(int start, int cnt, int finish) {
		if(cnt==finish) {
			if(solve()) {
				System.out.println(finish);
				System.exit(0);
			}
			return;
		}
		
		for(int i=start; i<=H; i++) {
			for(int j=1; j<N; j++) {
				if(ladder[i][j]) continue;
				
				ladder[i][j] = true;
				permutation(i, cnt+1, finish);
				ladder[i][j] = false;
			}
		}
	}
	
	private static boolean solve() {
		for(int i=1; i<=N; i++) {
			int x = i;
			for(int j=1; j<=H; j++) {
				if(ladder[j][x-1]) x--;
				else if(ladder[j][x]) x++;
			}
			if(x != i) return false;
		}
		return true;
	}
	
	private static int toInt(String str) {
		return Integer.parseInt(str);
	}
}
