import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int x;
		int y;
		String ans;
		
		Point(int x, int y, String ans) {
			this.x = x;
			this.y = y;
			this.ans = ans;
		}
	}
	static int N;
	static int[][] map;
	static int[][] tileNumArr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N*2];
		tileNumArr = new int[N][N*2];
		int cnt = 1;
		
		for(int i=0; i<N; i++) {
			if(i%2 == 0) {
				for(int j=0; j<2*N; j++) {
					
					st = new StringTokenizer(br.readLine());
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					map[i][j] = a;
					tileNumArr[i][j] = cnt;
					map[i][++j] = b;
					tileNumArr[i][j] = cnt++;
					
				}
			} else {
				for(int j=1; j<2*N-1; j++) {
					st = new StringTokenizer(br.readLine());
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					map[i][j] = a;
					tileNumArr[i][j] = cnt;
					map[i][++j] = b;
					tileNumArr[i][j] = cnt++;
				}
			}
		}
		bfs();
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<2*N; j++) {
//				System.out.printf("%d ", map[i][j]);
//			}
//			System.out.println();
//		}
	}
	
	public static void bfs() {
		Queue<Point> q = new LinkedList<>();
		boolean[] visited = new boolean[N*N+1];
		//int[][] dist = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
		int[][] dist = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		visited[1] = true;
		q.add(new Point(0, 0, "1"));
		int depth = 1;
		String ans = "1";
		int ansDepth = 0;
		int max = 0;
		
		while(!q.isEmpty()) {
			int len = q.size();
			
			for(int l=0; l<len; l++) {
				Point now = q.poll();
				int x = now.x;
				int y = now.y;
				String nowAns = now.ans;
				int nowTileNum = tileNumArr[x][y];
				
				if(nowTileNum == N*N - N/2) {
					System.out.println(depth);
					System.out.println(nowAns);
					return;
				}
				
				for(int i=0; i<4; i++) {
					int nx = x + dist[i][0];
					int ny = y + dist[i][1];		
					if(!isIn(nx, ny)) continue;
					int nextTileNum = tileNumArr[nx][ny];
					
					if(nextTileNum == nowTileNum) {
						for(int j=0; j<4; j++) {
							int nx2 = nx + dist[j][0];
							int ny2 = ny + dist[j][1];
							if(!isIn(nx2, ny2)) continue;
							int nextTileNum2 = tileNumArr[nx2][ny2];
							if(visited[nextTileNum2] || map[nx][ny] != map[nx2][ny2]) continue;
							q.add(new Point(nx2, ny2, nowAns + " " + nextTileNum2));
							visited[nextTileNum2] = true;
							
							if(max < tileNumArr[nx2][ny2]) {
								max = tileNumArr[nx2][ny2];
								ans = nowAns + " " + tileNumArr[nx2][ny2];
								ansDepth = depth;;
							}
						}
					} else {
						if(visited[nextTileNum] || map[nx][ny] != map[x][y]) continue;
						q.add(new Point(nx, ny, nowAns + " " + nextTileNum));
						visited[nextTileNum] = true;
						
						if(max < tileNumArr[nx][ny]) {
							max = tileNumArr[nx][ny];
							ans = nowAns + " " + tileNumArr[nx][ny];
							ansDepth = depth;
						}
					}
					
				}
			}
			depth++;
		}
		System.out.println(ansDepth+1);
		System.out.println(ans);
	}
	
	public static boolean isIn(int x, int y) {
		return 0<=x && x<N && 0<=y && y<2*N;
	}
}
