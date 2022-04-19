import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G4_13422_도둑 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder ans = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        for(int t=1; t<=TC; t++) {
            int N, M, K;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());

            int sum = 0;
            int cnt = 0;
            for(int i=0; i<N+(M-1); i++) {
                if(i<N) {
                    arr[i] = Integer.parseInt(st.nextToken());
                    sum+=arr[i];
                } else {
                    sum+=arr[i-N];
                }
                if(i >= M-1) {
                    if(sum < K) {
                        cnt++;
                    }
                    sum-=arr[i-(M-1)];
                }
                if(N==M && i==N-1) {
                    break;
                }
            }
            ans.append(cnt + "\n");
        }
        System.out.printf(ans.toString());
    }
}
