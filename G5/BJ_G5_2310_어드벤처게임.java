import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G5_2310_어드벤처게임 {
    static ArrayList<Integer>[] nextRoom;
    static char[] rooms;
    static int[] getMoney;
    static int N;
    static boolean isFinished;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder ans = new StringBuilder();
        N = 0;
        while((N = Integer.parseInt(br.readLine())) != 0) {
            rooms = new char[N+1];
            nextRoom = new ArrayList[N+1];
            getMoney = new int[N+1];
            visited = new boolean[N+1];
            for(int i=1; i<=N; i++) {
                st = new StringTokenizer(br.readLine());
                rooms[i] = st.nextToken().charAt(0);
                getMoney[i] = Integer.parseInt(st.nextToken());
                nextRoom[i] = new ArrayList<>();
                int roomNum = 0;
                while((roomNum = Integer.parseInt(st.nextToken())) != 0) {
                    nextRoom[i].add(roomNum);
                }
            }
            isFinished = false;
            dfs(1, 0);
            if(isFinished) ans.append("Yes\n");
            else ans.append("No\n");
        }
        System.out.printf(ans.toString());
    }

    private static void dfs(int room, int money) {
        if(isFinished) return;
        if(room == N) {
            isFinished = true;
            return;
        }

        for(int nRoom: nextRoom[room]) {
            if(visited[nRoom]) continue;
            if(rooms[nRoom] == 'L') {
                if(getMoney[nRoom] > money) {
                    money = getMoney[nRoom];
                }
            } else if(rooms[nRoom] == 'T') {
                if(getMoney[nRoom] <= money) {
                    money -= getMoney[nRoom];
                } else {
                    return;
                }
            }
            visited[nRoom] = true;
            dfs(nRoom, money);
            visited[nRoom] = false;
        }

        return;
    }
}
