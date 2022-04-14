import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G5_14503_로봇청소기 {
    static class Robot{
        int x;
        int y;
        int dist;
        Robot(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    static int N, M;
    static Robot robot;
    static int[][] map;
    // 북, 동, 남, 서
    static int[][] dist = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int dist = Integer.parseInt(st.nextToken());
        robot = new Robot(x, y, dist);

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        goCleaner();
    }

    private static void goCleaner() {
        int cleanCnt = 1;
        map[robot.x][robot.y] = 2;

        while(true) {
            int[] next = isGo();
            if(next[0] == -1) {
                int[] back = isBack();
                if(back[0] == -1) break;
                else {
                    robot = new Robot(back[0], back[1], back[2]);
                    continue;
                }
            }
            robot = new Robot(next[0], next[1], next[2]);
            map[robot.x][robot.y] = 2;
            cleanCnt++;
        }
        System.out.println(cleanCnt);
    }
    private static int[] isGo() {
        for(int i=1; i<=4; i++) {
            int nowDist = (robot.dist - i);
            if(nowDist < 0) nowDist += 4;
            int nx = robot.x + dist[nowDist][0];
            int ny = robot.y + dist[nowDist][1];
            if(map[nx][ny] == 0) {
                return new int[]{nx, ny, nowDist};
            }
        }
        return new int[]{-1};
    }

    private static int[] isBack() {
        int nowDist = (robot.dist + 2) % 4;
        int nx = robot.x + dist[nowDist][0];
        int ny = robot.y + dist[nowDist][1];

        if(map[nx][ny] == 2) return new int[] {nx, ny, robot.dist};
        return new int[]{-1};
    }
}
