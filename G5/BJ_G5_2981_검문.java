package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_G5_2981_검문 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int gcdGap = arr[1]-arr[0];

        for(int i=2; i<N; i++) {
            gcdGap = gcd(gcdGap, arr[i] - arr[i-1]);
        }

        for(int i=2; i<=gcdGap; i++) {
            if(gcdGap%i == 0 ) {
                System.out.printf("%d ",i);
            }
        }
    }
    public static int gcd(int p, int q)
    {
        if (q == 0) return p;
        return gcd(q, p%q);
    }
}
