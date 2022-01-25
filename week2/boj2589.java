package week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj2589 {
	static int [][] dist = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static char map[][];
	static boolean visited[][];
	static int row;
	static int col;
	
	static class Point {
		int x;
		int y;
		int cost;
		
		public Point(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		map = new char[row][col];
		
		for(int i=0; i<row; i++) {
			map[i] = br.readLine().toCharArray();
		}
		int max = 0;
		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) {
				if(map[i][j] == 'W') continue;
				visited = new boolean[row][col];
				int t = bfs(new Point(i, j, 0));
				max = Math.max(max,  t);
			}
		}
		System.out.println(max);
		
		br.close();
	}
	
	public static int bfs(Point p) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(p);
		visited[p.x][p.y] = true;
		int ans = 0;
		
		while(!queue.isEmpty()) {
			Point nP = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nx = nP.x + dist[i][0];
				int ny = nP.y + dist[i][1];
				
				if(nx<0 || nx>=row || ny<0 || ny>=col || visited[nx][ny] || map[nx][ny] =='W') {
					continue;
				}
				visited[nx][ny] = true;
				ans = Math.max(ans, nP.cost+1);
				queue.add(new Point(nx, ny, nP.cost+1));
			}
		}
		return ans;
	}
}
