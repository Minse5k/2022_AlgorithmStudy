import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x == y) return false;
        p[x] = y;

        return true;
    }
    public static int find(int x) {
        if(p[x]==x) return x;

        return p[x]=find(p[x]);

    }
    public static void make_set(int n) {
        for(int i=1;i<=n;i++) {
            p[i] = i;
        }
    }
    static int[] p;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Node[] info = new Node[m];
        for(int i=1;i<=m;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            info[i-1] = new Node(x, y, i);
        }
        for(int t=0;t<k;t++) {
            p = new int[n+1];
            Queue<Integer> q = new LinkedList<Integer>();
            //넣을 후보들 넣기

            make_set(n);
            int res = 0;
            for(int i=t;i<m;i++) {
                //턴이 지날 때 마다 가중치가 가장 적은 간선을 뺌.
                int x = find(info[i].x);
                int y = find(info[i].y);

                if(x==y) continue;
                union(x,y);
                q.add(i+1);
                res += info[i].w;
            }
            //사이클이 완성되었는지 확인
            boolean c = true;
            int flg = 0;
            for(int i=1;i<=n;i++) {
                if(i==1) {
                    flg = find(1);
                }
                if(flg != find(i)) {
                    c = false;
                    break;
                }
            }
            //확인이 되었으면 방문 표시하기
            if(c) {
                sb.append(res+" ");
            }else {
                for(int i=t;i<k;i++) {
                    sb.append("0 ");
                }
                break;
            }
        }
        System.out.println(sb);


    }
}
class Node implements Comparable<Node>{
    int x, y, w;

    public Node(int x, int y, int w) {
        super();
        this.x = x;
        this.y = y;
        this.w = w;
    }

    @Override
    public int compareTo(Node o) {
        // TODO Auto-generated method stub
        return this.w-o.w;
    }
}
