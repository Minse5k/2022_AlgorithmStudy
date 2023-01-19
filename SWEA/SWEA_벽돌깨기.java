import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static class Point{
        int x;
        int y;
        int num;
        Point(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
    static int N, W, H;
    static int[][] map;
    static int min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer ans = new StringBuffer();
        int TC = Integer.parseInt(br.readLine());

        for(int t=1; t<=TC; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            min = Integer.MAX_VALUE;

            for(int i=0; i<H; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            Permutation(0, new int[N]);
            ans.append("#" + t + " " + min + "\n");
        }
        System.out.printf(ans.toString());
    }

    private static void Permutation(int cnt, int[] arr) {
        if(cnt == N) {
            int[][] tmp = copyMap();
            playSimulation(arr, tmp);
            min = Math.min(min, getRestRockNum(tmp));
            return;
        }

        for(int i=0; i<W; i++) {
            arr[cnt] = i;
            Permutation(cnt+1, arr);
        }
    }

    private static int[][] copyMap() {
        int[][] tmp = new int[H][W];

        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                tmp[i][j] = map[i][j];
            }
        }
        return tmp;
    }

    private static void playSimulation(int[] arr, int[][] tmp) {
        int[][]dist = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited;

        for(int i=0; i<N; i++) {
            visited = new boolean[H][W];
            int col = arr[i];
            int row = 0;
            for(int j=0; j<H; j++) {
                if(tmp[j][col]!=0) {
                    row=j;
                    break;
                }
            }
            q.add(new Point(row, col, tmp[row][col]));

            while(!q.isEmpty()) {
                Point nP = q.poll();
                for(int j=0; j<4; j++) {
                    for(int k=0; k<nP.num; k++) {
                        int nx = nP.x + (dist[j][0] * k);
                        int ny = nP.y + (dist[j][1] * k);

                        if(!isIn(nx, ny) || visited[nx][ny] || tmp[nx][ny] == 0) continue;
                        visited[nx][ny] = true;
                        q.add(new Point(nx, ny, tmp[nx][ny]));
                        tmp[nx][ny] = 0;
                    }
                }
            }
            moveRock(tmp);
        }
    }
    private static void moveRock(int[][] tmp) {
        Queue<Integer> q;

        for(int w = 0 ; w < W ; w++) {
            q = new LinkedList<>();

            for(int h = H-1 ; h >=0 ; h--) {
                if(tmp[h][w] > 0) q.offer(tmp[h][w]);
                tmp[h][w] = 0;
            }

            for(int h = H-1 ; h >= 0 ; h--) {
                if(!q.isEmpty()) {
                    tmp[h][w] = q.poll();
                }
            }
        }
    }

    private static int getRestRockNum(int[][] tmp) {
        int cnt = 0;
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                if(tmp[i][j] == 0) continue;
                cnt++;
            }
        }
        return cnt;
    }

    private static boolean isIn(int x, int y) {
        return 0<=x && x<H && 0<=y && y<W;
    }
}
