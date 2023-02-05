class Solution {
    static String answer = "impossible";
    static int N, M, K;
    // d, l, r, u
    static int[][] dist = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N=n;
        M=m; 
        K=k;
        dfs(x-1, y-1, r-1, c-1, k, "");
        return answer;
    }
    
    public static void dfs(int x, int y, int r, int c, int k, String str) {
        if(answer != "impossible") return;

        if(x==r && y==c && k==0) {
            answer = str;
             return;
        }
        
        int shortest = Math.abs(x-r) + Math.abs(y-c);
        if(k < shortest || shortest%2 != k%2) return;
        
        for(int i=0; i<4; i++) {
            int nx = x + dist[i][0];
            int ny = y + dist[i][1];
            
            if(!isIn(nx,ny)) continue;
            
            if(i==0) {
                dfs(nx, ny, r, c, k-1, str+'d');
            } else if(i==1) {
                dfs(nx, ny, r, c, k-1, str+'l');

            } else if(i==2) {
                dfs(nx, ny, r, c, k-1, str+'r');

            } else {
                dfs(nx, ny, r, c, k-1, str+'u');
            }
        }
    }
    
    public static boolean isIn(int x, int y) {
        return 0<=x && x<N && 0<=y && y<M;
    }
}
