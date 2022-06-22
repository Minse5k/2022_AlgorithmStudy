package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G4_17779_게리맨더링 {
    static int N;
    static int[][] map;
    static int sum = 0;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                sum+=map[i][j];
            }
        }

        Permutation(0, new int[2]);

        System.out.println(ans);
    }

    public static void solution(int x, int y, int d1, int d2) {
        //경계선 체크
        boolean[][] isBoundary = getBoundary(x, y, d1, d2);
        int max = -1;
        int min = Integer.MAX_VALUE;

        //1번구역
        int sum1 = 0;
        for(int i=0; i<x+d1; i++) {
            for(int j=0; j<=y; j++) {
                if(isBoundary[i][j]) break;
                sum1 += map[i][j];
            }
        }
        max = Math.max(max, sum1);
        min = Math.min(min, sum1);
        
        //2번구역
        int sum2 = 0;
        for(int i=0; i<=x+d2; i++) {
            for(int j=N-1; j>y; j--) {
                if(isBoundary[i][j]) break;
                sum2 += map[i][j];
            }
        }
        max = Math.max(max, sum2);
        min = Math.min(min, sum2);
        
        //3번구역
        int sum3 = 0;
        for(int i=x+d1; i<N; i++) {
            for(int j=0; j<y-d1+d2; j++) {
                if(isBoundary[i][j]) break;
                sum3 += map[i][j];
            }
        }
        max = Math.max(max, sum3);
        min = Math.min(min, sum3);
        
        //4번구역
        int sum4 = 0;
        for(int i=x+d2+1; i<N; i++) {
            for(int j=N-1; j>=y-d1+d2; j--) {
                if(isBoundary[i][j]) break;
                sum4 += map[i][j];
            }
        }
        max = Math.max(max, sum4);
        min = Math.min(min, sum4);
        
        //5번구역
        int sum5 = sum - sum1 - sum2 - sum3 - sum4;
        max = Math.max(max, sum5);
        min = Math.min(min, sum5);

        ans = Math.min(ans, max-min);
    }

    public static boolean[][] getBoundary(int x, int y, int d1, int d2) {
        boolean[][] isBoundary = new boolean[N][N];
        int i, j;
        for(i=x, j=y; i<=x+d1; i++, j--) {
            isBoundary[i][j] = true;
        }
        for(i=x, j=y; i<=x+d2; i++, j++) {
            isBoundary[i][j] = true;
        }
        for(i=x+d1, j=y-d1; i<=x+d1+d2; i++, j++) {
            isBoundary[i][j] = true;
        }
        for(i=x+d2, j=y+d2; i<=x+d2+d1; i++, j--) {
            isBoundary[i][j] = true;
        }
        return isBoundary;
    }

    public static void Permutation(int cnt, int[] d) {
        if(cnt == 2) {
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(!isIn(i + d[0] + d[1], j - d[0]) || !isIn(i + d[0] + d[1], j + d[1])) continue;

                    solution(i, j, d[0], d[1]);
                }
            }
            return;
        }

        for(int i=1; i<N; i++) {
            d[cnt] = i;
            Permutation(cnt+1, d);
        }
    }

    public static boolean isIn(int x, int y) {
        return 0<=x && x<N && 0<=y && y<N;
    }
}
