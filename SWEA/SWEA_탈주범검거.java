import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_SWEA_탈주범검거 {
    static class Point{
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M, R, C, L;
    static int[][] map;
    // 상, 하, 좌, 우
    static int[][] dist = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int[][] pipe = {
            //0번
            {0, 0, 0, 0},
            //1번, 상하좌우
            {1, 1, 1, 1},
            //2번 상하
            {1, 1, 0, 0},
            //3번 좌우
            {0, 0, 1, 1},
            //4번 상우
            {1, 0, 0, 1},
            //5번 우하
            {0, 1, 0, 1},
            //6번 좌하
            {0, 1, 1, 0},
            //7번 좌상
            {1, 0, 1, 0}
    };
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer ans = new StringBuffer();
        int TC = Integer.parseInt(br.readLine());
        for(int t=1; t<=TC; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            map = new int[N][M];

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ans.append("#"+t+" "+bfs()+"\n");
        }
        System.out.printf(ans.toString());
    }
    private static int bfs() {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.add(new Point(R, C));
        visited[R][C] = true;
        int time = 0;
        int cnt = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            if(time == L) return cnt;
            for(int s=0; s<size; s++) {
                Point now = q.poll();
                cnt++;
                for(int i=0; i<4; i++) {
                    if(pipe[map[now.x][now.y]][i] == 0) continue;
                    int nx = now.x+dist[i][0];
                    int ny = now.y+dist[i][1];

                    if(!isIn(nx, ny) || visited[nx][ny] || map[nx][ny] == 0 || !isNext(i, nx, ny)) continue;

                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny));
                }
            }
            time++;
        }
        return cnt;
    }
    private static boolean isNext(int nowDist, int x, int y) {
        //상, 하, 좌, 우
        //상 > 하
        if(nowDist == 0 && pipe[map[x][y]][1] == 1)
            return true;
        //하 > 상
        if(nowDist == 1 && pipe[map[x][y]][0] == 1)
            return true;
        //좌 > 우
        if(nowDist == 2 && pipe[map[x][y]][3] == 1)
            return true;
        //우 > 좌
        if(nowDist == 3 && pipe[map[x][y]][2] == 1)
            return true;
        return false;
    }
    private static boolean isIn(int x, int y) {
        return 0<=x && x<N && 0<=y && y<M;
    }
}
