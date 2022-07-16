package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_G4_2262_토너먼트만들기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();

        for(int i=0; i<N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        int sum = 0;

        while(N>1) {
            int max = 0;
            int maxIdx = 0;

            for(int i=0; i<N; i++) {
                if(max < list.get(i)) {
                    max = list.get(i);
                    maxIdx = i;
                }
            }

            if(maxIdx == 0) {
                sum += Math.abs(list.get(0) - list.get(1));
            } else if(maxIdx == N-1) {
                sum += Math.abs(list.get(N-1) - list.get(N-2));
            } else {
                int left = Math.abs(list.get(maxIdx) - list.get(maxIdx-1));
                int right = Math.abs(list.get(maxIdx) - list.get(maxIdx+1));
                sum += Math.min(left, right);
            }

            list.remove(maxIdx);
            N--;
        }
        System.out.println(sum);
    }
}
