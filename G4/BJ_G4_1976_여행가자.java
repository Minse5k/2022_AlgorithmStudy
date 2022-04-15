import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G4_1976_여행가자 {
    static int N, M;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parents = new int[N+1];
        //자신의 부모노드를 자신의 값으로 세팅
        for(int i=1;i<=N;i++){
            parents[i] = i;
        }
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num == 1) {
                    union(i, j);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        int parent = findSet(Integer.parseInt(st.nextToken()));
        for(int i=0; i<M-1; i++) {
            int now = Integer.parseInt(st.nextToken());
            if(parent != findSet(now)) {
                System.out.println("NO");
                System.exit(0);
            }
        }
        System.out.println("YES");
    }

    //a의 집합 찾기 : a의 대표자 찾기
    private static int findSet(int a){
        if(a==parents[a]) return a;
        return parents[a] = findSet(parents[a]);  //path compression
    }

    //a, b 두 집합 합치기
    private static void union(int a, int b){
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if(aRoot!=bRoot) {
            if(a<b) {
                parents[b] = a;
            } else {
                parents[a] = b;
            }
        }
    }
}
