import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G3_1039_교환 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashSet<String> visited;
        Queue<String> queue = new LinkedList<>();
        String str = st.nextToken();
        queue.add(str);
        int K = Integer.parseInt(st.nextToken());
        int N = str.length();
        int max = -1;

        for(int k=0; k<K; k++) {
            visited = new HashSet<>();
            int size = queue.size();

            for(int q = 0; q < size; q++) {
                String nowStr = queue.poll();

                for(int i=0; i<N-1; i++) {
                    for(int j = i+1; j<N; j++) {
                        if(i==0 && nowStr.charAt(j) == '0') continue;
                        String swapStr = swap(nowStr, i, j);
                        if(visited.contains(swapStr)) continue;
                        visited.add(swapStr);
                        queue.add(swapStr);

                        if(k==K-1) {
                            max = Math.max(max, Integer.parseInt(swapStr));
                        }
                    }
                }
            }
        }
        System.out.printf("%d", max);
    }

    private static String swap(String str, int i, int j) {
        StringBuilder swapStr = new StringBuilder();
        swapStr.append(str);
        swapStr.setCharAt(i, str.charAt(j));
        swapStr.setCharAt(j, str.charAt(i));

        return swapStr.toString();
    }
}