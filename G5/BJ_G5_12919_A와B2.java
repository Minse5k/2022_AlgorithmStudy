import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String ans = br.readLine();

        bfs(ans, str);
        System.out.println(0);
    }

    public static void bfs(String startStr, String ans) {
        Queue<String> q = new LinkedList<>();

        q.add(startStr);

        while (!q.isEmpty()) {
            String str = q.poll();
            if (str.length() == ans.length()) {
                if (str.equals(ans)) {
                    System.out.println(1);
                    System.exit(0);
                }
                continue;
            }
            if(str.charAt(str.length() - 1) == 'A') {
                String nextStr = str.substring(0, str.length()-1);
                q.add(nextStr);
            }
            if(str.charAt(0) == 'B') {
                StringBuilder nextStr = new StringBuilder(str.substring(1, str.length()));
                q.add(nextStr.reverse().toString());
            }
        }
    }
}
