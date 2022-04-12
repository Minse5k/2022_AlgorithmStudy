import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_G3_20209_스트레이트스위치게임 {
    static int N, K;
    static ArrayList<Integer>[] switchList;
    static HashSet<String> visited = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        String str = "";
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            str += Integer.parseInt(st.nextToken());
        }
        switchList = new ArrayList[K];
        for(int i=0; i<K; i++) {
            switchList[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            for(int j=0; j<size; j++) {
                switchList[i].add(Integer.parseInt(st.nextToken()) - 1);
            }
        }

        System.out.println(bfs(str));
    }

    private static int bfs(String start) {
        Queue<String> q = new LinkedList<>();
        q.add(start);
        visited.add(start);
        int depth = 0;
        while(!q.isEmpty()) {
            int size = q.size();

            for(int s = 0; s<size; s++) {
                String now = q.poll();
                if(isFinished(now)) {
                    return depth;
                }
                for(int i=0; i<switchList.length; i++) {
                    StringBuilder tmp = new StringBuilder(now);
                    for(int j=0; j<switchList[i].size(); j++) {
                        char nowChar = tmp.charAt(switchList[i].get(j));
                        int num = ((nowChar-'0') + i+1)%5;
                        tmp.setCharAt(switchList[i].get(j), (char)(num+'0'));
                    }
                    if(visited.contains(tmp.toString())) continue;
                    visited.add(tmp.toString());
                    q.add(tmp.toString());
                }
            }
            depth++;
        }
        return -1;
    }
    private static boolean isFinished(String str) {
        for(int i=1; i<str.length(); i++) {
            if(str.charAt(0) != str.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
