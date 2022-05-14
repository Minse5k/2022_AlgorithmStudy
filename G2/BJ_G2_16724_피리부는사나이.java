import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G2_16724_피리부는사나이 {
    static int N, M;
    static char[][] map;
    static int[][] cycle;
    static boolean[][] visited;
    static int[][] dist = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int num;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][];
        cycle = new int[N][M];
        visited = new boolean[N][M];
        int groupNum = 1;

        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(cycle[i][j] != 0) continue;
                num = 0;
                isGroup(i, j);
                if(num!=0) {
                    findGroup(i, j, num);
                } else {
                    findGroup(i, j, groupNum);
                    groupNum++;
                }
            }
        }
        System.out.printf("%d", groupNum-1);
    }

    public static void isGroup(int x, int y) {
        if(cycle[x][y] != 0) {
            num = cycle[x][y];
            return;
        }
        if(!isIn(x, y) || visited[x][y]) {
            return;
        }
        visited[x][y] = true;
        int d = getDist(map[x][y]);
        isGroup(x + dist[d][0], y + dist[d][1]);
    }

    public static void findGroup(int x, int y, int groupNum) {
        if(!isIn(x, y) || cycle[x][y] != 0) {
            return;
        }
        cycle[x][y] = groupNum;
        int d = getDist(map[x][y]);
        findGroup(x + dist[d][0], y + dist[d][1], groupNum);
    }

    public static int getDist(char d) {
        switch(d) {
            case 'U':
                return 0;
            case 'R':
                return 1;
            case 'D':
                return 2;
            case 'L':
                return 3;
        }
        return 0;
    }

    public static boolean isIn(int x, int y) {
        return 0<=x && x<N && 0<=y && y<M;
    }
}
