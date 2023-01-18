import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int[] lowerCount,higherCount;
    static int result,N,M,count;
    static boolean[][] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        graph = null;
        for (int tc = 1; tc <= T; tc++) {
            result = 0;

            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            graph = new boolean[N+1][N+1];
            lowerCount = new int[N+1];
            higherCount = new int[N+1];


            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine(), " ");
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from][to] = true;
            }
            //입력끝

            for(int i=1; i<=N; i++){
                visited = new boolean[N+1];
                count = 0;
                dfs(i);
                higherCount[i] = count;
            }
            for(int i=1; i<=N; i++){
                if(lowerCount[i] + higherCount[i] == N-1){
                    result++;
                }
            }

            sb.append("#" + tc + " " + result + "\n");
        }
        System.out.print(sb.toString());
    }

    private static void dfs(int currentNode) {
        for(int i=1; i<=N; i++){
            //연결되어 있지 않거나 방문된경우는 continue
            if(!graph[currentNode][i] || visited[i]){
                continue;
            }
            //방문가능하면
            visited[i] = true;
            //해당노드가 방문됬다는것은 해당 노드보다 작은노드가 1개 존재한다는 것
            lowerCount[i] += 1;
            //어떤노드를 기준으로 count만큼 탐색했다는것은 count만큼 큰 수를 가진다는 것
            count +=1;
            dfs(i);
        }

    }

}
