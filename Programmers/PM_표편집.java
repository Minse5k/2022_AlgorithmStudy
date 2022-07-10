package 프로그래머스;

import java.util.Stack;

public class PM_표편집 {
    public static void main(String[] args) {
        System.out.println(solution(8, 2, new String[]{"D 16", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"}));
    }

    public static String solution(int n, int k, String[] cmd) {
        StringBuilder ans = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        for(String str: cmd) {
            int s = 0;
            switch(str.charAt(0)) {
                case 'U':
                    s = Integer.parseInt(str.split(" ")[1]);
                    k=(k-s)%n;
                    break;
                case 'D':
                    s = Integer.parseInt(str.split(" ")[1]);
                    k = (k+s)%n;
                    break;
                case 'C':
                    stack.push(k);
                    n--;
                    if(k==n) k--;
                    break;
                case 'Z':
                    if(k >= stack.pop()) k++;
                    n++;
                    break;
            }
        }

        for(int i=0; i<n; i++){
            ans.append("O");
        }
        while(!stack.isEmpty()) {
            ans.insert(stack.pop(), "X");
        }

        return ans.toString();
    }
}
