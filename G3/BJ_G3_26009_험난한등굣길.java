import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int x;
		int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static int M;
	static int[][] map;
	static int[][] dist = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static boolean isFinish = false;
	static int time = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		int K = Integer.parseInt(br.readLine());
		
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			
			setAccident(x, y, d);
		}
		bfs();
		System.out.println(isFinish ? "YES\n"+time : "NO");
	}
	
	public static boolean isIn(int x, int y) {
		return 0<=x && x<N && 0<=y && y<M;
	}
	
	public static void setAccident(int x, int y, int d) {
		map[x][y] = 1;
		int[][] diagonal = {{-1, 1},{1, 1},{1, -1},{-1, -1}};
		int nx = x;
		int ny = y - d;
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<d; j++) {
				nx += diagonal[i][0];
				ny += diagonal[i][1];
				if(!isIn(nx, ny)) continue;
				map[nx][ny] = 1;
			}
		}
	}
	
	public static void bfs() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		q.add(new Point(0, 0));
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			int len = q.size();
			
			for(int l=0; l<len; l++) {
				Point now = q.poll();
				if(now.x == N-1 && now.y == M-1) {
					isFinish = true;
					return;
				}
				for(int i=0; i<4; i++) {
					int nx = now.x + dist[i][0];
					int ny = now.y + dist[i][1];
					
					if(!isIn(nx, ny) || visited[nx][ny] || map[nx][ny] == 1) continue;
					visited[nx][ny] = true;
					q.add(new Point(nx, ny));
				}
			}
			time++;
		}
	}
}
