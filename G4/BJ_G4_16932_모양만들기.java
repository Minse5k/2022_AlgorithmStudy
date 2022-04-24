import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_G4_16932_모양만들기 {
    static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M;
    static int[][] map;
    static int[][] dist = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static HashMap<Integer, Integer> shapeInfo = new HashMap<>();
    static int shapeKey = 2;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        ArrayList<Point> zeroList = new ArrayList<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) zeroList.add(new Point(i, j));
            }
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 1) {
                    setShapeInfo(i, j);
                }
            }
        }

        int max = 0;
        for(int s=0; s<zeroList.size(); s++) {
            int x = zeroList.get(s).x;
            int y = zeroList.get(s).y;
            max = Math.max(max, getLinkedShapeSize(x, y));
        }

        System.out.println(max);
    }

    public static int getLinkedShapeSize(int x, int y) {
        HashSet<Integer> visited = new HashSet<>();
        int size = 1;
        for(int i=0; i<4; i++) {
            int nx = x + dist[i][0];
            int ny = y + dist[i][1];

            if(!isIn(nx, ny) || map[nx][ny] == 0 || visited.contains(map[nx][ny])) continue;
            visited.add(map[nx][ny]);
            size += shapeInfo.get(map[nx][ny]);
        }
        return size;
    }

    public static void setShapeInfo(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        map[x][y] = shapeKey;
        int shapeSize = 1;

        while(!q.isEmpty()) {
            Point now = q.poll();
            for(int i=0; i<4; i++) {
                int nx = now.x+dist[i][0];
                int ny = now.y+dist[i][1];

                if(!isIn(nx, ny)  || map[nx][ny] != 1) continue;
                map[nx][ny] = shapeKey;
                q.add(new Point(nx, ny));
                shapeSize++;
            }
        }
        shapeInfo.put(shapeKey, shapeSize);
        shapeKey++;
    }

    private static boolean isIn(int x, int y) {
        return 0<=x && x<N && 0<=y && y<M;
    }
}
