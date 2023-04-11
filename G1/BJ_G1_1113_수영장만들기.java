import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_G1_1113_수영장만들기 {
    static class Point{
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M;
    static int[][] map;
    static int[][] water;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        water = new int[N][M];

        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = str.charAt(j)-'0';
                if(max < map[i][j]) max = map[i][j]; 
                if(min > map[i][j]) min = map[i][j];
            }
        }

        int waterCnt = 0;
        for(int h = min+1; h <= max; h++) {
            for(int i=1; i<N-1; i++) {
                for(int j=1; j<M-1; j++) {
                    if(map[i][j] < h) {
                        waterCnt += bfs(i, j, h);
                    }
                }
            }
        }

        System.out.println(waterCnt);
    }

    public static int bfs(int r, int c, int height) {
        Queue<Point> q = new LinkedList<>();
        int cnt = 1;
        int[][] dist = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        map[r][c]++;
        boolean isFill = true;
        q.add(new Point(r, c));

        while(!q.isEmpty()) {
            Point now = q.poll();

            for(int i=0; i<4; i++) {
                int nx = now.x + dist[i][0];
                int ny = now.y + dist[i][1];

                if(!isIn(nx, ny)) {
                    isFill = false;
                    continue;
                }
                if(map[nx][ny] < height) {
                    map[nx][ny]++;
                    cnt++;
                    q.add(new Point(nx, ny));
                }
            }
        }

        if(isFill) return cnt;
        else return 0;
    }

    public static boolean isIn(int x, int y) {
        return 0<=x && x<N && 0<=y && y<M;
    }
}
