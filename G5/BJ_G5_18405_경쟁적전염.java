import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_G5_18405_경쟁적전염 {
    static class Point implements Comparable<Point> {
        int x;
        int y;
        int num;

        Point(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }

        @Override
        public int compareTo(Point o) {
            return this.num - o.num;
        }
    }
    static int N, K;
    static int S, X, Y;
    static int[][] map;
    static PriorityQueue<Point> pq = new PriorityQueue<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0) pq.add(new Point(i, j, map[i][j]));
            }
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        bfs();
        System.out.printf("%d", map[X][Y]);
    }

    public static void bfs() {
        int[][] dist = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Queue<Point> q = new LinkedList<>();
        int time = 0;
        while(time<S) {
            time++;
            while(!pq.isEmpty()) {
                Point now = pq.poll();

                for(int i=0; i<4; i++) {
                    int nx = now.x + dist[i][0];
                    int ny = now.y + dist[i][1];
                    if(!isIn(nx, ny) || map[nx][ny] != 0) continue;
                    map[nx][ny] = now.num;
                    q.add(new Point(nx, ny, now.num));
                }
            }
            while(!q.isEmpty()) {
                pq.add(q.poll());
            }
        }
    }

    public static boolean isIn(int x, int y) {
        return 0<x && x<=N && 0<y && y<=N;
    }
}
