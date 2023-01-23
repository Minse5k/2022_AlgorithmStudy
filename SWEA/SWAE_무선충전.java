import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class BC {
	int x;
	int y;
	int c;
	int p;

	BC(int x, int y, int c, int p) {
		this.x = x;
		this.y = y;
		this.c = c;
		this.p = p;
	}
}

public class Solution {
	static int[][] dist = { { 0, 0 }, { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static ArrayList<BC> bc;
	static int[] person1, person2;
	static int M, A;
	static int sum;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer ans = new StringBuffer();
		StringTokenizer st;

		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			sum = 0;
			bc = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			person1 = new int[M+1];
			person2 = new int[M+1];

			st = new StringTokenizer(br.readLine());
			
			for (int i = 1; i <= M; i++) {
				person1[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				person2[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				bc.add(new BC(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}

			int p1px = 1;
			int p1py = 1;
			int p2px = 10;
			int p2py = 10;
			
			for (int i = 0; i <= M; i++) {
				p1px += dist[person1[i]][1];
				p1py += dist[person1[i]][0];
				p2px += dist[person2[i]][1];
				p2py += dist[person2[i]][0];
				int max = 0;
				for (int j = 0; j < A; j++) {
					for (int k = 0; k < A; k++) {
						int tmp = 0;
						int sum1 = 0;
						int sum2 = 0;
						if (isInBC(p1px, p1py, j)) {
							sum1 = bc.get(j).p;
							//System.out.printf("hi1 %d %d %d", i, j, k);
						}
						if (isInBC(p2px, p2py, k)) {
							//System.out.printf("hi2 %d %d %d", i, j, k);
							sum2 = bc.get(k).p;
						}
						if (j == k) {
							tmp = Math.max(sum1, sum2);
						} else {
							tmp = sum1 + sum2;
						}
						if(tmp > max) max = tmp;
					}
				}
				sum += max;
			}
			ans.append("#" + t + " " + sum+"\n");
		}
		bw.write(ans.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean isInBC(int x, int y, int idx) {
		return (Math.abs(x - bc.get(idx).x) + Math.abs(y - bc.get(idx).y)) <= bc.get(idx).c;
	}
}
