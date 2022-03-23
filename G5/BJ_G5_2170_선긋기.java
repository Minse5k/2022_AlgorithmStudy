import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ_G5_2170_선긋기 {
    static class Point implements Comparable<Point> {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        ArrayList<Point> list = new ArrayList<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Point(x, y));
        }
        list.sort(new Comparator<Point>(){
            @Override
            public int compare(Point o1, Point o2) {
                if(o1.x == o2.x) {
                    return o1.y-o2.y;
                } else {
                    return o1.x-o2.x;
                }
            }
        });


        int s = list.get(0).x;
        int e = list.get(0).y;
        int len = e-s;
        for(int i=1; i<list.size(); i++) {
            int ns = list.get(i).x;
            int ne = list.get(i).y;
            //다음 ns, ne가 둘 다 s, e에 포함 돼 있을 시
            if(isIntersectX(ns, s, e) && isIntersectY(ne, s, e)) {
                continue;
                //x가 포함된다면
            } else if(isIntersectX(ns, s, e)) {
                len += (ne-e);
                e = ne;
            } else {
                len += ne-ns;
                s = ns;
                e = ne;
            }
        }
        System.out.println(len);
    }


    private static boolean isIntersectX(int x, int lx, int ly) {
        return (lx <= x && x <= ly);
    }

    private static boolean isIntersectY(int y, int lx, int ly) {
        return (lx <= y &&  y<= ly);
    }
}
