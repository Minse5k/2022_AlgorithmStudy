import java.util.*;

class Solution {
    static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static boolean[][] map;
    static int N, M;
    static int[][] dist = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int solution(String[] maps) {
        M = maps[0].length();
        N = maps.length;
        map = new boolean[N][M];
        Point start = new Point(0, 0);
        Point end = new Point(0, 0);
        Point lever = new Point(0, 0);
        int len = 0;
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                char ch = maps[i].charAt(j);
                if(ch == 'S') {
                    start = new Point(i, j);
                } else if(ch == 'E') {
                    end = new Point(i, j);
                } else if(ch == 'L') {
                    lever = new Point(i, j);
                } 
                map[i][j] = ch == 'X' ? false : true;
            }
        }

        
        int num = bfs(start, lever);
        if(num == 0) {
            return -1;
        } else {
            len += num;
            num = 0;
        }
        
        num = bfs(lever, end);
        if(num == 0) {
            return -1;
        } else {
            len += num;
            num = 0;
        }
        return len;
    }
    
    public static int bfs(Point st, Point end) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.add(st);
        visited[st.x][st.y] = true;
        int depth = 0;
        
        while(!q.isEmpty()) {
            int size = q.size();
            for(int s=0; s<size; s++) {
                Point now = q.poll();
                                
                for(int i=0; i<4; i++) {
                    int nx = now.x + dist[i][0];
                    int ny = now.y + dist[i][1];
                    
                    if(!isIn(nx, ny)) continue;

                    if(nx == end.x && ny == end.y) return depth+1;

                    if(visited[nx][ny] || !map[nx][ny]) continue;

                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny));
                }
            }           
            depth++;
        }
        
        return 0;
    }
    
    public static boolean isIn(int x, int y) {
        return 0<=x && x<N && 0<=y && y<M;
    }
    
    
}
