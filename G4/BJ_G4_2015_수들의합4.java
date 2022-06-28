package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ_G4_2015_수들의합4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long sum = 0;
        long cnt = 0;
        HashMap<Long, Integer> hm = new HashMap<>();
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            sum += Integer.parseInt(st.nextToken());
            if(sum == K) cnt++;

            cnt += hm.getOrDefault(sum-K, 0);

            hm.put(sum, hm.getOrDefault(sum, 0) + 1);
        }
        System.out.println(cnt);
    }
}
