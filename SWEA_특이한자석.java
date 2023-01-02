import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder ans = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        for(int t=1; t<=TC; t++) {
            int k = Integer.parseInt(br.readLine());
            int[][] gear = new int [4][8];

            for(int i=0; i<4; i++) {
                st = new StringTokenizer(br.readLine());

                for(int j=0; j<8; j++) {
                    gear[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for(int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int gearNum = Integer.parseInt(st.nextToken());
                int rotDir = Integer.parseInt(st.nextToken());

                solution(gearNum - 1, rotDir, gear);

            }
            ans.append("#" + t + " " +  score(gear) + "\n");
        }
        System.out.printf(ans.toString());
    }

    static void solution(int gearNum, int rotDir, int[][] gear) {
        checkLeft(gearNum - 1, -rotDir, gear);
        checkRight(gearNum + 1, -rotDir, gear);
        rotate(gearNum, rotDir, gear);
    }

    static void checkLeft(int gearNum, int rotDir, int[][] gear) {
        if(gearNum < 0) return;

        if(gear[gearNum][2] != gear[gearNum + 1][6]) {
            checkLeft(gearNum - 1, -rotDir, gear);
            rotate(gearNum, rotDir, gear);
        }
    }

    static void checkRight(int gearNum, int rotDir, int[][] gear) {
        if(gearNum > 3) return;

        if(gear[gearNum][6] != gear[gearNum - 1][2]) {
            checkRight(gearNum + 1, -rotDir, gear);
            rotate(gearNum, rotDir, gear);
        }
    }

    static void rotate(int gearNum, int rotDir, int[][] gear) {
        if(rotDir == 1) {
            int tmpValue = gear[gearNum][7];

            for(int i = 7; i > 0; i--) {
                gear[gearNum][i] = gear[gearNum][i-1];
            }

            gear[gearNum][0] = tmpValue;
        } else {
            int tmpValue = gear[gearNum][0];

            for(int i = 0; i < 7; i++) {
                gear[gearNum][i] = gear[gearNum][i+1];
            }

            gear[gearNum][7] = tmpValue;
        }
    }

    static int score(int[][] gear) {
        int score = 0;
        int num = 1;

        for(int i = 0; i < 4; i++) {
            score += gear[i][0] * num;
            num *= 2;
        }

        return score;
    }
}
