package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G4_20056_마법사상어와파이어볼 {
    static class FireBall{
        int r, c, m, s, d;

        FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        @Override
        public String toString() {
            return "FireBall{" +
                    "r=" + r +
                    ", c=" + c +
                    ", m=" + m +
                    ", s=" + s +
                    ", d=" + d +
                    '}';
        }
    }
    static int[][] dist = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    static ArrayList<FireBall>[][] map;
    static ArrayList<FireBall> fireBallList;
    static int N, M, K;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N+1][N+1];
        fireBallList = new ArrayList<>();
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            fireBallList.add(new FireBall(r, c, m, s, d));
        }

        while(K-- > 0) {
            moveFireBall();
            setFireBallList();
        }

        System.out.printf("%d", getFireBallWeight());
    }

    public static int getFireBallWeight() {
        int sum = 0;

        for(FireBall now : fireBallList) {
            sum+=now.m;
        }

        return sum;
    }

    public static void moveFireBall() {
        for(FireBall now : fireBallList) {
            int r = now.r;
            int c = now.c;
            int m = now.m;
            int s = now.s;
            int d = now.d;

            r += dist[d][0] * s%N;
            c += dist[d][1] * s%N;

            if(r > N)
                r -= N;
            else if(r <= 0)
                r += N;
            if(c > N)
                c -= N;
            else if(c <= 0)
                c += N;

            map[r][c].add(new FireBall(r, c, m, s, d));
        }
    }

    public static void setFireBallList() {
        fireBallList = new ArrayList<>();

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(map[i][j].size() == 1) {
                    fireBallList.add(map[i][j].get(0));
                } else if(map[i][j].size() >= 2) {
                    boolean isSame = (map[i][j].get(0).d % 2 == 0);
                    boolean isEven = true;
                    int m = map[i][j].get(0).m;
                    int s = map[i][j].get(0).s;
                    int size = map[i][j].size();

                    for(int k = 1; k < size; k++) {
                        m+=map[i][j].get(k).m;
                        s+=map[i][j].get(k).s;

                        if(isSame != (map[i][j].get(k).d % 2 == 0)) {
                            isEven = false;
                        }
                    }

                    // 모두 홀수 or 짝수
                    if(m/5 >= 1) {
                        if (isEven) {
                            fireBallList.add(new FireBall(i, j, m / 5, s / size, 0));
                            fireBallList.add(new FireBall(i, j, m / 5, s / size, 2));
                            fireBallList.add(new FireBall(i, j, m / 5, s / size, 4));
                            fireBallList.add(new FireBall(i, j, m / 5, s / size, 6));
                        } else {
                            fireBallList.add(new FireBall(i, j, m / 5, s / size, 1));
                            fireBallList.add(new FireBall(i, j, m / 5, s / size, 3));
                            fireBallList.add(new FireBall(i, j, m / 5, s / size, 5));
                            fireBallList.add(new FireBall(i, j, m / 5, s / size, 7));
                        }
                    }
                }
                map[i][j] = new ArrayList<>();
            }
        }
    }
}
