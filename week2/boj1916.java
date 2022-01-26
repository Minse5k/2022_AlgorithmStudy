package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1916 {

	static class Point implements Comparable<Point> {
		int end;
		int cost;

		Point(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Point o) {
			return this.cost - o.cost;
		}
	}

	static int n;
	static int m;
	static ArrayList<Point>[] map;
	static int[] distance;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		distance = new int[n + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		map = new ArrayList[n + 1];
		visited = new boolean[n + 1];
		for (int i = 0; i <= n; i++) {
			map[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			map[start].add(new Point(end, cost));
		}

		st = new StringTokenizer(br.readLine());

		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		dijkstra(start, end);

		System.out.println(distance[end]);
	}

	static void dijkstra(int start, int end) {
		Queue<Point> pQueue = new PriorityQueue<>();
		pQueue.add(new Point(start, 0));
		distance[start] = 0;

		while (!pQueue.isEmpty()) {
			Point cur = pQueue.poll();
			if(visited[cur.end]) continue;
			visited[cur.end]=true; 
			
			for (Point next : map[cur.end]) {
				if (distance[cur.end] + next.cost <= distance[next.end]) {
					distance[next.end] = distance[cur.end] + next.cost;
					pQueue.add(new Point(next.end, distance[next.end]));
				}
			}
		}
	}
}