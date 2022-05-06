import java.util.*;
public class Programmers_양과늑대 {
    static class Point{
        int node;
        int sheepCnt;
        int wolfCnt;
        ArrayList<Integer> list;

        public Point(int node, int sheepCnt, int wolfCnt, ArrayList<Integer> list) {
            this.node = node;
            this.sheepCnt = sheepCnt;
            this.wolfCnt = wolfCnt;
            this.list = list;
        }
    }
    public static void main(String[] args) throws Exception {
        int[] info = {0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0};
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}};
        System.out.println(solution(info, edges));
    }
    public static int solution(int[] info, int[][] edges) {
        ArrayList<Integer>[] list = new ArrayList[info.length];
        for(int i=0; i<info.length; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            list[u].add(v);
        }

        int answer = bfs(list, info);

        return answer;
    }

    public static int bfs(ArrayList<Integer>[] list, int[] info) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 1, 0, new ArrayList<>(list[0])));
        int max = 0;
        while(!q.isEmpty()) {
            Point nowNode = q.poll();
            if(max < nowNode.sheepCnt) {
                max = nowNode.sheepCnt;
            }
            for(int i=0; i<nowNode.list.size(); i++) {
                int nextNode = nowNode.list.get(i);
                ArrayList<Integer> next = new ArrayList<>();
                for(int j=0; j<list[nextNode].size(); j++) {
                    next.add(list[nextNode].get(j));
                }
                for(int j=0; j<nowNode.list.size(); j++) {
                    if(nowNode.list.get(j) == nowNode.node || nowNode.list.get(j) == nextNode) continue;
                    next.add(nowNode.list.get(j));
                }
                if(info[nextNode] == 0) {
                    q.add(new Point(nextNode, nowNode.sheepCnt+1, nowNode.wolfCnt, next));
                } else {
                    if(nowNode.sheepCnt <= nowNode.wolfCnt + 1) {
                        continue;
                    }
                    q.add(new Point(nextNode, nowNode.sheepCnt, nowNode.wolfCnt+1, next));
                }
            }
        }
        return max;
    }
}
