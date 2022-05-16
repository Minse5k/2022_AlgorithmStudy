import java.util.*;

public class Programmers_N으로표현 {
    public static void main(String[] args) {
        int N = 5;
        int number = 5;
        int answer = -1;

        List<HashSet<Integer>> list = new ArrayList<>();
        for(int i=1; i<=9; i++) {
            list.add(new HashSet<>());
        }
        list.get(1).add(N);
        for(int i=2; i<=8; i++) {
            for(int j=1; j<i; j++) {
                HashSet<Integer> l1 = list.get(j);
                HashSet<Integer> l2 = list.get(i-j);

                for(int num1: l1) {
                    for(int num2: l2) {
                        list.get(i).add(num1+num2);
                        list.get(i).add(num1*num2);
                        list.get(i).add(num1-num2);
                        if(num1 != 0 && num2 != 0) list.get(i).add(num1/num2);
                    }
                }
            }
            int num = getNum(N, i);
            list.get(i).add(num);
            if(list.get(i).contains(number)) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }
    private static int getNum(int n, int size) {
        int sum = 0;
        int len = 1;
        for (int i = 0; i < size; i++) {
            sum += n * len;
            len *= 10;
        }
        return sum;
    }
}
