package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_G5_7682_틱택토 {
    static char[][] map;
    static boolean[][] visited;
    static int xCnt = 0;
    static int oCnt = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();

        while(true) {
            String str = br.readLine();
            if(str.equals("end")) break;

            xCnt = 0;
            oCnt = 0;
            map = new char[3][3];
            visited = new boolean[3][3];
            for(int i=0; i<9; i++) {
                map[i/3][i%3] = str.charAt(i);
                if(str.charAt(i) == 'X') xCnt++;
                else if(str.charAt(i) == 'O') oCnt++;
            }

            //만약 놓여져있는 x가 o + 1보다 많거나, x가 o보다 적게 놓여있으면 안된다.
            if(xCnt > oCnt + 1 || xCnt < oCnt) {
                ans.append("invalid\n");
            } else {
                if(backTracking()) {
                    ans.append("valid\n");
                } else {
                    ans.append("invalid\n");
                }
            }
        }

        System.out.printf("%s", ans.toString());
    }

    public static boolean isBingo(char c) {
        //가로
        for(int i=0; i<3; i++) {
            if(map[i][0] == c && map[i][1] == c && map[i][2] == c) return true;
        }
        //세로
        for(int i=0; i<3; i++) {
            if(map[0][i] == c && map[1][i] == c && map[2][i] == c) return true;
        }
        //대각선
        if(map[0][0] == c && map[1][1] == c && map[2][2] == c) return true;
        if(map[0][2] == c && map[1][1] == c && map[2][0] == c) return true;

        return false;
    }

    public static boolean backTracking() {
        // 말판에 다 놓아져있는데
        if(xCnt + oCnt == 9) {
            // 'o'가 빙고면
            if(isBingo('O')) {
                return false;
            } else {
                return true;
            }
        } else {
            //다 놓여지지 않았으면(게임 반드시 끝나는 경우어야함)
            // 두개 놓여진 수가 같으면 O가 이겨야한다.
            if(xCnt == oCnt) {
                boolean isFInishX = isBingo('X');
                boolean isFInishO = isBingo('O');

                if(isFInishO && !isFInishX) return true;
                else return false;
            } else {
                // 두개 놓여진 수가 다르면 X가 이겨야한다.
                boolean isFInishX = isBingo('X');
                boolean isFInishO = isBingo('O');

                if(!isFInishO && isFInishX) return true;
                else return false;
            }
        }
    }
}
