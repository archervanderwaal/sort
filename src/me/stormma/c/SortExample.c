# include <stdio.h>
# define SIZE 6

static int aux[SIZE] = {0};

void show_array(int * array) {
	for (int i = 0; i < SIZE; i++) {
        printf("%d ", array[i]);
    }
}

void select_sort(int * array) {
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

void bubble_sort(int * array) {
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

void insert_sort(int * array) {
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

void shell_sort(int * array, int len) {
	//init gap
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

void _merge(int * array, int low, int mid, int high) {
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

void _merge_sort(int * array, int low, int high) {
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

int main() {
	int array[] = {3, 2, 1, 4, 9, 8};
//	select_sort(array);
//	bubble_sort(array);
//	insert_sort(array);
//	shell_sort(array, sizeof(array) / sizeof(array[0]));
	merge_sort(array, sizeof(array) / sizeof(array[0]));
	show_array(array);
}