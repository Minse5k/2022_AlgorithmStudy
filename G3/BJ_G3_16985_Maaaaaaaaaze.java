package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G3_16985_Maaaaaaaaaze {
    static class Point{
        int x;
        int y;
        int z;
        Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    static int[][][] map = new int[5][5][5];
    static int[][] dist = {{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i=0; i<5; i++) {
            for(int r=0; r<5; r++) {
                st = new StringTokenizer(br.readLine());
                for(int c=0; c<5; c++) {
                    map[i][r][c] = Integer.parseInt(st.nextToken());
                }
            }
        }
        getOrder(0, new int[5], new boolean[5]);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static void bfs(int[][][] cube) {
        Queue<Point> q = new LinkedList<>();
        boolean[][][] visited = new boolean[5][5][5];
        q.add(new Point(0, 0, 0));
        visited[0][0][0] = true;
        int time = 0;

        while(!q.isEmpty()) {
            int len = q.size();

            for(int l=0; l<len; l++) {
                Point now = q.poll();
                if(now.x == 4 && now.y == 4 && now.z == 4) {
                    ans = Math.min(time, ans);
                    return;
                }
                for(int i=0; i<6; i++) {
                    int nx = now.x + dist[i][0];
                    int ny = now.y + dist[i][1];
                    int nz = now.z + dist[i][2];

                    if(!isIn(nx, ny, nz) || visited[nz][nx][ny] || cube[nz][nx][ny] == 0) continue;
                    q.add(new Point(nx, ny, nz));
                    visited[nz][nx][ny] = true;
                }
            }
            time++;
        }
    }

    public static int[][][] rotateMap(int[] order, int[] rotate) {
        int[][][]tmp = new int[5][5][5];

        for(int o=0; o<5; o++) {
            int now = order[o];
            int rCnt = rotate[now];

            for(int i=0; i<5; i++) {
                for(int j=0; j<5; j++) {
                    if(rCnt == 0)   tmp[o][i][j] = map[now][i][j];
                    else if(rCnt == 1)   tmp[o][j][4-i] = map[now][i][j];
                    else if(rCnt == 2)   tmp[o][4-i][4-j] = map[now][i][j];
                    else if(rCnt == 3)   tmp[o][4-j][i] = map[now][i][j];
                }
            }
        }

        return tmp;
    }

    public static void getRotate(int cnt, int[] rotate, int[] order) {
        if(cnt == 5) {
            int[][][] tmp = rotateMap(order, rotate);
            //입구와 출구가 막혀있다.
            if(tmp[0][0][0] == 0 || tmp[4][4][4] == 0) return;
            bfs(tmp);
            return;
        }

        for(int i=0; i<4; i++) {
            rotate[cnt] = i;
            getRotate(cnt+1, rotate, order);
        }
    }

    public static void getOrder(int cnt, int[] order, boolean[] isOrder) {
        if(cnt == 5) {
            getRotate(0, new int[5], order);
            return;
        }

        for(int i=0; i<5; i++) {
            if(isOrder[i]) continue;
            isOrder[i] = true;
            order[cnt] = i;
            getOrder(cnt+1, order, isOrder);
            isOrder[i] = false;
        }
    }

    public static boolean isIn(int x, int y, int z) {
        return 0<=x && x<5 && 0<=y && y<5 && 0<=z && z<5;
    }
}
