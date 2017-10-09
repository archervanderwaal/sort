package me.stormma;

import java.util.Map;
import java.util.HashMap;

public class SortExample {

	public static void main(String[] args) {
		Integer[] a = {1, 14, 2, 13, 3, 12, 4, 11, 5, 10, 6, 9, 7, 8};
		//选择排序
		// me.stormma.SortUtils.sort(a, me.stormma.SortFactory.sortFactory.get("SelectSort"));
		// assert me.stormma.SortUtils.isSorted(a);
		// me.stormma.SortUtils.show(a);

		//插入排序
		// me.stormma.SortUtils.sort(a, me.stormma.SortFactory.sortFactory.get("InsertSort"));
		// assert me.stormma.SortUtils.isSorted(a);
		// me.stormma.SortUtils.show(a);

		//冒泡排序
		// me.stormma.SortUtils.sort(a, me.stormma.SortFactory.sortFactory.get("BubbleSort"));
		// assert me.stormma.SortUtils.isSorted(a);
		// me.stormma.SortUtils.show(a);

		//希尔排序
		// me.stormma.SortUtils.sort(a, me.stormma.SortFactory.sortFactory.get("ShellSort"));
		// assert me.stormma.SortUtils.isSorted(a);
		// me.stormma.SortUtils.show(a);

		//归并排序
		SortUtils.sort(a, SortFactory.sortFactory.get("MergeSort"));
		assert SortUtils.isSorted(a);
		SortUtils.show(a);
	}
}

interface Sort {
	void sort(Comparable[] a);
}

class SortUtils {

	public static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	public static void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
		show(a);
	}

	public static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	public static boolean isSorted(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			if (less(a[i], a[i-1])) {
				return false;
			}
		}
		return true;
	}

	public static void sort(Comparable[] a, Sort sort) {
		sort.sort(a);
	}
}

class SortFactory {
	public static Map<String, Sort> sortFactory;

	static {
		sortFactory = new HashMap<>();
		sortFactory.put("SelectSort", new SelectSort());
		sortFactory.put("BubbleSort", new BubbleSort());
		sortFactory.put("InsertSort", new InsertSort());
		sortFactory.put("ShellSort", new ShellSort());
		sortFactory.put("MergeSort", new MergeSort());
	}

	static class SelectSort implements Sort {
		@Override
		public void sort(Comparable[] a) {
			int length = a.length;
			for (int i = 0; i < length; i++) {
				int min = i;
				for (int j = i + 1; j < length; j++) {
					if (SortUtils.less(a[j], a[min])) {
						min = j;
					}
				}
				if (min != i) {
					SortUtils.exch(a, i, min);
				}
			}
		}
	}

	static class InsertSort implements Sort {
		@Override
		public void sort(Comparable[] a) {
			int length = a.length;
			for (int i = 1; i < length; i++) {
				for (int j = i; j > 0 && SortUtils.less(a[j], a[j-1]); j--) {
					SortUtils.exch(a, j, j-1);
				}
			}
		}
	}

	static class BubbleSort implements Sort {
		@Override
		public void sort(Comparable[] a) {
			int length = a.length;
			for (int i = 0; i < length - 1; i++) {
				for (int j = 0; j < length - 1 - i; j++) {
					if (!SortUtils.less(a[j], a[j+1])) {
						SortUtils.exch(a, j, j+1);
					}
				}
			}
		}
	}

	static class ShellSort implements Sort {
		@Override
		public void sort(Comparable[] a) {
			//before shell sort
			System.out.print("before shell sort==>");
			SortUtils.show(a);
			int length = a.length;
			int gap = 1; // 1, 4, 13, 40, 121...
			while (gap < length / 3) {
				gap = gap * 3 + 1;
			}
			for (; gap > 0; gap /= 3) {
				for (int i = gap; i < length; i++) {
					for (int j = i; j >= gap && SortUtils.less(a[j], a[j-gap]); j -= gap) {
						SortUtils.exch(a, j, j-gap);
					}
				}
			}
		}
	}

	static class MergeSort implements Sort {

		private static Comparable[] aux;

		@Override
		public void sort(Comparable[] a) {
			aux = new Comparable[a.length];
			sort(a, 0, a.length - 1);
		}

		private void sort(Comparable[] a, int low, int height) {
			if (height <= low) {
				return;
			}
			int mid = low + (height - low) / 2;
			sort(a, low, mid);
			sort(a, mid + 1, height);
			merge(a, low, mid, height);
		}

		private void  merge(Comparable[] a, int low, int mid, int height) {
			int i = low, j = mid + 1;
			for (int k = low; k <= height; k++) {
				aux[k] = a[k];
			}
			for (int k = low; k <= height; k++) {
				if (i > mid) {
					a[k] = aux[j++];
				} else if (j > height) {
					a[k] = aux[i++];
				} else if (SortUtils.less(aux[j], aux[i])) {
					a[k] = aux[j++];
				} else {
					a[k] = aux[i++];
				}
			}
		}
	}
}
