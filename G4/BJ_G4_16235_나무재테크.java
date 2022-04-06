import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_G4_16235_나무재테크 {
    static class Tree implements Comparable<Tree>{
        int x;
        int y;
        int age;
        Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age-o.age;
        }

        @Override
        public String toString() {
            return "Tree{" +
                    "x=" + x +
                    ", y=" + y +
                    ", age=" + age +
                    '}';
        }
    }
    static int N, M, K;
    static int[][] A;
    static int[][] map;
    static PriorityQueue<Tree> tree = new PriorityQueue<>();
    static Queue<Tree> dieTree = new LinkedList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N+1][N+1];
        map = new int[N+1][N+1];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = 5;
            }
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            tree.add(new Tree(x, y, age));
        }

        for(int i=0; i<K; i++) {
            Spring();
            Summer();
            Fall();
            Winter();
        }
        System.out.println(tree.size());
    }
    private static void Spring() {
        PriorityQueue<Tree> tmpTree = new PriorityQueue<>();
        //나무 돌면서 양분 확인
        while(!tree.isEmpty()) {
            Tree nowTree = tree.poll();
            int x = nowTree.x;
            int y = nowTree.y;
            int age = nowTree.age;
            //양분이 나이보다 많으면
            if(map[x][y] >= age) {
                tmpTree.add(new Tree(x, y, age+1));
                map[x][y] -= age;
            } else {
                //양분이 부족하면
                dieTree.add(new Tree(x, y, age));
            }
        }
        tree = new PriorityQueue<>(tmpTree);
    }
    private static void Summer() {
        //양분을 먹지 못한 나무들 죽이기
        while(!dieTree.isEmpty()) {
            Tree nowDieTree = dieTree.poll();
            int x = nowDieTree.x;
            int y = nowDieTree.y;
            int age = nowDieTree.age;
            map[x][y] += age/2;
        }
    }

    private static void Fall() {
        int[][] dist = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        PriorityQueue<Tree> tmpTree = new PriorityQueue<>();
        //나무 증식

        while(!tree.isEmpty()) {
            Tree nowTree = tree.poll();
            int x = nowTree.x;
            int y = nowTree.y;
            int age = nowTree.age;
            tmpTree.add(new Tree(x, y, age));
            if(age % 5 != 0) continue;
            for(int j=0; j<8; j++) {
                int nx = x + dist[j][0];
                int ny = y + dist[j][1];
                if(!isIn(nx, ny)) continue;
                tmpTree.add(new Tree(nx, ny, 1));
            }
        }

        tree = new PriorityQueue<>(tmpTree);
    }
    private static void Winter() {
        //양분 추가
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                map[i][j] += A[i][j];
            }
        }
    }
    private static boolean isIn(int x, int y) {
        return 0<x && x<=N && 0<y && y<=N;
    }
}
