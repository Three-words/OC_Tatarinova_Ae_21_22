import java.util.*;

public class Main {

    public static final int REPEATS = 50;   // число повторений

    public static void main(String[] args) {

        int[] sizes = {100, 1000, 5000, 10000};
        Random rnd = new Random();

        for (int n : sizes) {
            System.out.println("\n=== Размер: " + n + " ===");

            long totalAL = 0;
            long totalLL = 0;
            long totalDQ = 0;

            // === 50 повторов ===
            for (int r = 0; r < REPEATS; r++) {

                int[] base = generateArray(n, rnd);

                // ArrayList через массив
                ArrayList<Integer> al = toArrayList(base);
                totalAL += bubbleSortArrayListViaArray(al);

                // LinkedList через массив
                LinkedList<Integer> ll = toLinkedList(base);
                totalLL += bubbleSortLinkedListViaArray(ll);

                // ArrayDeque через массив
                ArrayDeque<Integer> dq = toArrayDeque(base);
                totalDQ += bubbleSortArrayDequeViaArray(dq);
            }

            System.out.println("ArrayList среднее:   " + (totalAL / REPEATS) + " ms");
            System.out.println("LinkedList среднее:  " + (totalLL / REPEATS) + " ms");
            System.out.println("ArrayDeque среднее:  " + (totalDQ / REPEATS) + " ms");
        }
    }

    // ===== Генерация массива =====
    static int[] generateArray(int n, Random rnd) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = rnd.nextInt(1_000_000);
        return a;
    }

    // ===== Преобразования =====
    static ArrayList<Integer> toArrayList(int[] a) {
        ArrayList<Integer> list = new ArrayList<>(a.length);
        for (int v : a) list.add(v);
        return list;
    }

    static LinkedList<Integer> toLinkedList(int[] a) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int v : a) list.add(v);
        return list;
    }

    static ArrayDeque<Integer> toArrayDeque(int[] a) {
        ArrayDeque<Integer> dq = new ArrayDeque<>(a.length);
        for (int v : a) dq.add(v);
        return dq;
    }

    // ===== Пузырёк через массив (для всех коллекций) =====
    static long bubbleSortArrayListViaArray(ArrayList<Integer> list) {
        long start = System.currentTimeMillis();
        int n = list.size();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = list.get(i);

        bubbleSortArray(a);

        list.clear();
        for (int v : a) list.add(v);
        return System.currentTimeMillis() - start;
    }

    static long bubbleSortLinkedListViaArray(LinkedList<Integer> list) {
        long start = System.currentTimeMillis();
        int n = list.size();
        int[] a = new int[n];
        int i = 0;
        for (int v : list) a[i++] = v;

        bubbleSortArray(a);

        list.clear();
        for (int v : a) list.add(v);
        return System.currentTimeMillis() - start;
    }

    static long bubbleSortArrayDequeViaArray(ArrayDeque<Integer> dq) {
        long start = System.currentTimeMillis();
        int n = dq.size();
        int[] a = new int[n];
        int i = 0;
        for (int v : dq) a[i++] = v;

        bubbleSortArray(a);

        dq.clear();
        for (int v : a) dq.add(v);
        return System.currentTimeMillis() - start;
    }

    // ===== Пузырёк для массива =====
    static void bubbleSortArray(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                }
            }
        }
    }
}
