import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder arr1 = new StringBuilder(br.readLine());
        StringBuilder arr2 = new StringBuilder(arr1);
        StringBuilder ans = new StringBuilder(br.readLine());

        int pushCnt1 = 1;
        int pushCnt2 = 0;
        arr1.setCharAt(0,  arr1.charAt(0) == '1' ? '0' : '1');
        arr1.setCharAt(1,  arr1.charAt(1) == '1' ? '0' : '1');

        for(int i=1; i<N; i++) {
            if(arr1.charAt(i-1) != ans.charAt(i-1)) {
                if(i==N-1) {
                    arr1.setCharAt(i-1,  arr1.charAt(i-1) == '1' ? '0' : '1');
                    arr1.setCharAt(i,  arr1.charAt(i) == '1' ? '0' : '1');
                } else {
                    arr1.setCharAt(i-1,  arr1.charAt(i-1) == '1' ? '0' : '1');
                    arr1.setCharAt(i,  arr1.charAt(i) == '1' ? '0' : '1');
                    arr1.setCharAt(i+1,  arr1.charAt(i+1) == '1' ? '0' : '1');
                }
                pushCnt1++;
            }
            if(arr2.charAt(i-1) != ans.charAt(i-1)) {
                if(i==N-1) {
                    arr2.setCharAt(i-1,  arr2.charAt(i-1) == '1' ? '0' : '1');
                    arr2.setCharAt(i,  arr2.charAt(i) == '1' ? '0' : '1');
                } else {
                    arr2.setCharAt(i-1,  arr2.charAt(i-1) == '1' ? '0' : '1');
                    arr2.setCharAt(i,  arr2.charAt(i) == '1' ? '0' : '1');
                    arr2.setCharAt(i+1,  arr2.charAt(i+1) == '1' ? '0' : '1');
                }
                pushCnt2++;
            }
        }
        if(!arr1.toString().equals(ans.toString())) pushCnt1 = Integer.MAX_VALUE;
        if(!arr2.toString().equals(ans.toString())) pushCnt2 = Integer.MAX_VALUE;
        int answer = Math.min(pushCnt1, pushCnt2);
        if(answer == Integer.MAX_VALUE) {
            System.out.printf("-1");
        } else {
            System.out.printf("%d", Math.min(pushCnt1, pushCnt2));
        }
    }
}
