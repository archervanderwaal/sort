# Sort
> 生命不息，奋斗不止！

----

> 常用的排序算法分析与实现，全部代码库地址[sort](https://github.com/StormMaybin/sort)，以及[algorithms4](https://github.com/StormMaybin/algorithms4)

<!-- more -->

排序算法是算法的入门知识，其经典思想可以用于很多算法当中。因为其实现代码较短，应用较常见。所以在面试中经常会问到排序算法及其相关的问题。但万变不离其宗，只要熟悉了思想，灵活运用也不是难事。一般在面试中最常考的是快速排序和归并排序，并且经常有面试官要求现场写出这两种排序的代码。对这两种排序的代码一定要信手拈来才行。还有插入排序、冒泡排序、堆排序、基数排序、桶排序等。面试官对于这些排序可能会要求比较各自的优劣、各种算法的思想及其使用场景。还有要会分析算法的时间和空间复杂度。通常查找和排序算法的考察是面试的开始，如果这些问题回答不好，估计面试官都没有继续面试下去的兴趣都没了。所以想开个好头就要把常见的排序算法思想及其特点要熟练掌握，有必要时要熟练写出代码。

## 冒泡排序
冒泡排序是最简单的排序之一了，其大体思想就是通过与相邻元素的比较和交换来把小的数交换到最前面。这个过程类似于水泡向上升一样，因此而得名。举个例子，对5, 3, 8, 6, 4这个无序序列进行冒泡排序。首先从前向后冒泡，5和3比较，把5交换到后面，序列变成3, 5, 8, 4, 6。同理5和8比较，依然是3, 5, 8, 4, 6。8和4交换，变成3, 5, 4, 8, 6。8和6交换，变成3, 5, 4, 6, 8这样一次冒泡就完了，把最大的数8排到最后面了。对剩下的序列依次冒泡就会得到一个有序序列。冒泡排序的时间复杂度为O(n^2)。当然也可以从后到前冒泡，依次冒泡得到最小的数排到最前面。

`BubbleSort.java`

```java
/**
 * This {@code BubbleSort} class is implementation of bubble sort.
 *
 * @author stormma
 * @date 2017/10/19
 */
public class BubbleSort implements Sort {
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
```

`BubbleSort.c`

```c
void bubble_sort(int* array) {
	for (int i = 0; i < SIZE - 1; i++) {
        for (int j = 0; j < SIZE - 1 - i; j++) {
            if (array[j] > array[j+1]) {
                int temp = array[j];
                array[j] = array[j+1];
                array[j+1] = temp;
            }
        }
    }
}
```

## 选择排序

选择排序的思想其实和冒泡排序有点类似，但是过程不同，冒泡排序是通过相邻的比较和交换。而选择排序是通过对整体的选择。举个栗子，对5, 3, 8, 6, 4这个无序序列进行简单选择排序，首先要选择5以外的最小数来和5交换，也就是选择3和5交换，一次排序后就变成了3, 5, 8, 6, 4.对剩下的序列一次进行选择和交换，最终就会得到一个有序序列。其实选择排序可以看成冒泡排序的优化，因为其目的相同，只是选择排序只有在确定了最小数的前提下才进行交换，大大减少了交换的次数。选择排序的时间复杂度为O(n^2)

`SelectSort.java`

```java
/**
 * This {@code SelectSort} class is implementation of select sort.
 *
 * @author stormma
 * @date 2017/10/19
 */
public class SelectSort implements Sort {
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
```

`SelectSort.c`

```c
void select_sort(int* array) {
	for (int i = 0; i < SIZE - 1; i++) {
		int min = i;
		for (int j = i + 1; j < SIZE; j++) {
			if (array[j] < array[min]) {
				min = j;
			}
		}
		if (min != i) {
			int temp = array[min];
			array[min] = array[i];
			array[i] = temp;
		}
	}
}
```

## 插入排序
插入排序不是通过交换位置而是通过比较找到合适的位置插入元素来达到排序的目的的。相信大家都有过打扑克牌的经历，特别是牌数较大的。在分牌时可能要整理自己的牌，牌多的时候怎么整理呢？就是拿到一张牌，找到一个合适的位置插入。这个原理其实和插入排序是一样的。举个例子，对5, 3, 8, 6, 4这个无序序列进行简单插入排序，首先假设第一个数的位置是正确的，想一下在拿到第一张牌的时候，没必要整理。然后3要插到5前面，把5后移一位，变成3, 5, 8, 6, 4.想一下整理牌的时候应该也是这样吧。然后8不用动，6插在8前面，8后移一位，4插在5前面，从5开始都向后移一位。注意在插入一个数的时候要保证这个数前面的数已经有序。简单插入排序的时间复杂度也是O(n^2)。

`InsertSort.java`

```java
/**
 * This {@code InsertSort} class is implementation of insert sort.
 *
 * @author stormma
 * @date 2017/10/19
 */
public class InsertSort implements Sort {
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
```

`InsertSort.c`

```c
void insert_sort(int* array) {
	for (int i = 1; i < SIZE; i++) {
		for (int j = i; j > 0; j--) {
			if (array[j] < array[j-1]) {
				int temp = array[j];
				array[j] = array[j-1];
				array[j-1] = temp;
			}
		}
	}
}
```

## 希尔排序
希尔排序是插入排序的优化，试想一下，还是我们打牌的整牌，插入的时候是一步一步的向前找到正确的位置?，不，是个正常的人都不会这么做，都是一次找到正确的位置插入的。但是对应的算法中，我们没法一次找到合适的位置，但是我们可以将数组的以不同的间隔gap分组，先在组内进行插入排序，然后缩小间隔gap直至到1，最后就变成了近乎有序的插入排序了。希尔排序基于插入排序的以下两点性质而提出改进方法的：

- 插入排序在对几乎已经排好序的数据操作时， 效率高， 即可以达到线性排序的效率
- 但插入排序一般来说是低效的， 因为插入排序每次只能将数据移动一位

算法思路:

1. 先取一个正整数 d1(d1 < n)，把全部记录分成 d1 个组，所有距离为 d1 的倍数的记录看成一组，然后在各组内进行插入排序
2. 然后取 d2(d2 < d1)
3. 重复上述分组和排序操作；直到取 di = 1(i >= 1) 位置，即所有记录成为一个组，最后对这个组进行插入排序。间隔的选择方式有很多种，而且每一种都是对应的算法复杂度也不一样。这样我们以1, 4, 13, 40, 121...这种间隔做示范。希尔排序的平均时间复杂度是O(nlogn)，怎么算法

![](常用排序方式总结/shell-sort-animation.gif)

举个例子:

![](https://camo.githubusercontent.com/bad891f3cd2c6aa3781eefeeeb6a3b86b40d1423/687474703a2f2f7374617469632e636f646563656f2e636f6d2f696d616765732f323031362f30332f61656630336230643233333962653334363237626364383161333830396536322e706e67)

`ShellSort.java`

```java
/**
 * This {@code ShellSort} class is implementation of shell sort.
 *
 * @author stormma
 * @date 2017/10/19
 */
public class ShellSort implements Sort {
    @Override
    public void sort(Comparable[] a) {
        //before shell sort
        System.out.print("before shell sort==>");
        SortUtils.show(a);
        int length = a.length;
        // 1, 4, 13, 40, 121...
        int gap = 1;
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
```

`ShellSort.c`

```c
void shell_sort(int* array, int len) {
	int gap = 1;
	while (gap < len / 3) {
		gap = gap * 3 + 1;
	}
	for (; gap > 0; gap /= 3) {
		for (int i = gap; i < len; i++) {
			for (int j = i; j >= gap; j -= gap) {
				if (array[j] < array[j-gap]) {
					int temp = array[j];
                	array[j] = array[j-gap];
                	array[j-gap] = temp;
				}
			}
		}
	}
}
```

## 归并排序(Top-Down)
归并排序的归并操作（merge），也叫归并算法，指的是将两个已经排序的序列合并成一个序列的操作。归并排序算法依赖归并操作。归并排序其基本思想是，先递归划分子问题，然后合并结果。把待排序列看成由两个有序的子序列，然后合并两个子序列，然后把子序列看成由两个有序序列。倒着来看，其实就是先两两合并，然后四四合并。。。最终形成有序序列。空间复杂度为O(n)，时间复杂度为O(nlogn)。归并排序过程:

图一:

![](https://upload.wikimedia.org/wikipedia/commons/c/cc/Merge-sort-example-300px.gif) [图片来自维基百科]

图二：

![](常用排序方式总结/merge_sort.png)

对应Top-Down MargeSort来说，最重要的环节就是归并操作，将两个有序的子数组归并成一个有序的数组。原地归并操作，需要引进一个辅助数组`aux`。具体代码如下：

`MergeSortTD.java`

```java
/**
 * This {@code MergeSortTD} is implementation of merge sort top-down
 *
 * @author stormma
 * @date 2017/10/19
 */
public class MergeSortTD implements Sort {

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
        int mid = low + ((high - low) >> 1);
        sort(a, low, mid);
        sort(a, mid + 1, high);
        merge(a, low, mid, high);
    }

    private static void  merge(Comparable[] a, int low, int mid, int high) {
        int i = low, j = mid + 1;
        System.arraycopy(a, low, aux, low, high + 1 - low);
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
```
`MergeSort.c`

```c
static int aux[N] = {0};
void merge(int* array, int low, int mid, int high) {
	int start1 = low, start2 = mid + 1;
	for (int i = start1; i <= high; i++) {
		aux[i] = array[i];
	}
	for (int k = low; k <= high; k++) {
		if (start1 <= mid && start2 <= high) {
			array[k] = aux[start1] < aux[start2] ? aux[start1++] : aux[start2++];
		} else if (start1 > mid) {
			array[k] = aux[start2++];
		} else if (start2 > high) {
			array[k] = aux[start1++];
		}
	}
}

void _merge_sort(int* array, int low, int high) {
	if (high <= low) {
		return;
	}
	int mid = (low + high) >> 1;
	_merge_sort(array, low, mid);
	_merge_sort(array, mid + 1, high);
	merge(array, low, mid, high);
}

void merge_sort(int* array, int length) {
	_merge_sort(array, 0, length - 1);
}
```

## 归并排序(Bottom-Up)
同理自顶而下mergesort

`MergeSortBU.java`

```java
/**
 * This {@code MergeSortBU} class is implementation of merge sort bottom-up
 *
 * @author stormma
 * @date 2017/10/19
 */
public class MergeSortBU implements Sort {
    private static Comparable[] aux;
    @Override
    public void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        int length = a.length;
        int step = 2;
        for (int sz = 1; sz < length; sz += sz) {
            for (int lo = 0; lo < length - sz; lo += step * sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + step * sz - 1, length - 1) );
            }
        }
    }

    private static void  merge(Comparable[] a, int low, int mid, int high) {
        System.out.println("Starting => merge(a, " + low + ", " + mid + ", " + high + ")");
        int start1 = low, start2 = mid + 1;
        System.arraycopy(a, low, aux, low, high + 1 - low);
        for (int k = low; k <= high; k++) {
            if (start1 <= mid && start2 <= high) {
                a[k] = SortUtils.less(aux[start1], aux[start2]) ? aux[start1++] : aux[start2++];
            } else if (start1 > mid) {
                a[k] = aux[start2++];
            } else if (start2 > high) {
                a[k] = aux[start1++];
            }
        }
        System.out.println("Merge result: ");
        SortUtils.show(a);
    }
}
```

## 快速排序
通过一趟扫描将要排序的数据分割成独立的两部分,其中一部分的所有数据都比另外一部分的所有数据都要小,然后再按此方法对这两部分数据分别进行快速排序,整个排序过程可以递归进行,以此达到整个数据变成有序序列。和归并排序有点类似，原理也很简单。
步骤如下

1. 对数组切分，用数组的第一个元素切分，比第一个元素大的放在它后边，小的放在前面。此时这个切分元素已经在正确位置上了。
2. 对前部分[0, pos]和[pos+1, length-1]重复步骤1。直至有序。

`QuickSort.java`

```java
/**
 * This {@code QuickSort} class is implementation of quick sort.
 *
 * @author stormma
 * @date 2017/10/19
 */
public class QuickSort implements Sort {

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
```

`QuickSort.c`


```c

int partition(int* array, int low, int high) {
    int i = low, j = high + 1;
    int value = array[low];
    while (1) {
        while (array[++i] < value) {
            if (i == high)
                break;
        }
        while (value < array[--j]) {
            if (j == low)
                break;
        }
        if (i >= j)
            break;
        exch(array, i, j);
    }
    exch(array, low, j);
    return j;
}

void _quick_sort(int* array, int low, int high) {
    if (high <= low) {
        return;
    }
    int j = partition(array, low, high);
    _quick_sort(array, low, j - 1);
    _quick_sort(array, j + 1, high);
}

void quick_sort(int* array, int length) {
    _quick_sort(array, 0, length - 1);
}
```

## 三向切分快速排序
对比快速排序，有了下面几点改变: 对于每次切分：从数组的左边到右边遍历一次，维护三个指针lt,gthe i，其中

1. lt指针使得元素（arr[0]-arr[lt-1]）的值均小于切分元素；
2. gt指针使得元素（arr[gt+1]-arr[N-1]）的值均大于切分元素；
3. i指针使得元素（arr[lt]-arr[i-1]）的值均等于切分元素，（arr[i]-arr[gt]）的元素还没被扫描，切分算法执行到i>gt为止。


`QuickSort3Way.java`

```java
/**
 * This {@code QuickSort3Way} is implementation of quick sort three-way.
 *
 * @author stormma
 * @date 2017/10/19
 */
public class QuickSort3Way implements Sort {
    @Override
    public void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private void sort(Comparable[] a, int low, int high) {
        if (high <= low) {
            return;
        }
        int lt = low, i = low + 1, gt = high;
        Comparable v = a[low];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                SortUtils.exch(a, lt++, i++);
            } else if (cmp > 0) {
                SortUtils.exch(a, i, gt--);
            } else {
                i++;
            }
        }
        sort(a, low, lt - 1);
        sort(a, gt + 1, high);
    }
}

```

`QuickSort3Way.c`

```java
void _quick_sort_3way(int* array, int low, int high) {
    if (high <= low) {
        return;
    }
    int lt = low, i = low + 1, gt = high;
    int value = array[low];
    while (i <= gt) {
        int cmp = array[i] - value;
        if (cmp < 0)
            exch(array, lt++, i++);
        else if (cmp > 0)
            exch(array, i, gt--);
        else
            i++;
    }
    _quick_sort_3way(array, low, lt - 1);
    _quick_sort_3way(array, gt + 1, high);
}

void quick_sort_3way(int * array, int length) {
    _quick_sort(array, 0, length - 1);
}
```

## 堆排序
其原理就是构造最大堆，然后进行下沉排序，原理也很简单，一点都不复杂。

`HeapSort.java`

```java
/**
 * This {@code HeapSort} class is implementation of heap sort.
 *
 * @author stormma
 * @date 2017/10/19
 */
public class HeapSort implements Sort {
    @Override
    public void sort(Comparable[] a) {
        int N = a.length - 1;
        //构造最大堆
        for (int k = (N - 1) / 2; k >= 0; k--) {
            sink(a, k, N);
        }
        SortUtils.show(a);
        while (N > 0) {
            SortUtils.exch(a, 0, N--);
            sink(a, 0, N);
        }
    }

    private void sink(Comparable[] a, int index, int N) {
        //left sub tree lt N
        while ((2 * index + 1) <= N) {
            int leftI = 2 * index + 1;
            int maxI = leftI, rightI = leftI + 1;
            if (leftI < N && SortUtils.less(a[leftI], a[rightI])) {
                maxI = rightI;
            }
            if (!SortUtils.less(a[index], a[maxI])) {
                break;
            }
            SortUtils.exch(a, index, maxI);
            index = maxI;
        }
    }
}
```

## 算法复杂度比较
![](https://camo.githubusercontent.com/9602fa0fb65208b0f9e645028cdc36eccb37dc3a/687474703a2f2f7374617469632e636f646563656f2e636f6d2f696d616765732f323031362f30332f32663066356336623563376230303762303066306433333432376137306462302e706e67)
