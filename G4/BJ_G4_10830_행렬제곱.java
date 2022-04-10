import java.io.*;
import java.util.StringTokenizer;

public class BJ_G4_10830_행렬제곱 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());

        int n = Integer.parseInt(tokenizer.nextToken());
        long square = Long.parseLong(tokenizer.nextToken());

        int[][] matrix = new int[n][n];
        int[][] odd = new int[n][n];
        boolean isOddEmpty = true;

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        // 9  4 * 4 * 1
        while (square > 1) {
            if (square % 2 == 1) {
                if (isOddEmpty) {
                    odd = matrix;
                    isOddEmpty = false;
                } else {
                    odd = multiple(matrix, odd, n);
                }
            }

            matrix = multiple(matrix, matrix, n);
            square /= 2;
        }

        if (isOddEmpty) {
            printMatrix(matrix, n, output);
        } else {
            matrix = multiple(matrix, odd, n);
            printMatrix(matrix, n, output);
        }


        output.flush();
        output.close();
        input.close();
    }

    public static int[][] multiple(int[][] matrix_01, int[][] matrix_02, int n) {
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    long num = (result[i][j] + (matrix_01[i][k] * matrix_02[k][j]));
                    result[i][j] = (int)num%1000;
                }
            }
        }

        return result;
    }

    public static void printMatrix(int[][] matrix, int n, BufferedWriter output) throws IOException {
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                output.write(matrix[i][j] % 1000 + " ");
            }
            output.write("\n");
        }
    }
}
