import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[] suit;
    static int[] rank;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder ans = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());

        for(int t=1; t<=TC; t++) {
            st = new StringTokenizer(br.readLine());
            suit = new int[4];
            rank = new int[14];
            int num = 0;
            for(int i=0; i<5; i++) {
                String card = st.nextToken();
                setCard(card.charAt(0), card.charAt(1));
            }

            num = isOnePair(num);
            num = isTwoPair(num);
            num = isThreeOfAKind(num);
            num = isStraight(num);
            num = isFlush(num);
            num = isFullHouse(num);
            num = isFourOfAKind(num);
            num = isStraightFlush(num);

            ans.append("#" + t + " " + getAnswer(num)+"\n");
        }
        System.out.printf(ans.toString());
    }
    private static int isOnePair(int num) {
        for(int i=1; i<14; i++) {
            if(rank[i] == 2) return 8;
        }
        return num;
    }

    private static int isTwoPair(int num) {
        int cnt = 0;
        for(int i=1; i<14; i++) {
            if(rank[i] == 2) cnt++;
        }
        if(cnt==2) return 7;
        return num;
    }

    private static int isThreeOfAKind(int num) {
        for(int i=1; i<14; i++) {
            if(rank[i] == 3) return 6;
        }
        return num;
    }

    private static int isStraight(int num) {
        String str = "";
        for(int i=1; i<14; i++) {
            str+=rank[i];
        }
        if(str.contains("11111")) return 5;
        return num;
    }

    private static int isFlush(int num) {
        for(int i=0; i<4; i++) {
            if(suit[i] == 5) return 4;
        }
        return num;
    }

    private static int isFullHouse(int num) {
        String str = "";
        for(int i=1; i<14; i++) {
            str+=rank[i];
        }
        if(str.contains("3") && str.contains("2")) return 3;
        return num;
    }

    private static int isFourOfAKind(int num) {
        for(int i=1; i<14; i++) {
            if(rank[i] == 4) return 2;
        }
        return num;
    }

    private static int isStraightFlush(int num) {
        String str = "";
        for(int i=1; i<14; i++) {
            str+=rank[i];
        }
        if(str.contains("11111")) {
            for(int i=0; i<4; i++) {
                if(suit[i] == 5) return 1;
            }
        }
        return num;
    }

    private static String getAnswer(int num) {
        switch(num) {
            case 1:
                return "Straight Flush";
            case 2:
                return "Four of a Kind";
            case 3:
                return "Full House";
            case 4:
                return "Flush";
            case 5:
                return "Straight";
            case 6:
                return "Three of a kind";
            case 7:
                return "Two pair";
            case 8:
                return "One pair";
            default:
                return "High card";
        }
    }

    private static void setCard(char a, char b) {
        switch(a) {
            case'S':
                suit[0]++;
                break;
            case 'D':
                suit[1]++;
                break;
            case 'H':
                suit[2]++;
                break;
            case 'C':
                suit[3]++;
                break;
        }

        switch(b) {
            case 'A':
                rank[1]++;
                break;
            case '2':
                rank[2]++;
                break;
            case '3':
                rank[3]++;
                break;
            case '4':
                rank[4]++;
                break;
            case '5':
                rank[5]++;
                break;
            case '6':
                rank[6]++;
                break;
            case '7':
                rank[7]++;
                break;
            case '8':
                rank[8]++;
                break;
            case '9':
                rank[9]++;
                break;
            case 'T':
                rank[10]++;
                break;
            case 'J':
                rank[11]++;
                break;
            case 'Q':
                rank[12]++;
                break;
            case 'K':
                rank[13]++;
                break;
        }
    }
}
