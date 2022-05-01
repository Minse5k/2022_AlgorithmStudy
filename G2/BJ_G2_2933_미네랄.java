import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_G2_2939_미네랄 {
    static class Point{
        int x;
        int y;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M;
    static char[][] map;
    static HashSet<Point> mineral;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder ans = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][];

        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        int T = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int t=0; t<T; t++) {
            int r = Integer.parseInt(st.nextToken());
            if(t%2==0) {
                throwStick(N-r, true);
            } else {
                throwStick(N-r, false);
            }
            boolean[][] visited = new boolean[N][M];
            loop: for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(map[i][j] == 'x') {
                        if(visited[i][j]) continue;
                        mineral = new HashSet<>();
                        visited[i][j] = true;
                        if(!isClusterWithBottom(i, j, visited)) {
                            moveCluster();
                            break loop;
                        }
                    }
                }
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                ans.append(map[i][j]);
            }
            if(i!=N-1) ans.append("\n");
        }
        System.out.printf("%s", ans.toString());
    }

    public static void moveCluster() {
        int height = 100;
        Iterator<Point> iter = mineral.iterator();

        while(iter.hasNext()) {
            Point now = iter.next();
            int x = now.x;
            int y = now.y;
            map[x][y] = '.';
            for(int h=x+1; h<N; h++) {
                if(map[h][y] == 'x' && !mineral.contains(new Point(h, y))) {
                    height = Math.min(height, h-x-1);
                    break;
                }
            }
            height = Math.min(height, N-1-x);
        }

        Iterator<Point> iter2 = mineral.iterator();
        while(iter2.hasNext()) {
            Point now = iter2.next();
            int x = now.x;
            int y = now.y;
            map[x+height][y] = 'x';
        }
    }

    public static boolean isClusterWithBottom(int r, int c, boolean[][] visited) {
        Queue<Point> q = new LinkedList<>();
        int[][] dist = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean isTrue = false;
        q.add(new Point(r, c));
        mineral.add(new Point(r, c));
        while(!q.isEmpty()) {
            Point now = q.poll();
            if(now.x == N-1) {
                isTrue = true;
            }

            for(int i=0; i<4; i++) {
                int nx = now.x + dist[i][0];
                int ny = now.y + dist[i][1];

                if(!isIn(nx, ny) || visited[nx][ny] || map[nx][ny] != 'x') continue;
                visited[nx][ny] = true;
                mineral.add(new Point(nx, ny));
                q.add(new Point(nx, ny));
            }
        }
        if(isTrue) return true;
        else return false;
    }

    public static void throwStick(int row, boolean isLeft) {
        if(isLeft) {
            for(int i=0; i<M; i++) {
                if(map[row][i] == 'x') {
                    map[row][i] = '.';
                    return;
                }
            }
        } else {
            for(int i=M-1; i>=0; i--) {
                if(map[row][i] == 'x') {
                    map[row][i] = '.';
                    return;
                }
            }
        }
    }

    public static boolean isIn(int x, int y) {
        return 0<=x && x<N && 0<=y && y<M;
    }
}
