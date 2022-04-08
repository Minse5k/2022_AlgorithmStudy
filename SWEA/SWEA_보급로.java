import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWAE_보급로 {
    static class Point{
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static int[][] map;
    static int[][] dp;
    static int[][] dist = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int min;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer ans = new StringBuffer();
        int TC = Integer.parseInt(br.readLine());

        for(int t=1; t<=TC; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            dp = new int[N][N];

            for(int i=0; i<N; i++) {
                String str = br.readLine();
                for(int j=0; j<N; j++) {
                    map[i][j] = str.charAt(j)-'0';
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
            min = Integer.MAX_VALUE;
            BFS(0, 0);
            ans.append("#"+t+" "+min+"\n");
        }
        System.out.println(ans.toString());
    }

    private static void BFS(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        dp[x][y] = 0;
        while(!q.isEmpty()) {
            Point now = q.poll();
            if(now.x == N-1 && now.y == N-1) {
                min = Math.min(dp[N-1][N-1], min);
            }
            for(int i=0; i<4; i++) {
                int nx = now.x + dist[i][0];
                int ny = now.y + dist[i][1];

                if(!isIn(nx, ny) || dp[nx][ny] <= dp[now.x][now.y] + map[nx][ny]) continue;
                dp[nx][ny] = dp[now.x][now.y] + map[nx][ny];
                q.add(new Point(nx, ny));
            }
        }
    }

    private static boolean isIn(int x, int y) {
        return 0<=x && x<N && 0<=y && y<N;
    }
}
