import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G5_27210_신을모시는사당 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken()) == 2 ? -1: 1;
		}
		
		int left = 0;
		int right = 0;
		int max = 0;
		for(int i=0; i<N; i++) {
			left += arr[i];
			right += arr[i];
			
			max = Math.max(left,  max);
			max = Math.max(Math.abs(right),  max);
			
			if(left < 0) left = 0;
			if(right > 0) right = 0;
		}
		
		System.out.println(max);
	}
}
