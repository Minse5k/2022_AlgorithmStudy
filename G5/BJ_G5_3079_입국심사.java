import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_G5_3079_입국심사 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Integer.parseInt(st.nextToken());
        long[] port = new long[N];
        long max = 0;
        for(int i=0; i<N; i++) {
            port[i] = Integer.parseInt(br.readLine());
            if(max < port[i]) max = port[i];
        }

        long left = 0;
        long right = max * M;
        long min = Long.MAX_VALUE;
        while(left<=right) {
            long mid = (left+right)/2;
            long cntSum = 0;

            for(int i=0; i<N; i++) {
                cntSum += mid/port[i];
                if(cntSum >= M) {
                    break;
                }
            }
            if(cntSum >= M) {
                right = mid - 1;
                min = Math.min(min, mid);
            } else {
                left = mid+1;
            }
        }

        System.out.println(min);
    }
}
