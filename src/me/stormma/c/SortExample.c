# include <stdio.h>
# define SIZE 6

static int aux[SIZE] = {0};

void show_array(int* array) {
	for (int i = 0; i < SIZE; i++) {
        printf("%d ", array[i]);
    }
    printf("\n");
}

void exch(int* array, int a, int b) {
    int temp = array[b];
    array[b] = array[a];
    array[a] = temp;
}

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

void _merge(int* array, int low, int mid, int high) {
	int i = low;
	int j = mid + 1;
	for (int k = low; k <= high; k++) {
		aux[k] = array[k];
	}
	for (int k = low; k <= high; k++) {
		if (i > mid)
			array[k] = aux[j++];
		else if (j > high)
			array[k] = aux[i++];
		else if (aux[j] < aux[i]) {
			array[k] = aux[j++];
		} else {
			array[k] = aux[i++];
		}
	}
}

void _merge_sort(int* array, int low, int high) {
    if (high <= low)
        return;
    int mid = low + ((high - low) >> 1);
    _merge_sort(array, low, mid);
    _merge_sort(array, mid+1, high);
    _merge(array, low, mid, high);
}

void merge_sort(int * array, int length) {
    int high = length - 1, low = 0;
    _merge_sort(array, low, high);
}

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

int main() {
	int array[] = {3, 2, 1, 4, 9, 8};
	select_sort(array);
	printf("After select sort: \n");
	show_array(array);

	bubble_sort(array);
	printf("After bubble sort: \n");
	show_array(array);

	insert_sort(array);
	printf("After insert sort: \n");
	show_array(array);

	shell_sort(array, sizeof(array) / sizeof(array[0]));
	printf("After shell sort: \n");
	show_array(array);

	merge_sort(array, sizeof(array) / sizeof(array[0]));
	printf("After merge sort: \n");
	show_array(array);


    quick_sort(array, sizeof(array) / sizeof(array[0]));
    printf("After quick sort: \n");
    show_array(array);

    quick_sort_3way(array, sizeof(array) / sizeof(array[0]));
    printf("After quick three-way sort: \n");
    show_array(array);

    return 0;
}