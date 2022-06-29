package 프로그래머스;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PM_양궁대회 {
    static ArrayList<int[]> list = new ArrayList<>();
    static int[] Ryan;
    static int maxDiff = -1;

    public static void main(String[] args) throws Exception {
        System.out.println(Arrays.toString(solution(10, new int[]{0,0,0,0,0,0,0,0,3,4,3})));
    }

    public static void permutation(int[] apeach, int cnt, int N, int start) {
        if(cnt == N) {
            int aSum = 0;
            int rSum = 0;

            for(int i=0; i<=10; i++) {
                if(apeach[i] == 0 && Ryan[i] == 0) continue;
                int score = 10-i;

                if(apeach[i] >= Ryan[i]) {
                    aSum += score;
                } else {
                    rSum += score;
                }
            }

            if(rSum > aSum) {
                int diff = rSum - aSum;
                if(maxDiff < diff) {
                    maxDiff = diff;
                    list.clear();
                    list.add(Ryan.clone());
                } else if(maxDiff == diff){
                    list.add(Ryan.clone());
                }
            }
            return;
        }

        for(int i=start; i<=10; i++) {
            if(Ryan[i] > apeach[i]) continue;
            Ryan[i]++;
            permutation(apeach, cnt+1, N, i);
            Ryan[i]--;
        }
    }

    public static int[] solution(int n, int[] info) {
        Ryan = new int[11];
        permutation(info, 0, n, 0);

        if(list.size() == 0) return new int[] {-1};

        Collections.sort(list, (o1, o2) -> {
            for(int i=10;i>=0;i--){
                if(o1[i] != o2[i]) return o2[i] - o1[i];
            }
            return 0;
        });
        return list.get(0);
    }
}
