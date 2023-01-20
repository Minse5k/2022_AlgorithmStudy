import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer ans = new StringBuffer();
        int TC = Integer.parseInt(br.readLine());
        for(int t=1; t<=TC; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N+1];
            int[]dp = new int[N];
            int max = 0;
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                dp[i]=1;
                for(int j=0; j<i; j++) {
                    if(arr[i] > arr[j] && dp[i] < dp[j]+1) {
                        dp[i] = dp[j]+1;
                    }
                }
                max = Math.max(dp[i], max);
            }
            ans.append("#"+t+" "+max+"\n");
        }
        System.out.println(ans.toString());
    }
}
