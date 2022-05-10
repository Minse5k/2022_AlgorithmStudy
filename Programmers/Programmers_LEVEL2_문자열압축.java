public class Programmers_LEVEL2_문자열압축 {
    public static void main(String[] args) throws Exception {
        System.out.println(solution("abcabcdede"));
    }
    public static int solution(String s) {
        int answer = s.length();
        int n = s.length()/2;
        for(int i=n; i>=1; i--) {
            answer = Math.min(getComperssWord(s, i), answer);
        }

        return answer;
    }

    public static int getComperssWord(String s, int chunkSize){
        String result = "";
        int i=0;
        int chunkCnt = 1;
        int strSize = s.length();
        for(i=0; i<s.length()-chunkSize; i+=chunkSize*chunkCnt, chunkCnt=1) {
            String str = s.substring(i, i+chunkSize);

            int j=i+chunkSize;
            while(j+chunkSize <= strSize) {
                String compStr = s.substring(j, j+chunkSize);
                if(!str.equals(compStr)) break;
                chunkCnt++;
                j+=chunkSize;
            }
            if(chunkCnt > 1) {
                result += chunkCnt;
            }
            result+=str;
        }
        if(i < s.length()) result+=s.substring(i, strSize);
        return result.length();
    }
}
