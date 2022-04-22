import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_G3_17370_육각형우리속의개미 {
    static int[][] dist = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] map = new int[51][51];
    static int N;
    static int ans = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map[26][25] = 1;
        dfs(25, 25, 0, 26, 25);
        System.out.println(ans);
    }

    private static void dfs(int r, int c, int turnCnt, int preR, int preC) {
        if(map[r][c] == 1) {
            if(turnCnt == N) ans++;
            return;
        }
        if(turnCnt >= N) {
            return;
        }

        map[r][c] = 1;
        for(int i=0; i<4; i++) {
            //아래는 r+c가 짝수일때만 이동 가능하다.
            if((i==0) && (r+c)%2==1) continue;
            //위는 r+c가 홀수일때만 이동 가능하다.
            if((i==1) && (r+c)%2==0) continue;
            int nr = r+dist[i][0];
            int nc = c+dist[i][1];
            if(nr == preR && nc == preC) continue;
            dfs(nr, nc, turnCnt+1, r, c);
        }
        map[r][c] = 0;
    }
}
