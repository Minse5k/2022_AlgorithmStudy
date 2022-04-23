import java.util.*;

public class PM_LEVEL3_불량사용자 {
    static HashSet<String> hs = new HashSet<>();
    public static void main(String[] args) {
        System.out.println(solution());
    }

    public static int solution() {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "*rodo", "******", "******"};

        int answer = 0;

        Permutation( 0, new String[banned_id.length], new boolean[user_id.length], user_id, banned_id);
        answer = hs.size();

        return answer;
    }

    public static void Permutation(int cnt, String[] arr, boolean[] visited, String[] user_id, String[] banned_id) {
        if(cnt == banned_id.length) {
            String str = "";
            ArrayList<String> a = new ArrayList<>();
            for(int i=0; i<arr.length; i++) {
                a.add(arr[i]);
            }
            Collections.sort(a);
            for(int i=0; i<arr.length; i++) {
                str += a.get(i);
            }
            hs.add(str);
            return;
        }

        for(int i=0; i<user_id.length; i++) {
            if(visited[i] || !isChecked(user_id[i], banned_id[cnt])) continue;
            visited[i] = true;
            arr[cnt] = user_id[i];
            Permutation(cnt+1, arr, visited, user_id, banned_id);
            visited[i] = false;
        }
    }

    public static boolean isChecked(String user_id, String banned_id) {
        if(user_id.length() != banned_id.length()) return false;
        for(int i=0; i<user_id.length(); i++) {
            if(banned_id.charAt(i) == '*') continue;
            if(user_id.charAt(i) != banned_id.charAt(i)) return false;
        }
        return true;
    }
}
