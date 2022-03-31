import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G4_2636_치즈 {
    static int N, M;
    static int[][] map;
    static int cheezeCnt = 0;

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) cheezeCnt++;
            }
        }
        int cheeze = 0;
        int time = 0;
        while(cheezeCnt>0) {
            cheeze = meltCheeze();
            time++;
        }
        System.out.printf("%d\n%d", time, cheeze);
    }

    private static int meltCheeze() {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.add(new Point(0, 0));
        visited[0][0] = true;
        int cnt = 0;
        int[][] dist = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!q.isEmpty()) {
            Point nP = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nP.x + dist[i][0];
                int ny = nP.y + dist[i][1];

                if (!isIn(nx, ny) || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                if (map[nP.x][nP.y] == 0 && map[nx][ny] == 1) {
                    map[nx][ny] = 0;
                    cnt++;
                    cheezeCnt--;
                } else {
                    q.add(new Point(nx, ny));
                }
            }
        }
        return cnt;
    }


    private static boolean isIn(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
