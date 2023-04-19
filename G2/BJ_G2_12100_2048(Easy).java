import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G2_12100_2048Easy {
    static int N;
    static int max = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];  
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());             
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        playGame(map, 0);
        System.out.println(max);
    }

    public static void playGame(int[][] map, int cnt) {
        if(cnt == 5) {
            int maxNum = 0;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(map[i][j] > maxNum) {
                        maxNum = map[i][j];
                    }
                }
            }
            if(max < maxNum) max =maxNum;
            return;
        }
        int[][] tmp = copyMap(map);

        moveUp(tmp);

        playGame(tmp, cnt+1);
        tmp = copyMap(map);
        moveDown(tmp);
        playGame(tmp, cnt+1);
        tmp = copyMap(map);
        moveRight(tmp);
        playGame(tmp, cnt+1);
        tmp = copyMap(map);
        moveLeft(tmp);
        playGame(tmp, cnt+1);
    }

    public static ArrayList<Integer> sumBlock(ArrayList<Integer> list) {
        ArrayList<Integer> sumBlock = new ArrayList<>();

        for(int i=0; i<list.size(); i++) {
            if(i+1 >= list.size()) {
                sumBlock.add(list.get(i));
            }
            else if(list.get(i).equals(list.get(i+1))) {
                sumBlock.add(list.get(i) * 2);
                i++;
            } else {
                sumBlock.add(list.get(i));
            }
        }
        return sumBlock;
    }

    public static void moveUp(int[][] map) {
        ArrayList<Integer> list;
        for(int j=0; j<N; j++) {
            list = new ArrayList<>();
            for(int i=0; i<N; i++) {
                if(map[i][j] !=0) {
                    list.add(map[i][j]);
                    map[i][j] = 0;
                }
            }

            if(list.size() == 0) continue;
            ArrayList<Integer> result =  sumBlock(list);
            for(int i=0; i<result.size(); i++) {
                map[i][j] = result.get(i);
            }
        }
    }

    public static void moveDown(int[][] map) {
        ArrayList<Integer> list;
        for(int j=0; j<N; j++) {
            list = new ArrayList<>();
            for(int i=N-1; i>=0; i--) {
                if(map[i][j] !=0) {
                    list.add(map[i][j]);
                    map[i][j] = 0;
                }
            }

            if(list.size() == 0) continue;
            ArrayList<Integer> result =  sumBlock(list);
            for(int i=0; i<result.size(); i++) {
                map[N-i-1][j] = result.get(i);
            }
        }
    }

    public static void moveLeft(int[][] map) {
        ArrayList<Integer> list;
        for(int i=0; i<N; i++) {
            list = new ArrayList<>();
            for(int j=0; j<N; j++) {
                if(map[i][j] !=0) {
                    list.add(map[i][j]);
                    map[i][j] = 0;
                }
            }

            if(list.size() == 0) continue;
            ArrayList<Integer> result =  sumBlock(list);
            for(int j=0; j<result.size(); j++) {
                map[i][j] = result.get(j);
            }
        }
    }

    public static void moveRight(int[][] map) {
        ArrayList<Integer> list;
        for(int i=0; i<N; i++) {
            list = new ArrayList<>();
            for(int j=N-1; j>=0; j--) {
                if(map[i][j] !=0) {
                    list.add(map[i][j]);
                    map[i][j] = 0;
                }
            }

            if(list.size() == 0) continue;
            ArrayList<Integer> result =  sumBlock(list);
            for(int j=0; j<result.size(); j++) {
                map[i][N-j-1] = result.get(j);
            }
        }
    }


    public static int[][] copyMap(int[][] map) {
        int[][] tmp = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                tmp[i][j] = map[i][j];
            }
        }
        return tmp;
    }
}
