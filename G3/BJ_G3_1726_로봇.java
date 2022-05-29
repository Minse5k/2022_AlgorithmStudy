package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G3_1726_로봇 {
    static class Point{
        int x;
        int y;
        int dist;
        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y && dist == point.dist;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, dist);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", dist=" + dist +
                    '}';
        }
    }

    static int N, M;
    static int[][] map;
    static Point start, end;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        System.out.printf("%d", bfs());
    }

    public static int bfs() {
        // 1 : 동, 2 : 서, 3 : 남, 4 : 북
        int[][] dist = {{}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<Point> q = new LinkedList<>();
        boolean[][][] visited = new boolean[5][N+1][M+1];
        q.add(start);
        visited[start.dist][start.x][start.y] = true;
        int time = 0;
        while(!q.isEmpty()) {
            int len = q.size();
            time++;
            for(int l=0; l<len; l++) {
                Point now = q.poll();
                if(end.equals(now)) {
                    return time-1;
                }
                //앞으로가기
                for(int i=1; i<=3; i++) {
                    int nx = now.x + dist[now.dist][0] * i;
                    int ny = now.y + dist[now.dist][1] * i;

                    if(!isIn(nx, ny) || map[nx][ny] == 1) break;
                    if(visited[now.dist][nx][ny]) continue;


                    visited[now.dist][nx][ny] = true;
                    q.add(new Point(nx, ny, now.dist));
                }

                //방향 돌리기
                int nx = now.x;
                int ny = now.y;
                int nextLeft = distChange(now.dist, 'L');
                int nextRight = distChange(now.dist, 'R');

                if(!visited[nextLeft][nx][ny]) {
                    visited[nextLeft][nx][ny] = true;
                    q.add(new Point(nx, ny, nextLeft));
                }

                if(!visited[nextRight][nx][ny]) {
                    visited[nextRight][nx][ny] = true;
                    q.add(new Point(nx, ny, nextRight));
                }
            }

        }

        return time;
    }

    public static int distChange(int num, char d) {
        switch(d) {
            case 'L':
                if(num == 1) {
                    return 4;
                } else if(num == 2) {
                    return 3;
                } else if(num == 3) {
                    return 1;
                } else {
                    return 2;
                }
            case 'R':
                if(num == 1) {
                    return 3;
                } else if(num == 2) {
                    return 4;
                } else if(num == 3) {
                    return 2;
                } else {
                    return 1;
                }
        }

        return 0;
    }

    public static boolean isIn(int x, int y) {
        return 1<=x && x<=N && 1<=y && y<=M;
    }
}
