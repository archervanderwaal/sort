# include <stdio.h>
# define SIZE 6

static int aux[SIZE] = {0};

void printArray(int * nums) {
	for (int i = 0; i < SIZE; i++) {
        printf("%d ", nums[i]);
    }
}

void select_sort(int * nums) {
	for (int i = 0; i < SIZE - 1; i++) {
		int min = i;
		for (int j = i + 1; j < SIZE; j++) {
			if (nums[j] < nums[min]) {
				min = j;
			}
		}
		if (min != i) {
			int temp = nums[min];
			nums[min] = nums[i];
			nums[i] = temp;
		}
	}
}

void bubble_sort(int * nums) {
	for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5-i; j++) {
            if (nums[j] > nums[j+1]) {
                int temp = nums[j];
                nums[j] = nums[j+1];
                nums[j+1] = temp;
            }
        }
    }
}

void insert_sort(int * nums) {
	for (int i = 1; i < SIZE; i++) {
		for (int j = i; j > 0; j--) {
			if (nums[j] < nums[j-1]) {
				int temp = nums[j];
				nums[j] = nums[j-1];
				nums[j-1] = temp;
			}
		}
	}
}

void shell_sort(int * nums, int len) {
	//init gap
	int gap = 1;
	while (gap < len / 3) {
		gap = gap * 3 + 1;
	}
	for (; gap > 0; gap /= 3) {
		for (int i = gap; i < len; i++) {
			for (int j = i; j >= gap; j -= gap) {
				if (nums[j] < nums[j-gap]) {
					int temp = nums[j];
                	nums[j] = nums[j-gap];
                	nums[j-gap] = temp;
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

void _merge_sort(int * nums, int low, int high) {
    if (high <= low)
        return;
    int mid = low + ((high - low) >> 1);
    _merge_sort(nums, low, mid);
    _merge_sort(nums, mid+1, high);
    _merge(nums, low, mid, high);
}

void merge_sort(int * nums, int length) {
    int high = length - 1, low = 0;
    _merge_sort(nums, low, high);
}

int main() {
	int nums[] = {3, 2, 1, 4, 9, 8};
//	select_sort(nums);
//	bubble_sort(nums);
//	insert_sort(nums);
//	shell_sort(nums, sizeof(nums) / sizeof(nums[0]));
	merge_sort(nums, sizeof(nums) / sizeof(nums[0]));
	printArray(nums);
}