package 프로그래머스;

public class PM_몸짱트레이너라이언의고민 {
    static int[][] arr;
    public static void main(String[] args) {
        System.out.println(solution(3, 2, new int[][]{{1170, 1210}, {1200, 1260}}));
    }

    public static int solution(int n, int m, int[][] timetable) {
        int answer = 0;
        int[] time = new int[1331];
        for(int i=0; i<timetable.length; i++) {
            time[timetable[i][0]]++;
            time[timetable[i][1]+1]--;
        }
        int nowPeople = 0;
        int maxPeople = 0;
        for(int i=0; i<time.length; i++) {
            nowPeople+=time[i];
            maxPeople = Math.max(maxPeople, nowPeople);
        }
        if(maxPeople <= 1) return 0;
        int[] gap = new int[30];

        for(int i=0; i<=18; i++) {
            gap[i] = setFirstLine(n, i);
            if(gap[i] < maxPeople) break;
        }
        while(gap[answer]>=maxPeople) answer++;
        return answer;
    }

    public static int setFirstLine(int n, int gap) {
        int res = 0;
        for(int j=0; j<n; j++) {
            arr = new int[110][110];
            arr[0][j] = 2;
            for(int r=0; r<n; r++) {
                for(int c=0; c<n; c++) {
                    if(isInner(r, c-j, gap)) arr[r][c] = 1;
                }
            }
            res = Math.max(res, setOtherLine(n, gap) + 1);
        }
        return res;
    }

    public static int setOtherLine(int n, int gap) {
        int res = 0;

        while(true) {
            int x = 0, y = 0;
            boolean flag = true;
            for(int j=0; flag && j<n; j++) {
                for(int i=0; flag && i<n; i++) {
                    if(arr[i][j] == 0) {
                        res++;
                        x=i;
                        y=j;
                        flag=false;
                        break;
                    }
                }
            }
            if(!flag) {
                arr[x][y] = 2;
                for(int r=0; r<n; r++) {
                    for(int c=0; c<n; c++) {
                        if(arr[r][c] == 0 && isInner(r-x, c-y, gap)) arr[r][c] = 1;
                    }
                }
            } else break;
        }
        return res;
    }

    public static boolean isInner(int x, int y, int gap) {
        return Math.abs(x) + Math.abs(y) <= gap;
    }
}
