import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G5_14500_테르미노 {
    public static void main(String[] args) throws IOException {
        int[][][] tetromino = {
                // ㅡ
                {{0, 0}, {0, 1}, {0, 2}, {0, 3}},
                {{0, 0}, {1, 0}, {2, 0}, {3, 0}},
                // ㅁ
                {{0, 0}, {0, 1}, {1, 0}, {1, 1}},
                //ㄴ
                {{0, 0}, {1, 0}, {2, 0}, {2, 1}},
                {{1, 0}, {1, 1}, {1, 2}, {0, 2}},
                {{0, 0}, {0, 1}, {1, 1}, {2, 1}},
                {{1, 0}, {0, 0}, {0, 1}, {0, 2}},
                {{2, 0}, {2, 1}, {1, 1}, {0, 1}},
                {{0, 0}, {0, 1}, {0, 2}, {1, 2}},
                {{0, 1}, {0, 0}, {1, 0}, {2, 0}},
                {{0, 0}, {1, 0}, {1, 1}, {1, 2}},
                // ㄱㄴ
                {{0, 0}, {1, 0}, {1, 1}, {2, 1}},
                {{1, 0}, {1, 1}, {0, 1}, {0, 2}},
                {{0, 1}, {1, 1}, {1, 0}, {2, 0}},
                {{0, 0}, {0, 1}, {1, 1}, {1, 2}},
                // ㅗ
                {{1, 0}, {1, 1}, {1, 2}, {0, 1}},
                {{0, 0}, {0, 1}, {0, 2}, {1, 1}},
                {{0, 0}, {1, 0}, {2, 0}, {1, 1}},
                {{1, 0}, {1, 1}, {2, 1}, {0, 1}}
        };
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][]map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int max = 0;
        //완탐
        for(int t=0; t<tetromino.length; t++) {
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    //블럭 크기는 반드시 4
                    int sum = 0;
                    for(int k=0; k<4; k++) {
                        int nx = i + tetromino[t][k][0];
                        int ny = j + tetromino[t][k][1];
                        if(!isIn(nx, ny, N, M)) continue;

                        sum+=map[nx][ny];
                    }
                    max = Math.max(sum, max);
                }
            }
        }
        System.out.printf("%d", max);
    }

    private static boolean isIn(int x, int y, int n, int m) {
        return 0<=x && x<n && 0<=y && y<m;
    }
}
