package me.stormma.java;

public class Test {

    private static class SelectSort implements Sort {
        @Override
        public void sort(Comparable[] a) {
            int length = a.length;
            for (int i = 0; i < length; i++) {
                int min = i;
                for (int j = i + 1; j < length; j++) {
                    if (SortUtils.less(a[j], a[i])) {
                        min = j;
                    }
                }
                if (min != i) {
                    SortUtils.exch(a, min, i);
                }
            }
        }


        public static void main(String[] args) {
            Integer[] a = {5, 4, 3, 2, 1};
            new SelectSort().sort(a);
            SortUtils.show(a);
        }
    }

    private static class BubbleSort implements Sort {
        @Override
        public void sort(Comparable[] a) {
            int length = a.length;
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length - i - 1; j++) {
                    if (!SortUtils.less(a[j], a[j + 1])) {
                        SortUtils.exch(a, j, j + 1);
                    }
                }
            }
        }

        public static void main(String[] args) {
            Integer[] a = {5, 4, 3, 2, 1};
            new BubbleSort().sort(a);
            SortUtils.show(a);
        }
    }

    private static class InsertSort implements Sort {
        @Override
        public void sort(Comparable[] a) {
            int length = a.length;
            for (int i = 1; i < length; i++) {
                Comparable tmp = a[i];
                int j;
                for (j = i; j > 0 && SortUtils.less(tmp, a[j - 1]); j--) {
                    a[j] = a[j - 1];
                }
                a[j] = tmp;
                // 5 4 3 2 1 tmp = 4
                // 5 5 3 2 1
                // 4 5 3 2 1
            }
        }

        public static void main(String[] args) {
            Integer[] a = {5, 4, 3, 2, 1};
            new InsertSort().sort(a);
            SortUtils.show(a);
        }
    }

    private static class ShellSort implements Sort {
        @Override
        public void sort(Comparable[] a) {
            int length = a.length;
            int gap = 1;
            while (gap < length / 3) {
                gap *= 3 + 1;
            }
            for (; gap > 0; gap /= 3) {
                for (int i = gap; i < length; i++) {
                    for (int j = i; j >= gap && SortUtils.less(a[j], a[j-gap]); j -= gap) {
                        SortUtils.exch(a, j, j - gap);
                    }
                }
            }
        }

        public static void main(String[] args) {
            Integer[] a = {5, 4, 3, 2, 1};
            new ShellSort().sort(a);
            SortUtils.show(a);
        }
    }

    private static class QuickSort implements Sort {
        @Override
        public void sort(Comparable[] a) {
            int length = a.length;
            sort(a, 0, length - 1);
        }

        private void sort(Comparable[] a, int low, int high) {
            if (high <= low) {
                return;
            }
            int j = partition(a, low, high);
            sort(a, low, j - 1);
            sort(a, j + 1, high);
        }

        private int partition(Comparable[] a, int low, int high) {
            int i = low, j = high + 1;
            Comparable value = a[low];
            while (true) {
                while (SortUtils.less(a[++i], value)) {
                    if (i == high) {
                        break;
                    }
                }
                while (SortUtils.less(value, a[--j])) {
                    if (j == low) {
                        break;
                    }
                }
                if (i >= j) {
                    break;
                }
                SortUtils.exch(a, i, j);
            }
            SortUtils.exch(a, low, j);
            return j;

        }
    }

    private static class HeapSort implements Sort {
        @Override
        public void sort(Comparable[] a) {
            int N = a.length;
            for (int k = N / 2; k >= 1; k--) {
                sink(k, a);
            }
            while (N > 1) {
                SortUtils.exch(a, 1, N--);
                sink(1, a);
            }
        }

        private void swim(Comparable[] a, int k) {
            while (k > 1 && SortUtils.less(a[k / 2], a[k])) {
                SortUtils.exch(a, k / 2, k);
                k /= 2;
            }
        }

        private void sink(int k, Comparable[] a) {
            while (2 * k <= a.length) {
                int j = 2 * k;
                if (j < a.length && SortUtils.less(a[j], a[j + 1])) {
                    j++;
                }
                if (!SortUtils.less(a[k], a[j])) {
                    break;
                }
                SortUtils.exch(a, k, j);
                k = j;
            }
        }
    }
}
