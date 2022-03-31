import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class BJ_G4_1339_단어수학 {
    static int N;
    static String[] str;
    static HashMap<Character, String> hm = new HashMap<>();
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        str = new String[N];
        for(int i=0; i<N; i++) {
            str[i] = br.readLine();
            for(int j=0; j<str[i].length(); j++) {
                hm.put(str[i].charAt(j), "");
            }
        }
        int charSize = hm.size();
        Permutation(0, charSize, new int[charSize], new boolean[charSize]);
        System.out.println(max);
    }

    private static void Permutation(int cnt, int finish, int[] arr, boolean[] visited) {
        if(cnt == finish) {
            HashMap<Character, Integer> combHm = new HashMap<>();
            int i=0;
            for(Character c : hm.keySet()) {
                combHm.put(c, arr[i++]);
            }
            max = Math.max(max, result(combHm));
            return;
        }
        for(int i=0; i<finish; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            arr[cnt] = 9-i;
            Permutation(cnt+1, finish, arr, visited);
            visited[i] = false;
        }
    }
    private static int result(HashMap<Character, Integer> map) {
        int totalSum = 0;
        for(int i=0; i<str.length; i++) {
            int sum = map.get(str[i].charAt(0));
            for(int j=1; j<str[i].length(); j++) {
                sum = sum*10 + map.get(str[i].charAt(j));
            }
            totalSum+=sum;
        }
        return totalSum;
    }
}
