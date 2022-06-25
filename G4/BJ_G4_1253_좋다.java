package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ_G4_1253_좋다 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        HashSet<Integer> set = new HashSet<>();
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int cnt = 0;

        for(int i=0; i<N; i++) {
            int left = 0;
            int right = N-1;

            while(left < right) {
                if(left == i) {
                    left++;
                    continue;
                }
                if(right == i) {
                    right--;
                    continue;
                }
                if(arr[left] + arr[right] < arr[i]) left++;
                else if(arr[left] + arr[right] > arr[i]) right--;
                else {
                    cnt++;
                    break;
                }
            }
        }

        System.out.printf("%d", cnt);
    }
}
