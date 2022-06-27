package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G4_25307_시루의백화점구경 {
    static class Point{
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M, K;
    static int[][] map;
    static boolean[][] visited;
    static Point start = null, finish = null;
    static int[][] dist = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 4) {
                    start = new Point(i, j);
                } else if(map[i][j] == 3) {
                    // dfs를 통해서 K거리까지 시루가 이동하지 않게 visited에 체크해줌
                    visited[i][j] = true;
                    dfs(i, j, 0);
                } else if(map[i][j] == 2) {
                    finish = new Point(i, j);
                }
            }
        }
        //early return
        if(finish == null) {
            System.out.println(-1);
            return;
        }

        bfs();
    }

    public static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        visited[start.x][start.y] = true;
        int depth = 0;

        while(!q.isEmpty()) {
            int len = q.size();

            for(int l=0; l<len; l++) {
                Point now = q.poll();

                for(int i=0; i<4; i++) {
                    int nx = now.x + dist[i][0];
                    int ny = now.y + dist[i][1];

                    if(!isIn(nx, ny) || visited[nx][ny]) continue;
                    if(finish.x == nx && finish.y == ny) {
                        System.out.println(depth+1);
                        return;
                    }
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny));
                }
            }
            depth++;
        }

        System.out.println(-1);
    }

    public static void dfs(int x, int y, int depth) {
        if(depth == K) {
            return;
        }

        for(int i=0; i<4; i++) {
            int nx = x + dist[i][0];
            int ny = y + dist[i][1];

            if(!isIn(nx, ny) || visited[nx][ny]) continue;
            visited[nx][ny] = true;
            dfs(nx, ny, depth+1);
        }
    }

    public static boolean isIn(int x, int y) {
        return 0<=x && x<N && 0<=y && y<M;
    }
}
