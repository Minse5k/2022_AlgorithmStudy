import java.util.*;
class Programmers_메뉴리뉴얼 {
    static Map<String, Integer> stringHm = new HashMap<>();
    static char[] arr;

    public static void main(String[] args) {
        solution(new String[]{"", ""}, new int[]{1, 2});
    }
    public static void solution(String[] orders, int[] course) {
        HashSet<Character> hs = new HashSet<>();

        for(int i=0; i<orders.length; i++) {
            for(int j=0; j<orders[i].length(); j++) {
                hs.add(orders[i].charAt(j));
            }
        }

        arr = new char[hs.size()];
        Iterator iter = hs.iterator();
        int idx = 0;
        while(iter.hasNext()) {
            arr[idx++] = (char)iter.next();
        }

        for(int i=0; i<course.length; i++) {
            Combination(0, 0, course[i], new char[course[i]], orders);
        }

        Set<String> keySet = stringHm.keySet();
        ArrayList<String> list = new ArrayList<>();
        for(int i=0; i<course.length; i++) {
            int max = 0;
            for(String key : keySet) {
                if(key.length() == course[i]) {
                    if(max < stringHm.get(key)) max = stringHm.get(key);
                }
            }
            for(String key : keySet) {
                if(key.length() == course[i] && stringHm.get(key) == max) {
                    if(max == stringHm.get(key)) list.add(key);
                }
            }
        }

        Collections.sort(list);
        String[] answer = new String[list.size()];
        for(int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        System.out.println(Arrays.toString(answer));
    }

    public static void Combination(int start, int cnt, int finish, char[] ans, String[] orders) {
        if(cnt == finish) {
            String str = "";
            for(int i=0; i<finish; i++) {
                str += ans[i];
            }

            int num = isSameCnt(str, orders);
            if(num > 1) {
                stringHm.put(str, num);
            }
            return;
        }

        for(int i=start; i<arr.length; i++) {
            ans[cnt] = arr[i];
            Combination(i+1, cnt+1, finish, ans, orders);
        }
    }

    public static int isSameCnt(String str, String[] orders) {
        int cnt = 0;
        for(int i=0; i<orders.length; i++) {
            boolean isCheck = false;
            for(int j=0; j<str.length(); j++) {
                isCheck = false;
                for(int k=0; k<orders[i].length(); k++) {
                    if(orders[i].charAt(k) == str.charAt(j)) isCheck = true;
                }
                if(!isCheck) break;
            }
            if(isCheck) cnt++;
        }
        return cnt;
    }
}
