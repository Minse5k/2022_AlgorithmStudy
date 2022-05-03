import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_G5_6416_트리인가 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder ans = new StringBuilder();
        HashSet<Integer> hsU = new HashSet<>();
        HashSet<Integer> hsV = new HashSet<>();
        boolean isTree = false;
        int t = 1;
        loop: while (true) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                if (u == 0 || v == 0) {
                    if(hsV.size()!=0) {
                        int root = 0;
                        Iterator<Integer> iter = hsU.iterator();
                        while(iter.hasNext()) {
                            if(!hsV.contains(iter.next())) root++;
                        }
                        if(root!=1) isTree=true;
                    }
                    if (isTree) {
                        ans.append("Case " + t + " is not a tree.\n");
                    } else {
                        ans.append("Case " + t + " is a tree.\n");
                    }
                    t++;
                    hsU = new HashSet<>();
                    hsV = new HashSet<>();
                    isTree=false;
                    continue;
                }
                if (u == -1 || v == -1) {
                    break loop;
                }
                if (!hsV.add(v)) {
                    isTree = true;
                }
                hsU.add(u);
            }
        }
        System.out.printf("%s", ans.toString());
    }
}
