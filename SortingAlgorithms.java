/* This file contains implementations of sorting algorithms.
 * You are NOT allowed to modify any of the given method headers.
 */

public class SortingAlgorithms {

    /*
     * You may define additional helper functions here if you need to.
     * Make sure the helper functions have the private access modifier, as
     * they will only be used in this class.
     */

    public void insertionSort(Record[] arr, int n) {
        for (int i = 1; i < n; i++) {
            Record key = arr[i];
            int j = i - 1;
    
            while (j >= 0 && arr[j].getIdNumber() > key.getIdNumber()) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
    
            arr[j + 1] = key;
        }
    }

    public void selectionSort(Record[] arr, int n) {
        // TODO: Implement this sorting algorithm here.

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {

                if (arr[j].getIdNumber() < arr[minIndex].getIdNumber()) {
                    minIndex = j;
                }
            }

            Record temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public void mergeSort(Record[] arr, int p, int r) {
        int q;
        if(p < r)
        {
            q = (p + r) / 2;
            mergeSort(arr, p, q);
            mergeSort(arr, q + 1, r);
            merge(arr, p, q, r);

        }

    }

    private void merge(Record[] arr, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        int i, j;
    
        Record[] L = new Record[n1];
        Record[] R = new Record[n2];
    
        for (i = 0; i < n1; i++) {
            L[i] = arr[p + i];
        }
    
        for (j = 0; j < n2; j++) {
            R[j] = arr[q + 1 + j];
        }
    
        int k = p;
        i = 0;
        j = 0;
        while (i < n1 || j < n2) { // Change the loop condition here
            if (i < n1 && (j >= n2 || L[i].getIdNumber() <= R[j].getIdNumber())) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
    }
    

    public void quickSort(Record[] arr, int lowerBound, int upperBound) {
		//if we've reached an array of just 1 element, then leave the recursion because we're done
		if(lowerBound >= upperBound) {
			return;
		}
		
		//set median of three as pivot
		int pivot = this.getMedianOfThree(arr, lowerBound, upperBound);
		
		//partition the array
		int left = this.partition(arr, lowerBound, upperBound, pivot);
	
		//recursively sort the left and right sides
		this.quickSort(arr, lowerBound, left - 1);
		this.quickSort(arr, left + 1, upperBound);
	}
	
	/*find and return the median of three as pivot*/
	private int getMedianOfThree(Record[] arr, int lowerBound, int upperBound) {
		int first = lowerBound;
		int last = upperBound;
		int middle = (upperBound + lowerBound) / 2;
		
		// sort the first, middle, and last elements
        if (arr[first].getIdNumber() > arr[middle].getIdNumber()) {
            swap(arr, first, middle);
        }
		
        if (arr[first].getIdNumber() > arr[last].getIdNumber()) {
            swap(arr, first, last);
        }
		
        if (arr[middle].getIdNumber() > arr[last].getIdNumber()) {
            swap(arr, middle, last);
        }
		
		//swap the median with the last element so it becomes the pivot
		swap(arr, middle, last);
		
		return arr[last].getIdNumber();
	}
	
	/*partition to left and right helper method*/
	private int partition(Record[] arr, int lowerBound, int upperBound, int pivot) {
		int left = lowerBound;
		int right = upperBound;
		
		//partition the elements to the left and right of pivot
		while(left < right) {
			//keep looking for element with value greater than pivot, if found, stop
			while(arr[left].getIdNumber() <= pivot && left < right) {
				left++;
			}
			
			//keep looking for element with value less than pivot, if found, stop
			while(arr[right].getIdNumber() >= pivot && right > left) {
				right--;
			}
			
			//swap the two
			this.swap(arr, left, right);
			
			//repeat everything until the array is partitioned
		}
		
		//swap the pivot into where both left and right stopped
		this.swap(arr, left, upperBound);
		
		return left;
	}
	
	/*swap 2 positions helper method*/
	private void swap(Record[] arr, int a, int b) {
		Record temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
