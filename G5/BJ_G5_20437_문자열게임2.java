import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class BJ_G5_20437_문자열게임2 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());

        for(int t=1; t<=TC; t++) {
            char[] arr = br.readLine().toCharArray();
            int[]charArr = new int[26];
            int K = Integer.parseInt(br.readLine());
            if(K==1) {
                ans.append("1 1");
                if(t!=TC) {
                    ans.append("\n");
                }
                continue;
            }
            for(int i=0; i<arr.length; i++) {
                charArr[arr[i]-'a']++;
            }
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for(int i=0; i<arr.length-K+1; i++) {
                char word = arr[i];
                if(charArr[word-'a'] <K) continue;
                int cnt = 1;
                for(int j=i+1; j<arr.length; j++) {
                    if(word == arr[j]) {
                        cnt++;
                    }
                    if(cnt == K) {
                        if(min > j-i+1) {
                            min = j-i+1;
                        }
                        if(max < j-i+1) {
                            max = j-i+1;
                        }
                        charArr[word-'a']--;
                        break;
                    }
                }
            }
            if(min==Integer.MAX_VALUE) {
                ans.append("-1");
            } else {
                ans.append(min + " " + max);
            }
            if(t!=TC) {
                ans.append("\n");
            }
        }
        System.out.printf("%s", ans.toString());
    }
}
