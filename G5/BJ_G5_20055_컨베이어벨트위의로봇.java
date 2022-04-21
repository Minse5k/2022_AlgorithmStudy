import java.io.*;
import java.util.*;

public class BJ_G5_20055_컨베이어벨트위의로봇 {
    static int N, K;
    static int[] convey;
    static boolean[] hasRobot;
    static int zeroCnt = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        convey = new int[2*N];
        hasRobot = new boolean[2*N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<2*N; i++) {
            convey[i] = Integer.parseInt(st.nextToken());
            if(convey[i]==0) zeroCnt++;
        }

        int time = 0;

        while(zeroCnt < K) {
            conveyMove();
            robotMove();
            update();
            time++;
        }
        System.out.printf("%d", time);
    }

    private static void conveyMove() {
        int tmp = convey[2*N-1];
        boolean tmpRobot = hasRobot[2*N-1];
        for(int i=2*N-1; i>0; i--) {
            convey[i] = convey[i-1];
            hasRobot[i] = hasRobot[i-1];
        }
        convey[0] = tmp;
        hasRobot[0] = tmpRobot;
        //N번째칸에 로봇이 있다면 하차
        if(hasRobot[N-1]) hasRobot[N-1] = false;
    }
    private static void robotMove() {
        //0은 아직 로봇을 올려두기 전이기에 무조건 false라 따로 움직이지 않음
        for(int i=N-2; i>0; i--) {
            //현재 칸에 로봇이있고, 이동하려는 칸의 내구도가 0보다 크다면
            if(hasRobot[i] && convey[i+1] > 0 && !hasRobot[i+1]) {
                hasRobot[i+1] = true;
                hasRobot[i] = false;
                convey[i+1]--;
                if(convey[i+1]==0) zeroCnt++;
            }
        }
        //N번째칸에 로봇이 있다면 하차
        if(hasRobot[N-1]) hasRobot[N-1] = false;
    }
    private static void update() {
        if(convey[0] > 0 && !hasRobot[0]) {
            convey[0]--;
            hasRobot[0]=true;
            if(convey[0]==0) zeroCnt++;
        }
    }
}
