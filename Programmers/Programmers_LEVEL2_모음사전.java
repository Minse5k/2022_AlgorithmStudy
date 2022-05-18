public class Programmers_모음사전 {
    static int cnt = 0;
    static int ans = 0;
    static boolean isFinish = false;

    public static void main(String[] args) {
        System.out.println(solution("AAAAR"));
    }
    public static int solution(String word) {
        int N = word.length();
        dfs("", word, 0, N);
        return ans;
    }

    public static void dfs(String str, String word, int len, int N) {
        if(isFinish) return;
        if(str.equals(word)) {
            ans = cnt;
            isFinish = true;
            return;
        }
        if(len == 5) {
            return;
        }

        cnt++;
        dfs(str+'A', word, len+1, N);
        cnt++;
        dfs(str+'E', word, len+1, N);
        cnt++;
        dfs(str+'I', word, len+1, N);
        cnt++;
        dfs(str+'O', word, len+1, N);
        cnt++;
        dfs(str+'U', word, len+1, N);
    }
}
