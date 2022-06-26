package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G4_17951_흩날리는시험지속에서내평점이느껴진거야 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int left = 0;
        int right = 0;
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            right+=arr[i];
        }

        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = 0, sum = 0;

            for (int i = 0; i < N; i++) {
                sum += arr[i];

                if (sum >= mid) {
                    cnt++;
                    sum = 0;
                }
                if(cnt >= K) break;
            }

            if (cnt >= K) left = mid + 1;
            else right = mid - 1;
        }

        System.out.println(left-1);
    }
}
