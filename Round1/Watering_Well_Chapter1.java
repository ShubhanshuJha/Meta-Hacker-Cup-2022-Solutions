// Accepted
import java.io.*;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;

public class Watering_Well_Chapter1 implements Runnable {
	class Pair {
		Long x, y;
		Pair(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}
	final long MOD = (long)1e9 + 7;

    private final void solution() throws IOException {
        InputReader input = new InputReader(new FileReader(System.getenv("INPUT")));
        PrintWriter output = new PrintWriter(new BufferedOutputStream(new FileOutputStream(System.getenv("OUTPUT"))));
        // InputReader input = new InputReader(in);
        // PrintWriter output = new PrintWriter(new BufferedOutputStream(out));

        byte test = input.nextByte();
        for (byte t = 1; t <= test; t++) {
        	int N = input.nextInt();
        	Pair[] trees = new Pair[N];
        	for (int i = 0; i < N; i++)
        		trees[i] = new Pair(input.nextLong(), input.nextLong());
        	int Q = input.nextInt();
        	Pair[] wells = new Pair[Q];
        	for (int i = 0; i < Q; i++) {
        		wells[i] = new Pair(input.nextLong(), input.nextLong());
        	}
            long beta = 0l, delta = 0l, gamma = 0l;
            for (Pair p : trees) {
                long xSqr = (long)Math.pow(p.x, 2) % MOD,
                     ySqr = (long)Math.pow(p.y, 2) % MOD;

                beta = (beta % MOD + ((xSqr + ySqr) % MOD)) % MOD;
                gamma = (gamma % MOD + p.x % MOD) % MOD;
                delta = (delta % MOD + p.y % MOD) % MOD;
            }
            beta %= MOD; gamma %= MOD; delta %= MOD;

            long[] inconv = new long[Q];
            int i = 0;
            for (Pair p : wells) {
                long xSqr = (long)Math.pow(p.x, 2) % MOD,
                     ySqr = (long)Math.pow(p.y, 2) % MOD;

                long alpha = (N * ((xSqr + ySqr) % MOD)) % MOD;
                alpha = (alpha % MOD + beta % MOD) % MOD;

                long gamma2X = (((gamma * p.x) % MOD) * 2L) % MOD;
                long delta2Y = (((delta * p.y) % MOD) * 2L) % MOD;
                long deltaGamma2X2Y = (gamma2X + delta2Y) % MOD;

                inconv[i++] = (alpha - (deltaGamma2X2Y )) % MOD;
            }
            long totalInconvenience = 0l;
            for (long val : inconv) {
                totalInconvenience = (totalInconvenience % MOD + val % MOD) % MOD;
            }
            if (totalInconvenience < 0) {
                totalInconvenience = 0;
                for (Pair w : wells) {
                    long curr = 0l;
                    for (Pair tr : trees) {
                        long x = (long) Math.pow(w.x - tr.x, 2) % MOD,
                             y = (long) Math.pow(w.y - tr.y, 2) % MOD;
                        curr = (curr % MOD + (x + y) % MOD) % MOD;
                    }
                    totalInconvenience = (totalInconvenience % MOD + curr % MOD) % MOD;
                }
            }
            output.println("Case #" + t + ": " + totalInconvenience);
        }
        output.close();
        input.close();
    }

    @Override
    public void run() {
        try {
            solution();
        } catch (IOException ignored) {}
    }
    public static void main(String[] args) throws IOException {
        new Thread(null, new Watering_Well_Chapter1(), "Main", 1 << 26).start();
    }

    private final void printArr(PrintWriter output, short...arr) {
        output.println(Arrays.toString(arr).replaceAll("\\[|]|, ", " ").trim());
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
        output.println(Arrays.toString(arr).replaceAll("\\[|]|, ", " ").trim());
    }
    private final void printArr(PrintWriter output, String...arr) {
        output.println(Arrays.toString(arr).replaceAll("\\[|]|, ", " ").trim());
    }
}

class InputReader {
    private StringTokenizer token;
    private BufferedReader buffer;

    public InputReader(InputStream stream) {
        buffer = new BufferedReader(new InputStreamReader(stream));
    }
    public InputReader(FileReader reader) throws FileNotFoundException {
        buffer = new BufferedReader(reader);
    }

    public final String next() throws IOException {
        while (token == null || !token.hasMoreTokens())
            token = new StringTokenizer(buffer.readLine());
        return token.nextToken();
    }
    public final String nextLine() throws IOException {
        return buffer.readLine();
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
    public char[] readCharArray() throws IOException {
        return nextLine().toCharArray();
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
        return buffer.ready();
    }
    public void close() throws IOException {
        buffer.close();
    }
}