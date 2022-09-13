// Was wrong, but I've corrected it
import java.io.*;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;

public class Consecutive_Cuts_Chapter1 implements Runnable {
    final String[] result = {"YES", "NO"};
    private final void solution() throws IOException {
        // InputReader input = new InputReader(new FileReader("D:\\Competitive Programming\\consecutive_cuts_chapter_1_input.txt"));
        // InputReader input = new InputReader(new FileReader(System.getenv("INPUT")));
        // PrintWriter output = new PrintWriter(new BufferedOutputStream(new FileOutputStream(System.getenv("OUTPUT"))));
        InputReader input = new InputReader(in);
        PrintWriter output = new PrintWriter(new BufferedOutputStream(out));

        short test = input.nextShort();
        for (int t = 1; t <= test; t++) {
            int N = input.nextInt(),
                K = input.nextInt();
            int[] arrA = input.readIntArray();
            int[] arrB = input.readIntArray();
            int idx = 0;
            boolean isEqual = Arrays.equals(arrA, arrB);
            if (K == 0) {
                idx = isEqual ? 0 : 1;
            } else if (K == 1 && isEqual) {
                idx = 1; // We can't put the whole array to back again
            } else if (isEqual) {
                idx = N == 2 && (K & 1) == 1 ? 1 : 0;
            } else {
                int selIdx = 0;
                for (int i = 0; i < N; i++)
                    if (arrA[i] == arrB[0]) {
                        selIdx = i;
                        break;
                    }
                reverseArr(arrA, 0, selIdx - 1);
                reverseArr(arrA, selIdx, N - 1);
                reverseArr(arrA, 0, N - 1);
                if (!Arrays.equals(arrA, arrB)) {
                    idx = 1;
                } else {
                    if (K == 1 || (N & 1) == 1) {
                        idx = 0;
                    } else if (N == 2) {
                        idx = K > 2 && (K & 1) == 1 ? 0 : 1;
                    } else {
                        idx = 0;
                    }
                }
            }
            output.println("Case #" + t + ": " + result[idx]);
        }
        output.close();
        input.close();
    }
    private void reverseArr(int[] arr, int l, int h) {
        while (l < h) {
            int temp = arr[l];
            arr[l] = arr[h];
            arr[h] = temp;
            l++; h--;
        }
    }

    @Override
    public void run() {
        try {
            solution();
        } catch (IOException ignore) {}
    }
    public static void main(String... args) throws IOException {
        new Thread(null, new Consecutive_Cuts_Chapter1(), "Main", 1 << 26).start();
    }

    private final void printArr(PrintWriter output, int...arr) {
        output.println(Arrays.toString(arr).replaceAll("\\[|]|, ", " ").trim());
    }
    private final void printArr(PrintWriter output, double...arr) {
        output.println(Arrays.toString(arr).replaceAll("\\[|]|, ", " ").trim());
    }
    private final void printArr(PrintWriter output, long...arr) {
        output.println(Arrays.toString(arr).replaceAll("\\[|]|, ", " ").trim());
    }
    private final void printArr(PrintWriter output, char...arr) {
        output.println(Arrays.toString(arr).replaceAll("\\[|]|, ", "").trim());
    }
    private final void printArr(PrintWriter output, String...arr) {
        output.println(Arrays.toString(arr).replaceAll("\\[|]|, ", " ").trim());
    }
    private final boolean isPowerofTwo(long n){
        if (n <= 0) return false;
        int bit = (int) (Math.log(n) / Math.log(2));
        
        long expec = 1 << bit;
        return n % expec == 0;
    }
}

class InputReader {
    private StringTokenizer st;
    private BufferedReader br;

    public InputReader(InputStream s) {
        br = new BufferedReader(new InputStreamReader(s));
    }
    public InputReader(FileReader s) throws FileNotFoundException {
        br = new BufferedReader(s);
    }

    public final String next() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }
    public final String nextLine() throws IOException {
        return br.readLine();
    }
    public final byte nextByte() throws IOException {
        return Byte.parseByte(next());
    }
    public final short nextShort() throws IOException {
        return Short.parseShort(next());
    }
    public final int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
    public final long nextLong() throws IOException {
        return Long.parseLong(next());
    }
    public final double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }
    public final char nextChar() throws IOException {
        return next().charAt(0);
    }
    public final boolean nextBoolean() throws IOException {
        return Boolean.parseBoolean(next());
        // return Boolean.getBoolean(next());
        // return Boolean.valueOf(next());
    }

    public int[] readIntArray(int n) throws IOException {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = nextInt();
        return arr;
    }
    public int[] readIntArray() throws IOException {
        return java.util.Arrays.stream(nextLine().split("\\s+")).
                mapToInt(Integer::parseInt).toArray();
    }
    public long[] readLongArray(int n) throws IOException {
        long[] arr = new long[n];
        for (int i = 0; i < n; i++)
            arr[i] = nextLong();
        return arr;
    }
    public long[] readLongArray() throws IOException {
        return java.util.Arrays.stream(nextLine().split("\\s+")).
                mapToLong(Long::parseLong).toArray();
    }
    public double[] readDoubleArray(int n) throws IOException {
        double[] arr = new double[n];
        for (int i = 0; i < n; i++)
            arr[i] = nextDouble();
        return arr;
    }
    public double[] readDoubleArray() throws IOException {
        return java.util.Arrays.stream(nextLine().split("\\s+")).
                mapToDouble(Double::parseDouble).toArray();
    }
    public String[] readStringArray(int n) throws IOException {
        String[] arr = new String[n];
        for (int i = 0; i < n; i++)
            arr[i] = next();
        return arr;
    }
    public String[] readStringArray() throws IOException {
        return nextLine().split("\\s+");
    }
    public boolean ready() throws IOException {
        return br.ready();
    }
    public void close() throws IOException {
        br.close();
    }
}