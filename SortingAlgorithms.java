/* This file contains implementations of sorting algorithms.
 * You are NOT allowed to modify any of the given method headers.
 */

public class SortingAlgorithms {

    /*
     * You may define additional helper functions here if you need to.
     * Make sure the helper functions have the private access modifier, as
     * they will only be used in this class.
     */

    /**
     * This method implements the insertion sort algorithm given an array of records and the number of records to sort.
     * @param arr The array of records to sort.
     * @param n The number of records to sort.
     */
    public void insertionSort(Record[] arr, int n) {
        for (int i = 1; i < n; i++) { // Start from the second element
            Record key = arr[i]; // The element to be inserted at the correct position
            int j = i - 1; // The index of the previous element
    
            while (j >= 0 && arr[j].getIdNumber() > key.getIdNumber()) { // While previous element is greater than key and we haven't reached the beginning of the array
                arr[j + 1] = arr[j]; //Shift the previous element to the right, this overwrites the value in the position of the key (we have a copy of it in the key variable)
                j = j - 1; // Move to the previous element
            }
    
            arr[j + 1] = key; // Insert the key in the correct position (found through the previous while loop)
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


	
// start and end are used to check the range
// q is the result of the average of (start and end)
    public void mergeSort(Record[] arr, int start, int end) {
        int q;
        if (start < end) {
            q = (start + end) / 2;
            mergeSort(arr, start, q); // Recursively sort the left subarray
            mergeSort(arr, q + 1, end); // Recursively sort the right subarray
            merge(arr, start, q, end); // Merge the sorted subarrays
        }
    }

	
    
// left, mid and right are used to check the range
// L is a temporary array in which the values of left to mid are copied to
// R is the temporary array in which the values of mid + 1 and to right are copied to

    private void merge(Record[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int i, j;
    
        Record[] L = new Record[n1];
        Record[] R = new Record[n2];
    
        for (i = 0; i < n1; i++) {
            L[i] = arr[left + i]; // Copy elements from the left subarray to L
        }
    
        for (j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j]; // Copy elements from the right subarray to R
        }
    
        int k = left;
        i = 0;
        j = 0;

        while (i < n1 || j < n2) { // Merge the sorted subarrays
                                    // the conditions is to check that all elements have been procesed

            if (i < n1 && (j >= n2 || L[i].getIdNumber() <= R[j].getIdNumber())) // comparison
            {
                arr[k] = L[i]; // If the current element in L is smaller or equal, put it in arr
                i++;
            } 
            else 
            {
                arr[k] = R[j]; // If the current element in R is smaller, put it in arr
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
