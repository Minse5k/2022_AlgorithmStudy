import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G4_1600_말이되고픈원숭이 {
    static int K;
    static int N, M;
    static int min = Integer.MAX_VALUE;
    static int[][] map;
    static int dist[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int dist2[][] = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};

    static class Point {
        int x;
        int y;
        int k;

        Point(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs();
    }

    private static void bfs() {
        Queue<Point> q = new LinkedList<>();
        boolean[][][] visited = new boolean[K+1][N][M];
        q.add(new Point(0, 0, K));
        visited[K][0][0] = true;
        int depth = 0;

        while (!q.isEmpty()) {
            int len = q.size();

            for (int l = 0; l < len; l++) {
                Point nP = q.poll();
                if (nP.x == N - 1 && nP.y == M - 1) {
                    System.out.println(depth);
                    return;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = nP.x + dist[i][0];
                    int ny = nP.y + dist[i][1];

                    if (!isIn(nx, ny) || map[nx][ny] == 1 || visited[nP.k][nx][ny]) continue;
                    visited[nP.k][nx][ny] = true;
                    q.add(new Point(nx, ny, nP.k));
                }
                if (nP.k > 0) {
                    for (int i = 0; i < 8; i++) {
                        int nx = nP.x + dist2[i][0];
                        int ny = nP.y + dist2[i][1];

                        if (!isIn(nx, ny) || map[nx][ny] == 1 || visited[nP.k-1][nx][ny]) continue;
                        visited[nP.k-1][nx][ny] = true;
                        q.add(new Point(nx, ny, nP.k-1));
                    }
                }
            }

            depth++;
        }
        System.out.println(-1);
    }

    private static boolean isIn(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
