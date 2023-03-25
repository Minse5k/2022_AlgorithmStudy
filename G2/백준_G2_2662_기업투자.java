import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n, m;
    static int[][] dp, info, invest;
    static int[] path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]); // 투자할 수 있는 자금
        m = Integer.parseInt(input[1]); // 기업 개수

        invest = new int[n + 1][m + 1];
        info = new int[n + 1][m + 1];
        dp = new int[n + 1][m + 1];
        path = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            input = br.readLine().split(" ");
            for (int j = 1; j <= m; j++) {
                int benefit = Integer.parseInt(input[j]);
                info[i][j] = benefit;
            }
        }

        findMaxBenefit();
        getPath(n, m);

        StringBuilder result = new StringBuilder();

        result.append(dp[n][m]).append('\n');

        for (int i = 1; i <= m; i++) {
            result.append(path[i]).append(' ');
        }

        System.out.println(result);
        br.close();
    }

    public static void findMaxBenefit() {
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = n - j; k >= 0; k--) {
                    if (dp[k][i - 1] + info[j][i] > dp[j + k][i]) {
                        dp[j + k][i] = dp[k][i - 1] + info[j][i];
                        invest[j + k][i] = j;
                    }
                }
            }
        }
    }

    public static void getPath(int n, int m) {
        if (m == 0) {
            return;
        }
        path[m] = invest[n][m];
        getPath(n - path[m], m - 1);
    }
}
