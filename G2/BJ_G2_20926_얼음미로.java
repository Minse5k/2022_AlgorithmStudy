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
	
	static int N, M;
	static char[][] map;
	static int[][] cost;
	static int[][] dist = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static Point start, end;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		cost = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<str.length(); j++) {
				cost[i][j] = Integer.MAX_VALUE;
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'T') {
					start = new Point(i, j);
					map[i][j] = '0';
					cost[i][j] = 0;
				} else if(map[i][j] == 'E') {
					end = new Point(i, j);
				}
			}
		}
		
		bfs();
		System.out.println(cost[end.x][end.y] == Integer.MAX_VALUE ? -1 : cost[end.x][end.y]);
	}
	
	public static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(start);
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int s=0; s<size; s++) {
				Point now = q.poll();
				if(now.x == end.x && now.y == end.y) {
					continue;
				}
				int nowCost = cost[now.x][now.y];
				
				for(int i=0; i<4; i++) {
					int nx = now.x + dist[i][0];
					int ny = now.y + dist[i][1];
					int nextCost = 0;
					
					while(isIn(nx, ny)) {
						if(map[nx][ny] == 'H') {
							break;
						}
						if(map[nx][ny] == 'E') {
							break;
						}
						if(!isNum(map[nx][ny])) {
							nx -= dist[i][0];
							ny -= dist[i][1];
							break;
						}
						nextCost += (int) map[nx][ny] - '0';
						nx += dist[i][0];
						ny += dist[i][1];
						
					}
					if(!isIn(nx, ny) || map[nx][ny] == 'H' || cost[nx][ny] <= nowCost + nextCost) continue;
					cost[nx][ny] = nowCost + nextCost;
					q.add(new Point(nx, ny));
				}
			}
		}
	}
	
	public static boolean isNum(int x) {
		return '0'<=x && x<='9';
	}
	
	public static boolean isIn(int x, int y) {
		return 0<=x && x<N && 0<=y && y<M;
	}
}
