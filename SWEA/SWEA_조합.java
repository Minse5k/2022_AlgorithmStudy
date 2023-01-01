import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static final int mod = 1234567891;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder ans = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        long[] factorial = new long[1000001];
        factorial[0] = 1;
        for(int i=1; i<=1000000; i++) {
            factorial[i] = (factorial[i-1]*i)%mod;
        }
        for(int t=1; t<=TC; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            long n = factorial[N];
            long r = (factorial[R] * factorial[N-R]) % mod;
            long mt = pow(r, mod-2);
            ans.append("#" + t + " " + ((n*mt)%mod) + "\n");
        }

        System.out.printf(ans.toString());
    }

    private static long pow(long num, int p) {
        if(p==0) return 1;
        long half = pow(num, p/2);
        long ret = (half*half) % mod;
        if(p%2==0) {
            return ret;
        } else {
            return (ret*num)%mod;
        }
    }
}
