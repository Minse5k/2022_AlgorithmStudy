import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ_G5_21610_마법사상어와비바라기 {
    static class Point {
        int x;
        int y;

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M;
    static int[][] map;
    static int[][] cloudMove;
    static boolean[][] visited;
    static LinkedList<Point> cloud = new LinkedList<>();
    //x, ←, ↖, ↑, ↗, →, ↘, ↓, ↙
    static int dist[][] = {{}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        cloudMove = new int[M][2];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int dist = Integer.parseInt(st.nextToken());
            int moveCnt = Integer.parseInt(st.nextToken());

            cloudMove[i][0] = dist;
            cloudMove[i][1] = moveCnt;
        }
        //초기 구름 세팅
        cloud.add(new Point(N, 1));
        cloud.add(new Point(N, 2));
        cloud.add(new Point(N-1, 1));
        cloud.add(new Point(N-1, 2));

        goSimulation();
    }

    private static void goSimulation() {
        for(int i=0; i<M; i++) {
            //1. 모든 구름이 d방향으로 s칸 이동한다.
            visited = new boolean[N+1][N+1];
            moveCloud(i);
            getWater();
            increaseWater();
            deleteCloud();
            setCloud();
        }
        getSumWater();
    }

    private static void moveCloud(int idx) {
        int d = cloudMove[idx][0];
        // N 번은 제자리로 돌아옴
        int mCnt = cloudMove[idx][1] % N;
        for(Point p: cloud) {
            int nx = p.x;
            int ny = p.y;
            for(int i=0; i<mCnt; i++) {
                nx+=dist[d][0];
                ny+=dist[d][1];
                if(nx > N) nx = 1;
                else if(nx < 1) nx = N;
                if(ny > N) ny = 1;
                else if(ny < 1) ny = N;
            }
            p.x = nx;
            p.y = ny;
            visited[p.x][p.y] = true;
        }
    }

    private static void getWater() {
        for(Point p: cloud) {
            map[p.x][p.y]++;
        }
    }

    private static void increaseWater() {
        for(Point p: cloud) {
            for(int i=2; i<=8; i+=2) {
                int nx = p.x+dist[i][0];
                int ny = p.y+dist[i][1];
                // 범위 초과하거나 대각선에 물이 없다면 continue
                if(!isIn(nx, ny) || map[nx][ny] <= 0) continue;
                map[p.x][p.y]++;
            }
        }
    }

    private static void deleteCloud() {
        cloud.clear();
    }

    private static void setCloud() {
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(map[i][j] < 2 || visited[i][j]) continue;
                cloud.add(new Point(i, j));
                map[i][j]-=2;
            }
        }
    }

    private static void getSumWater() {
        int sum = 0;
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                sum+=map[i][j];
            }
        }

        System.out.println(sum);
    }

    private static boolean isIn(int x, int y) {
        return 0<x && x<=N && 0<y && y<=N;
    }
}
