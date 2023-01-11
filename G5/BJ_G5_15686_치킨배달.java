import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Pos{
	int x;
	int y;
	
	Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static int N, M;
	static int[][] map;
	static boolean[] visited;
	static ArrayList<Pos> chicken = new ArrayList<Pos>();
	static ArrayList<Pos> house = new ArrayList<Pos>();
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					house.add(new Pos(i, j));
				} else if(map[i][j] == 2) {
					chicken.add(new Pos(i, j));
				}
			}
		}
		
		visited = new boolean[chicken.size()];
		combination(0, 0);
		System.out.println(result);
	}
	
	private static void combination(int start, int cnt) {
		if(cnt == M) {
			int chickDist = solve();
			result = Math.min(result,  chickDist);
			return;
		}
		
		for(int i=start; i<chicken.size(); i++) {
			if(visited[i]) continue;
			visited[i] = true;
			combination(i+1, cnt+1);
			visited[i] = false;
		}
	}
	
	private static int solve() {
		int sum = 0;
		for(int i=0; i<house.size(); i++) {
			int min = Integer.MAX_VALUE;
			for(int j=0; j<chicken.size(); j++) {
				if(!visited[j]) continue;
				int dis = getDistance(i, j);
				min = Math.min(dis, min);
			}
			sum += min;
		}
		return sum;
	}
	private static int getDistance(int i, int j) {
		Pos hous = house.get(i);
		Pos chick = chicken.get(j);
		
		return Math.abs(hous.x - chick.x) + Math.abs(hous.y - chick.y);
	}
}
