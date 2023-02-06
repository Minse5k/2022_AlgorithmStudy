import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G4_2831_댄스파티 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> lowMale = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> lowFemale = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> highMale = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> highFemale = new PriorityQueue<>(Collections.reverseOrder());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int height = Integer.parseInt(st.nextToken());
			if(height < 0) {
				lowMale.add(Math.abs(height));
			} else {
				highMale.add(height);
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int height = Integer.parseInt(st.nextToken());
			if(height < 0) {
				lowFemale.add(Math.abs(height));
			} else {
				highFemale.add(height);
			}
		}
		
		System.out.println(twoPoint(lowMale, highFemale) + twoPoint(lowFemale, highMale));
	}
	
	public static int twoPoint(PriorityQueue<Integer> low, PriorityQueue<Integer> high) {
		int cnt = 0;
		while(!low.isEmpty() && !high.isEmpty()) {
			if(low.peek() > high.peek()) {
				cnt++;
				low.poll();
				high.poll();
			} else {
				high.poll();
			}
		}
		return cnt;
	}
}
