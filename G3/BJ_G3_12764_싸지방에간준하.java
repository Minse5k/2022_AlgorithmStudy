import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G3_12764_싸지방에간준하 {
	static class time implements Comparable<time> {
		int start;
		int end;
		
		time(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(time o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.start, o.start);
		}
	}
	
	static class computer implements Comparable<computer> {
		int end;
		int index;
		
		computer(int end, int index) {
			this.end = end;
			this.index = index;
		}
		
		@Override
		public int compareTo(computer o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.end, o.end);
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<time> times = new PriorityQueue<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			times.add(new time(start, end));			
		}
		
		PriorityQueue<computer> endTime = new PriorityQueue<>();
		PriorityQueue<Integer> restCom = new PriorityQueue<>();
		int[] comIdx = new int[100001];
		int comCnt = 0;
		
		for(int i=0; i<N; i++) {
			while(!endTime.isEmpty() && times.peek().start > endTime.peek().end) {
				restCom.add(endTime.poll().index);
			}
			if(restCom.isEmpty()) {
				endTime.add(new computer(times.poll().end, comCnt));
				comIdx[comCnt]++;
				comCnt++;
			} else {
				endTime.add(new computer(times.poll().end, restCom.peek()));
				comIdx[restCom.poll()]++;
			}
		}
		
		sb.append(comCnt+"\n");
		for(int i=0; i<comCnt; i++) {
			sb.append(comIdx[i]+ " ");
		}
		System.out.println(sb.toString());
	}
}
