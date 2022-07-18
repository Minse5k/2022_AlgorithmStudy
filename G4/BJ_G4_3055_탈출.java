package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_G4_3055_탈출 {
    static class Point{
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static Point start, finish;
    static int[][] dist = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];

        for(int i=0; i<R; i++) {
            String str = br.readLine();
            for(int j=0; j<C; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'D') {
                    finish = new Point(i, j);
                } else if(map[i][j] == 'S') {
                    map[i][j] = '.';
                    start = new Point(i, j);
                }
            }
        }

        bfs();
        System.out.println("KAKTUS");
    }
    public static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        visited[start.x][start.y] = true;
        int time = 0;

        while(!q.isEmpty()) {
            fillWater();
            int len = q.size();

            for(int l=0; l<len; l++) {
                Point now = q.poll();
                if(now.x == finish.x && now.y == finish.y) {
                    System.out.println(time);
                    System.exit(0);
                }
                for(int i=0; i<4; i++) {
                    int nx = now.x + dist[i][0];
                    int ny = now.y + dist[i][1];

                    if(!isIn(nx, ny) || map[nx][ny] == '*' || map[nx][ny] == 'X' || visited[nx][ny]) continue;
                    q.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                }
            }
            time++;
        }
    }

    public static void fillWater() {
        ArrayList<Point> list = new ArrayList<>();

        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(map[i][j] == '*') {
                    for(int k=0; k<4; k++) {
                        int nx = i + dist[k][0];
                        int ny = j + dist[k][1];

                        if(!isIn(nx , ny) || map[nx][ny] != '.') continue;
                        list.add(new Point(nx, ny));
                    }
                }
            }
        }

        for(Point now : list) {
            map[now.x][now.y] = '*';
        }
    }

    public static boolean isIn(int x, int y) {
        return 0<=x && x<R && 0<=y && y<C;
    }
}
