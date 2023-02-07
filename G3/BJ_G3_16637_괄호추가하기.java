import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static int ans = Integer.MIN_VALUE;
    static ArrayList<Character> ops;
    static ArrayList<Integer> nums;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		ops = new ArrayList<>();
		nums = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			char ch = str.charAt(i);
			if(i%2 == 0) {
				nums.add(ch-'0');
			} else {
				ops.add(ch);
			}
		}
		
		dfs(nums.get(0), 0);
		System.out.println(ans);
	}
	
	public static void dfs(int sum, int idx) {
		if(idx >= ops.size()) {
			ans = Math.max(sum,  ans);
			return;
		}
		
		int re = calc(ops.get(idx), sum, nums.get(idx+1));
		dfs(re, idx+1);
		
		if(idx + 1 < ops.size()) {
			int res = calc(ops.get(idx + 1), nums.get(idx + 1), nums.get(idx + 2));
			dfs(calc(ops.get(idx), sum, res), idx + 2);
		}
	}
	
	public static int calc(char op, int n1, int n2) {
		switch(op) {
		case '+':
			return n1+n2;
		case '-':
			return n1-n2;
		case '*':
			return n1*n2;
		}
		return 0;
	}
}
