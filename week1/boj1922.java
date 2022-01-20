package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

// Kruskal Algorithm

class Network implements Comparable<Network> {
	int pc1;
	int pc2;
	int cost;
	
	public Network(int pc1, int pc2, int cost) {
		this.pc1 = pc1;
		this.pc2 = pc2;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Network o) {
		// TODO Auto-generated method stub
		if(this.cost < o.cost) {
			return -1;
		} else if(this.cost == o.cost) {
			return 0;
		} else {
			return 1;
		}
	}
}

public class boj1922 {
	static int comNum;
	static int lineNum;
	static int[] pcUnit;
	static ArrayList<Network> networkList = new ArrayList<Network>();
	
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x != y) {
			pcUnit[y] = x;
		}
	}
	
	public static int find(int x) {
		if(pcUnit[x] == x) {
			return x;
		}
		return pcUnit[x] = find(pcUnit[x]);
	}
	
	public static boolean isSamePcUnit(int x, int y) {
		x = find(x);
		y = find(y);
		if(x==y) return true;
		else return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		comNum = Integer.parseInt(br.readLine());
		lineNum = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < lineNum; i++) {
			st = new StringTokenizer(br.readLine());
			int pc1 = Integer.parseInt(st.nextToken());
			int pc2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			networkList.add(new Network(pc1, pc2, cost));
		}
		Collections.sort(networkList);
		pcUnit = new int[comNum + 1];
		
		for(int i = 1; i <= comNum; i++) {
			pcUnit[i] = i;
		}
		
		int answer = 0;
		
		for(int i = 0; i < networkList.size(); i++) {
			Network net = networkList.get(i);
			
			if(find(net.pc1) != find(net.pc2)) {
				answer += net.cost;
				union(net.pc1, net.pc2);
			}
		}
		System.out.println(answer);
	}
}