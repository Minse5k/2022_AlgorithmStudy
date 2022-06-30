package 프로그래머스;

public class PM_자물쇠와열쇠 {
    static int N, M;
    public static void main(String[] args) throws Exception {
        System.out.println(solution(new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}}, new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}));
    }
    //bfs
    public static boolean solution(int[][] key, int[][] lock) {
        N = lock.length;
        M = key.length;

        int index = 2;
        for(int d=0; d<4; d++) {
            key = rotate(key);

            for (int i = -(M - 1); i < N; i++) {
                next:
                for (int j = -(M - 1); j < N; j++) {
                    index++;
                    for (int x = 0; x < M; x++) {
                        for (int y = 0; y < M; y++) {
                            if (!isIn(i + x, j + y)) continue;
                            if (key[x][y] == lock[i + x][j + y]) {
                                continue next;
                            } else if (key[x][y] == 1) {
                                lock[i + x][j + y] = index;
                            }
                        }자무
                    }
                    if (check(lock, index)) return true;
                }
            }
        }
        return false;
    }

    public static boolean check(int[][] lock, int index) {
        for(int x=0; x<N; x++) {
            for(int y=0; y<N; y++) {
                if(lock[x][y] != 1 && lock[x][y] != index) return false;
            }
        }
        return true;
    }

    public static int[][] rotate(int[][] key) {
        int[][] tmp = new int[M][M];
        for(int x = 0; x < M; x++){
            for(int y = 0; y < M; y++){
                tmp[y][M-1-x] = key[x][y];
            }
        }
        return tmp;
    }

    public static boolean isIn(int x, int y) {
        return 0<=x && x<N && 0<=y && y<N;
    }
}
