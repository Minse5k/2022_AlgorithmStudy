package week2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj2812 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		String str = br.readLine();
		int n = N-K;
		String[] arr = str.split("");
		
		Stack<Integer> stack = new Stack<>();
		
		for(int i=0; i<arr.length; i++) {
			int nowNum = Integer.parseInt(arr[i]);
			while(K>0 && !stack.isEmpty() && stack.peek() < nowNum) {
				stack.pop();
				K--;
			}
			stack.add(nowNum);
		}
		if(stack.size() <= n) {
			n = stack.size();
		}
		for (int i = 0; i < n; i++) {
			System.out.print(stack.get(i));
		}
	}
}