import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_G5_3190_뱀 {
    static class Point{
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    static int N, K, L;
    static int[][] map;
    static Map<Integer, Character> snakeDist = new HashMap<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];

        for(int i=1; i<=K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[r][c] = 1;
        }
        L = Integer.parseInt(br.readLine());
        for(int i=0; i<L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            Character dist =  st.nextToken().charAt(0);
            snakeDist.put(time, dist);
        }

        playSnakeGame();
    }

    private static void playSnakeGame (){
        Deque<Point> snake = new LinkedList<>();
        snake.addFirst(new Point(1, 1));
        // 상, 우, 하, 좌
        int[][] dist = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int nowDist = 1;
        int time = 0;

        while(true) {
            if(snakeDist.containsKey(time)) {
                if(snakeDist.get(time) == 'L') {
                    nowDist = nowDist-1;
                    if(nowDist < 0) nowDist = 3;
                } else if(snakeDist.get(time) == 'D'){
                    nowDist = nowDist+1;
                    if(nowDist > 3) nowDist = 0;
                }
            }
            //다음으로 이동해야할 위치는 머리 + 이동
            int nx = snake.getFirst().x + dist[nowDist][0];
            int ny = snake.getFirst().y + dist[nowDist][1];
            if(!isIn(nx, ny) || isSnake(nx, ny, snake)) break;
            snake.addFirst(new Point(nx, ny));
            //사과를 먹었다면
            if(map[nx][ny] != 1){
                //사과 먹지 않앗다면 꼬리 자르기
                snake.pollLast();
            } else {
                map[nx][ny] = 0;
            }
            time++;
        }
        System.out.println(time+1);
    }
    private static boolean isSnake(int x, int y, Deque<Point> snake) {
        return snake.contains(new Point(x, y));
    }

    private static boolean isIn(int x, int y) {
        return 0<x && x<=N && 0<y && y<=N;
    }
}
