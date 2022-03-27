import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G4_12869_뮤탈리스크 {
    static class SCV{
        int s1;
        int s2;
        int s3;
        int cnt;
        SCV(int s1, int s2, int s3, int cnt) {
            this.s1 = s1;
            this.s2 = s2;
            this.s3 = s3;
            this.cnt = cnt;
        }

    }

    public static void main(String[] args) throws IOException {
        int[] scv = new int[3];
        int N;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }
        bfs(scv);
    }

    private static void bfs(int[] scv) {
        boolean [][][] visited = new boolean[61][61][61];
        int[][] deal = {{9, 3, 1}, {9, 1, 3}, {3, 9, 1}, {3, 1, 9}, {1, 3, 9}, {1, 9, 3}};
        int[][][] dp = new int[61][61][61];
        Queue<SCV> q = new LinkedList<>();

        visited[0][0][0] = true;
        q.add(new SCV(0, 0, 0, 0));

        while(!q.isEmpty()) {
            SCV now = q.poll();

            for(int i=0; i<6; i++) {
                int nextS1 = now.s1+deal[i][0];
                int nextS2 = now.s2+deal[i][1];
                int nextS3 = now.s3+deal[i][2];
                if(nextS1 > 60) nextS1 = 60;
                if(nextS2 > 60) nextS2 = 60;
                if(nextS3 > 60) nextS3 = 60;

                if(visited[nextS1][nextS2][nextS3]) continue;
                if(nextS1 >= scv[0] && nextS2 >= scv[1] && nextS3 >= scv[2]) {
                    System.out.println(now.cnt+1);
                    return;
                }
                visited[nextS1][nextS2][nextS3] = true;
                q.add(new SCV(nextS1, nextS2, nextS3, now.cnt+1));
            }
        }

    }
}
