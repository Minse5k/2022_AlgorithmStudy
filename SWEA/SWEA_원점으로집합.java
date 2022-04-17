import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D4_원점으로집합 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        for(int t=1; t<=TC; t++) {
            int N = Integer.parseInt(br.readLine());
            boolean isChecked = true;
            boolean isFinished = false;
            int max = 0;
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int len = Math.abs(x) + Math.abs(y);
                //거리 최대 구하기
                if(len%2 == 0) {
                    if(!isFinished && i!=0 && isChecked) {
                        ans.append("#"+t+" -1\n");
                        isFinished = true;
                    }
                    isChecked = false;
                } else {
                    if(!isFinished && i!=0 && !isChecked) {
                        ans.append("#"+t+" -1\n");
                        isFinished = true;
                    }
                    isChecked = true;
                }
                max = Math.max(max, len);
            }
            if(isFinished) continue;
            int sum = 0;
            int cnt = 0;
            while(true) {
                sum+=cnt;
                if(sum==max) {
                    break;
                }
                if(sum > max && sum%2 == max%2) {
                    break;
                }
                cnt++;
            }
            //1, 3, 6, 10, 15
            ans.append("#"+t+" "+(cnt)+"\n");
        }
        System.out.printf(ans.toString());
    }
}
