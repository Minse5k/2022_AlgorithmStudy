import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_G4_16120_PPAP {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stack = new Stack<>();
        String str = br.readLine();

        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == 'P') {
                stack.push('P');
            } else {
                if(stack.size() >= 2 && i!= str.length() -1 && str.charAt(i+1) == 'P') {
                    stack.pop();
                    stack.pop();
                } else {
                    System.out.println("NP");
                    return;
                }
            }
        }
        if(stack.size() == 1) {
            System.out.println("PPAP");
        } else {
            System.out.println("NP");
        }
    }
}
