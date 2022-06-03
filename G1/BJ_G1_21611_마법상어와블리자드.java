package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_G1_21611_마법상어와블리자드 {
    static int N;
    static int[][] map;
    static int midPos;
    static int[][] dist = {{}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[] bombCnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        midPos = (N+1)/2;
        map = new int[N+1][N+1];
        bombCnt = new int[4];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            doMagic(d, s);
            moveMarble();
            bombMarble();
            groupMarble();
        }
        System.out.println(bombCnt[1] + 2*bombCnt[2] + 3*bombCnt[3]);
    }

    public static void groupMarble() {
        Queue<Integer> q = inputQueue();
        Queue<Integer> tmp = new LinkedList<>();

        if(q.size() == 0) return;
        int num = q.poll();
        int cnt = 1;
        while(!q.isEmpty()) {
            if(num != q.peek()) {
                tmp.add(num);
                tmp.add(cnt);
                num = q.poll();
                cnt = 1;
            } else {
                q.poll();
                cnt++;
            }
        }
        tmp.add(num);
        tmp.add(cnt);
        map = inputMap(tmp);
    }

    public static void bombMarble() {
        Queue<Integer> q = inputQueue();
        Queue<Integer> tmp = new LinkedList<>();

        while(!q.isEmpty()) {
            int num = q.poll();
            int cnt = 1;
            boolean isBomb = false;
            while(!q.isEmpty()) {
                if(num != q.peek()) {
                    if(cnt >= 4) {
                        bombCnt[num] += cnt;
                        isBomb = true;
                    } else {
                        while(cnt-- > 0) {
                            tmp.add(num);
                        }
                    }
                    num = q.poll();
                    cnt = 1;
                } else {
                    q.poll();
                    cnt++;
                }
            }
            if(cnt >= 1) {
                if(cnt >= 4) {
                    bombCnt[num] += cnt;
                    isBomb = true;
                } else {
                    while(cnt-- > 0) {
                        tmp.add(num);
                    }
                }
            }

            q = new LinkedList<>(tmp);

            tmp = new LinkedList<>();

            if(!isBomb) break;
        }
        map = inputMap(q);
    }


    public static void doMagic(int d, int s) {
        for(int i=1; i<=s; i++) {
            int nx = midPos + dist[d][0]*i;
            int ny = midPos + dist[d][1]*i;

            map[nx][ny] = -1;
        }
    }

    public static Queue<Integer> inputQueue() {
        Queue<Integer> q = new LinkedList<>();
        boolean[][] visited = new boolean[N+1][N+1];

        int x = 1;
        int y = 0;
        int d = 4;
        visited[1][0] = true;

        while(true) {
            int nx = x + dist[d][0];
            int ny = y + dist[d][1];

            if(nx == midPos && ny == midPos) break;
            if(!isIn(nx, ny) || visited[nx][ny]) {
                if(d == 4) {
                    d=2;
                } else if(d == 3) {
                    d=1;
                } else if(d == 2) {
                    d=3;
                } else if(d == 1) {
                    d=4;
                }
                continue;
            }
            visited[nx][ny] = true;
            if(map[nx][ny] != -1 && map[nx][ny] != 0) q.add(map[nx][ny]);
            x = nx;
            y = ny;
        }

        return q;
    }

    public static int[][] inputMap(Queue<Integer> q) {
        int[][] tmp = new int[N+1][N+1];
        boolean[][] visited = new boolean[N+1][N+1];

        visited[1][0] = true;
        int x = 1;
        int y = 0;
        int d = 4;
        while(q.size() >= N*N) {
            q.poll();
        }
        int cnt = N*N - q.size();

        while(true) {
            int nx = x + dist[d][0];
            int ny = y + dist[d][1];

            if(nx == midPos && ny == midPos) break;
            if(!isIn(nx, ny) || visited[nx][ny]) {
                if(d == 4) {
                    d=2;
                } else if(d == 3) {
                    d=1;
                } else if(d == 2) {
                    d=3;
                } else if(d == 1) {
                    d=4;
                }
                continue;
            }
            cnt--;
            visited[nx][ny] = true;
            x = nx;
            y = ny;
            if(cnt < 1) {
                tmp[nx][ny] = q.poll();
            }
        }

        return tmp;
    }
    public static void moveMarble() {
        Queue<Integer> q = inputQueue();
        map = inputMap(q);
    }

    public static boolean isIn(int x, int y) {
        return 1<=x && x<=N && 1<=y && y<=N;
    }
}
