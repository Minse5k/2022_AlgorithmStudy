import java.util.*;

class Solution {
    static class Point{
        int x;
        int y;
        int dir;
        int cost;

        Point(int x, int y, int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }
    }
    static int N = 0;
    static int[][] dist = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int solution(int[][] board) {
        N = board.length;
        return bfs(board);
    }
    public static int bfs(int[][] board) {
        Queue<Point> q = new LinkedList<>();
        int[][][] visited = new int[2][N][N];
        q.add(new Point(0, 0, 0, 0));

        while(!q.isEmpty()) {
            Point now = q.poll();

            for(int i=0; i<4; i++) {
                int nx = now.x+dist[i][0];
                int ny = now.y+dist[i][1];

                if(!isIn(nx, ny) || board[nx][ny] == 1) continue;
                int nextDir = i < 2 ? 0 : 1;
                int nextCost = 0;
                if((now.x==0&&now.y==0) || nextDir == now.dir) {
                    nextCost = 100;
                } else {
                    nextCost = 600;
                }

                if(visited[nextDir][nx][ny] == 0 || visited[nextDir][nx][ny] > now.cost + nextCost) {
                    visited[nextDir][nx][ny] = now.cost+nextCost;
                    q.add(new Point(nx, ny, nextDir, now.cost+nextCost));
                }
            }
        }
        int ans = 0;
        if(visited[0][N-1][N-1] == 0) {
            ans = visited[1][N-1][N-1];
        } else if(visited[1][N-1][N-1] == 0) {
            ans = visited[0][N-1][N-1];
        } else {
            ans = Math.min(visited[0][N-1][N-1], visited[1][N-1][N-1]);
        }
        return ans;
    }

    public static boolean isIn(int x, int y) {
        return 0<=x && x<N && 0<=y && y<N;
    }
}
