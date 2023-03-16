import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node>{
		int node;
		int dist;
		
		Node(int node, int dist) {	
			this.node = node;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.dist - o.dist;
		}
	}
	static ArrayList<Node>[] graph;	
	static StringBuilder sb = new StringBuilder();
	static int[] dist;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=TC; t++) {
			st = new StringTokenizer(br.readLine());
			int n, d, c;
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			graph = new ArrayList[n+1];
			dist = new int[n+1];
			
			for(int i=0; i<n+1; i++) {
				graph[i] = new ArrayList<Node>();
				dist[i] = Integer.MAX_VALUE;
			}
			
			for(int i=0; i<d; i++) {
				st = new StringTokenizer(br.readLine());
				int a, b, s;
				
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				s = Integer.parseInt(st.nextToken());
				
				graph[b].add(new Node(a, s));
			}
			
			Dijkstra(c, n);
		}
		System.out.println(sb.toString());
	}
	
	public static void Dijkstra(int start, int n) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			for(int i=0; i<graph[now.node].size(); i++) {
				Node next = graph[now.node].get(i);
				
				if(dist[now.node] + next.dist >= dist[next.node]) continue;
				dist[next.node] = dist[now.node]+ next.dist;
				pq.add(new Node(next.node, dist[now.node]+ next.dist));
			}
		}
		
		int max = 0;
		int cnt = 0;
		for(int i=0; i<=n; i++) {
			if(dist[i] == Integer.MAX_VALUE) continue;
			max = Math.max(dist[i], max);
			cnt++;
		}
		sb.append(cnt + " " + max + "\n");
	}
}
