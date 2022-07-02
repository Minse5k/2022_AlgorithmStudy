package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_G5_2011_암호코드 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        if(str.length() == 1 || str.charAt(0) == '0') {
            if(str.charAt(0) == '0') System.out.println(0);
            else System.out.println(1);
            return;
        }
        long []dp = new long[str.length()];
        dp[0] = 1;
        int num = (str.charAt(0) - '0')*10 + (str.charAt(1) - '0');
        if (11 <= num && num <= 26) {
            if(num == 20) dp[1] = 1;
            else dp[1] = 2;
        } else {
            if(str.charAt(1) == '0' && num!= 10) {
                System.out.println(0);
                return;
            }
            dp[1] = 1;
        }

        for(int i=2; i<str.length(); i++) {
            int num1 = str.charAt(i) - '0';
            if (num1 > 0 && num1 < 10) {
                dp[i] += dp[i-1];
                dp[i] %= 1000000;
            }
            int num2 = (str.charAt(i-1) - '0')*10 + (str.charAt(i) - '0');
            if(num1 == 0 && num2 ==0) {
                System.out.println(0);
                return;
            }
            if (num2 > 9 && num2 < 27) {
                dp[i] += dp[i-2];
                dp[i] %= 1000000;
            }
        }

        System.out.println(dp[str.length()-1]%1000000);
    }
}
