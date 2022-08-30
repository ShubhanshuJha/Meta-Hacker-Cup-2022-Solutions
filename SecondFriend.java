// Accurate Solution that was accepted in the competition.

import java.io.*;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;

public class SecondFriend implements Runnable {
    class Pair {
        Integer r, c;
        Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
	final String[] result = {"Possible", "Impossible"};
    private final void solution() throws IOException {
        // InputReader input = new InputReader(new FileReader(System.getenv("INPUT")));
        // PrintWriter output = new PrintWriter(new BufferedOutputStream(new FileOutputStream(System.getenv("OUTPUT"))));
        InputReader input = new InputReader(in);
        PrintWriter output = new PrintWriter(new BufferedOutputStream(out));

        int test = input.nextInt();
        for (int t = 1; t <= test; t++) {
        	int R = input.nextInt(),
        		C = input.nextInt();
        	char[][] initPainting = new char[R][C];
        	for (int i = 0; i < R; i++)
        		initPainting[i] = input.nextLine().trim().toCharArray();
        	int idx;
            if (R == 1 && C == 1) {
                idx = initPainting[0][0] == '.' ? 0 : 1;
            } else {
                HashSet<Pair> hset = new HashSet<>();
                for (int i = 0; i < R; i++)
                    for (int j = 0; j < C; j++) {
                        if (initPainting[i][j] == '^')
                            hset.add(new Pair(i, j));
                    }
                if (hset.size() == 0) {
                    idx = 0;
                } else {
                    idx = 0;
                    if (hset.size() != R * C)
                        for (Pair p : hset) {
                            for (int j = 0; j < C; j++) {
                                if (initPainting[p.r][j] == '.')
                                initPainting[p.r][j] = '^';
                            }
                            if (p.r + 1 < R) {
                                for (int j = 0; j < C; j++) {
                                    if (initPainting[p.r + 1][j] == '.')
                                    initPainting[p.r + 1][j] = '^';
                                }
                            } else if (p.r - 1 >= 0) {
                                for (int j = 0; j < C; j++) {
                                    if (initPainting[p.r - 1][j] == '.')
                                    initPainting[p.r - 1][j] = '^';
                                }
                            }
                        }
                    boolean everyTreeHasAtleast2Friends = true;
                    int i = 0, j = 0;
                    while (i < R && j < C) {
                        if (initPainting[i][j] == '^') {
                            int friendCount = 0;
                            // Up check
                            if (i - 1 >= 0 && initPainting[i - 1][j] == '^')
                                friendCount++;
                            // Down check
                            if (i + 1 < R && initPainting[i + 1][j] == '^')
                                friendCount++;
                            // Left check
                            if (j - 1 >= 0 && initPainting[i][j - 1] == '^')
                                friendCount++;
                            // Right check
                            if (j + 1 < C && initPainting[i][j + 1] == '^')
                                friendCount++;
                            if (friendCount < 2) {
                                everyTreeHasAtleast2Friends = false;
                                break;
                            }
                        }
                        j++;
                        if (j == C) {
                            i++;
                            j = 0;
                        }
                    }
                    if (!everyTreeHasAtleast2Friends)
                        idx = 1;
                }
            }
            output.println("Case #" + t + ": " + result[idx]);
            if (idx == 0) {
            	for (int i = 0; i < R; i++)
            		printArr(output, initPainting[i]);
            }
        }
        output.close();
        input.close();
    }

    @Override
    public void run() {
        try {
            solution();
        } catch (IOException ignore) {}
    }
    public static void main(String... args) throws IOException {
        new Thread(null, new SecondFriend(), "Main", 1 << 26).start();
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