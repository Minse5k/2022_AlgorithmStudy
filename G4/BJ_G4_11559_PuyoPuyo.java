import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_G4_11559_PuyoPuyo {
    static class Point{
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }           
    static char[][] map = new char[12][];
    static boolean[][] isBigGroup = new boolean[12][6];
    static int[][] dist = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<12; i++) {
            map[i] = br.readLine().toCharArray();
        }
        int cnt = 0;

        while(isBroken()) {
            brokeMap();
            mapDown();
            isBigGroup = new boolean[12][6];
            cnt++;
        }
        System.out.printf("%d", cnt);
    }

    public static void mapDown() {
        for(int j=0; j<6; j++) {
            ArrayList<Character> list = new ArrayList<>();
            for(int i=11; i>=0; i--) {
                if(map[i][j] == '.') continue;
                list.add(map[i][j]);
            }
            for(int i=0; i<12; i++) {
                if(i < list.size()) {
                    map[11-i][j] = list.get(i);
                } else {
                    map[11-i][j] = '.';
                }
            }
        }
    }

    public static void brokeMap() {
        for(int i=0; i<12; i++) {
            for(int j=0; j<6; j++) {
                if(!isBigGroup[i][j]) continue;
                map[i][j] = '.';
            }
        }
    }

    public static boolean isBroken() {
        boolean isBroke = false;
        boolean[][] visited = new boolean[12][6];

        for(int i=0; i<12; i++) {
            for(int j=0; j<6; j++) {
                if(map[i][j] == '.' || visited[i][j]) continue;
                if(isGroup(i, j, visited)) {
                    isBroke = true;
                }
            }
        }
        return isBroke;
    }

    public static boolean isGroup(int x, int y, boolean[][] visited) {
        Queue<Point> q = new LinkedList<>();
        ArrayList<Point> list = new ArrayList<>();
        char color = map[x][y];

        q.add(new Point(x, y));
        list.add(new Point(x, y));
        visited[x][y] = true;

        while(!q.isEmpty()) {
            Point now = q.poll();

            for(int i=0; i<4; i++) {
                int nx = now.x + dist[i][0];
                int ny = now.y + dist[i][1];

                if(!isIn(nx, ny) || visited[nx][ny] || map[nx][ny] != color) continue;
                visited[nx][ny] = true;
                q.add(new Point(nx, ny));
                list.add(new Point(nx, ny));
            }
        }

        if(list.size() >= 4) {
            for(Point now: list) {
                isBigGroup[now.x][now.y] = true;
            }
            return true;
        }
        return false;
    }

    public static boolean isIn(int x, int y) {
        return 0<=x && x<12 && 0<=y && y<6;
    }
}
