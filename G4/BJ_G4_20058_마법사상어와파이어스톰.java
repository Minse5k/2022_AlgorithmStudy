import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G4_20058_마법사상어와파이어스톰 {
    static int N, Q;
    static int[][] map;
    static int size;
    static int sum=0;
    static boolean[][] visited;
    static int[][] dist = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        size = 1<<N;
        map = new int[size][size];
        for(int i=0; i<size; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<size; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                sum+=map[i][j];
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<Q; i++) {
            int L = 1<<Integer.parseInt(st.nextToken());
            map = rotate(L);
            melt();
        }
        System.out.println(sum);
        visited = new boolean[size][size];
        int iceSize = 0;
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if(visited[i][j] || map[i][j] == 0) continue;
                iceSize = Math.max(iceSize, findIce(i, j));
            }
        }
        System.out.println(iceSize);
    }

    private static int[][] rotate(int l) {
        int[][] tmp = new int[size][size];
        for(int i=0; i<size; i+=l) {
            for(int j=0; j<size; j+=l) {
                for(int x=0; x<l; x++) {
                    for(int y=0; y<l; y++) {
                        tmp[i+y][j+l-x-1] = map[i+x][j+y];
                    }
                }
            }
        }
        return tmp;
    }

    private static void melt() {
        //LinkedList<Point> meltList = new LinkedList<>();
        boolean[][] checked = new boolean[size][size];
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if(map[i][j] == 0) continue;
                int cnt = 0;
                for(int k=0; k<4; k++) {
                    int nx = i + dist[k][0];
                    int ny = j + dist[k][1];
                    if(!isIn(nx, ny) || map[nx][ny] == 0) continue;
                    cnt++;
                }
                if(cnt < 3) {
                    checked[i][j] = true;
                }
            }
        }
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if(!checked[i][j]) continue;
                map[i][j]--;
                sum--;
            }
        }
    }

    private static int findIce(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        visited[x][y] = true;
        int iceSize = 0;
        while(!q.isEmpty()) {
            Point now = q.poll();
            iceSize++;

            for(int i=0; i<4; i++) {
                int nx = now.x + dist[i][0];
                int ny = now.y + dist[i][1];

                if(!isIn(nx, ny) || visited[nx][ny] || map[nx][ny] == 0 ) continue;
                visited[nx][ny] = true;
                q.add(new Point(nx, ny));
            }
        }
        return iceSize;
    }

    private static boolean isIn(int x, int y) {
        return 0<=x && x<size && 0<=y && y<size;
    }
}
