import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G4_1806_부분합 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] == S) {
                System.out.printf("1");
                System.exit(0);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N-1; i++) {
            int sum = arr[i];
            for (int j = i+1; j < N; j++) {
                if(min < j-i+1) {
                    break;
                }
                sum += arr[j];
                if (sum >= S) {
                    if(min > j-i+1) {
                        min = j-i+1;
                    }
                    break;
                }
            }
        }
        if(min == Integer.MAX_VALUE) {
            System.out.printf("0");
        }
        else {
            System.out.println(min);
        }
    }
}
