package me.stormma.java;

import java.util.Map;
import java.util.HashMap;

public class SortExample {

	public static void main(String[] args) {
//		Integer[] a = {1, 14, 2, 13, 3, 12, 4, 11, 5, 10, 6, 9, 7, 8};
		Integer[] a = {5, 1, 2, 4, 3, 6, 7, 9, 8};
		//Select Sort
		// me.stormma.java.SortUtils.sort(a, me.stormma.java.SortFactory.sortFactory.get("SelectSort"));
		// assert me.stormma.java.SortUtils.isSorted(a);
		// me.stormma.java.SortUtils.show(a);

		//Insert Sort
		// me.stormma.java.SortUtils.sort(a, me.stormma.java.SortFactory.sortFactory.get("InsertSort"));
		// assert me.stormma.java.SortUtils.isSorted(a);
		// me.stormma.java.SortUtils.show(a);

		//Bubble Sort
		// me.stormma.java.SortUtils.sort(a, me.stormma.java.SortFactory.sortFactory.get("BubbleSort"));
		// assert me.stormma.java.SortUtils.isSorted(a);
		// me.stormma.java.SortUtils.show(a);

		//Shell Sort
		// me.stormma.java.SortUtils.sort(a, me.stormma.java.SortFactory.sortFactory.get("ShellSort"));
		// assert me.stormma.java.SortUtils.isSorted(a);
		// me.stormma.java.SortUtils.show(a);

		//Top-Down Merge Sort
		//SortUtils.sort(a, SortFactory.sortFactory.get("MergeSortTD"));
		//assert SortUtils.isSorted(a);
		//SortUtils.show(a);

		//Bottom-Top Merge Sort
		//SortUtils.sort(a, SortFactory.sortFactory.get("MergeSortBU"));
		//assert SortUtils.isSorted(a);
		//SortUtils.show(a);

		SortUtils.sort(a, SortFactory.sortFactory.get("QuickSort"));
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
		sortFactory.put("MergeSortTD", new MergeSortTD());
		sortFactory.put("MergeSortBU", new MergeSortBU());
		sortFactory.put("QuickSort", new QuickSort());
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

	static class MergeSortTD implements Sort {

		private static Comparable[] aux;

		@Override
		public void sort(Comparable[] a) {
			aux = new Comparable[a.length];
			sort(a, 0, a.length - 1);
		}

		private void sort(Comparable[] a, int low, int high) {
			if (high <= low) {
				return;
			}
			int mid = low + (high - low) / 2;
			sort(a, low, mid);
			sort(a, mid + 1, high);
			merge(a, low, mid, high);
		}

		private static void  merge(Comparable[] a, int low, int mid, int high) {
			int i = low, j = mid + 1;
			for (int k = low; k <= high; k++) {
				aux[k] = a[k];
			}
			for (int k = low; k <= high; k++) {
				if (i <= mid && j <= high) {
					a[k] = SortUtils.less(aux[i], aux[j]) ? aux[i++] : aux[j++];
				} else if (i > mid) {
					a[k] = aux[j++];
				} else if (j > high) {
					a[k] = aux[i++];
				}
			}
		}
	}

	static class MergeSortBU implements Sort {
		private static Comparable[] aux;
		@Override
		public void sort(Comparable[] a) {
			aux = new Comparable[a.length];
			int length = a.length;
			for (int sz = 1; sz < length; sz += sz) {
				for (int lo = 0; lo < length - sz; lo += 2 * sz) {
					System.out.println(sz);
					merge(a, lo, lo + sz - 1, Math.min(lo + 2 * sz - 1, length - 1) );
				}
			}
		}

		private static void  merge(Comparable[] a, int low, int mid, int high) {
			System.out.println("Starting => merge(a, " + low + ", " + mid + ", " + high + ")");
			int i = low, j = mid + 1;
			for (int k = low; k <= high; k++) {
				aux[k] = a[k];
			}
			for (int k = low; k <= high; k++) {
				if (i <= mid && j <= high) {
					a[k] = SortUtils.less(aux[i], aux[j]) ? aux[i++] : aux[j++];
				} else if (i > mid) {
					a[k] = aux[j++];
				} else if (j > high) {
					a[k] = aux[i++];
				}
			}
			System.out.println("Merge result: ");
			SortUtils.show(a);
		}
	}

	static class QuickSort implements Sort {

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
}
