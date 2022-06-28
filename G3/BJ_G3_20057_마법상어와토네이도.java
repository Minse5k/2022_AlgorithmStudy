package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G3_20057_마법사상어와토네이도 {
    static int N;
    static int[][] map;
    static int x, y;
    static int ans = 0;
    //왼쪽, 아래, 오른쪽, 위
    static int[][] dist = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    static int[][][] moveSend = {
            // 10 10 7 7 5 2 2 1 1 a 순서.
            //d = 0
            {
                    {-1, -1}, {1, -1}, {-1, 0}, {1, 0}, {0, -2}, {-2, 0}, {2, 0}, {1, 1}, {-1, 1}, {0, -1}
            },
            //d = 1
            {
                    {1, -1}, {1, 1}, {0, -1}, {0, 1}, {2, 0}, {0, 2}, {0, -2}, {-1, 1}, {-1, -1}, {1, 0}
            },
            //d = 2
            {
                    {-1, 1}, {1, 1}, {1, 0}, {-1, 0}, {0, 2}, {2, 0}, {-2, 0}, {1, -1}, {-1, -1}, {0, 1}
            },
            //d = 3
            {
                    {-1, -1}, {-1, 1}, {0, 1}, {0, -1}, {-2, 0}, {0, 2}, {0, -2}, {1, -1}, {1, 1}, {-1, 0}
            }
    };

    static int[] rate = {10, 10, 7, 7, 5, 2, 2, 1, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        x = (N + 1) / 2 - 1;
        y = (N + 1) / 2 - 1;
        for (int i = 1; i <= N / 2; i++) {
            tornado(0, 2 * i - 1);
            tornado(1, 2 * i - 1);
            tornado(2, 2 * i);
            tornado(3, 2 * i);
        }

        tornado(0, N-1);

        System.out.println(ans);
    }

    public static void tornado(int d, int cnt) {
        for (int c = 0; c < cnt; c++) {
            x += dist[d][0];
            y += dist[d][1];
            int send = map[x][y];
            map[x][y] = 0;
            int a = send;

            for (int i = 0; i < 10; i++) {
                int nx = x + moveSend[d][i][0];
                int ny = y + moveSend[d][i][1];

                a -= (send * rate[i]) / 100;

                if (!isIn(nx, ny)) {
                    if(i==9) {
                        ans += a;
                    } else {
                        ans += (send * rate[i]) / 100;
                    }
                    continue;
                }
                if(i == 9) {
                    map[nx][ny] += a;
                } else {
                    map[nx][ny] += (send * rate[i]) / 100;
                }
            }
        }
    }

    public static boolean isIn(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
