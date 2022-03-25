import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G4_17135_캐슬디펜스 {

    /*
    1. 궁수는 총 3명, 성에 배치한다.
    2. 궁수의 사거리는 D = |r2-r1| + |c2-c1|;
    3. 성은 N+1행에 존재한다.
    3. 1은 적의 위치이다.
    4. 선 공격, 후 이동
     */
    static int N, M, D;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        placeArcher(0, 1, new int[M], map);
        System.out.println(max);
    }

    private static void placeArcher(int start, int cnt, int[] arr, int[][] map) {
        if (cnt == 4) {
            //System.out.println(Arrays.toString(arr));
            int[][] tmpMap = new int[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    tmpMap[i][j] = map[i][j];
                }
            }

            int killCnt = 0;
            for (int i = 0; i < N; i++) {
                killCnt += killEnemy(arr, tmpMap);
                moveEnemy(tmpMap);
            }
            max = Math.max(killCnt, max);
            return;
        }
        for (int i = start; i < M; i++) {
            arr[i] = cnt;
            placeArcher(i + 1, cnt + 1, arr, map);
            arr[i] = 0;
        }
    }

    private static int killEnemy(int[] Pos, int[][] map) {
        int[][] killPos = new int[3][3];
        int j = 0;
        int kCnt = 0;
        for (int i = 0; i < M; i++) {
            if (Pos[i] == 0) continue;
            killPos[j] = getCloseEnemy(N, i, map);
            j++;
        }

        for (int i = 0; i < 3; i++) {
            if (killPos[i][2] <= D) {
                int dx = killPos[i][0];
                int dy = killPos[i][1];
                if (dx == -1 || map[dx][dy] == 0) continue;
                map[dx][dy] = 0;
                kCnt++;
            }
        }
        return kCnt;
    }

    private static int[] getCloseEnemy(int x, int y, int[][] map) {
        int min = Integer.MAX_VALUE;
        int ex = -1;
        int ey = -1;
        for (int j = 0; j < M; j++) {
            for (int i = N - 1; i >= 0; i--) {
                if (map[i][j] == 0) continue;
                int dist = Math.abs(x - i) + Math.abs(y - j);
                if (dist > D) continue;
                if (min > dist) {
                    min = dist;
                    ex = i;
                    ey = j;
                }
            }
        }
        return new int[]{ex, ey, min};
    }

    private static void moveEnemy(int[][] map) {
        for (int i = N - 1; i > 0; i--) {
            for (int j = 0; j < M; j++) {
                map[i][j] = map[i - 1][j];
            }
        }
        for (int j = 0; j < M; j++) {
            map[0][j] = 0;
        }
    }
}
