public class Sort {

    /** quick sort */
    private static int partition(int[] arr, int p, int r) {
        int x = arr[r];
        int i = p-1;
        for(int j=p; j<=r-1; j++) {
            if(arr[j]<x) {
                i = i+1;
                arr[i] += arr[j] - (arr[j]=arr[i]);
            }
        }
        arr[i+1] += arr[r] - (arr[r]=arr[i+1]);
        return i+1;
    }

    public static void quickSort(int[] arr, int p, int r) {
        if(p<r) {
            int q = partition(arr,p,r);
            quickSort(arr,p,q-1);
            quickSort(arr,q+1,r);
        }
    }

    /** merge sort */
    private static void merge(int[] arr, int p, int q, int r) {
        int m = q-p+1;
        int n = r-q;
        // copying
        int[] arr1 = new int[m];
        int[] arr2 = new int[n];
        for(int i=0; i<m; i++)  arr1[i] = arr[p+i];
        for(int i=0; i<n; i++)  arr2[i] = arr[q+i+1];

        // now combining
        int i=0, j=0, k=p;
        while(i<m && j<n) {
            if(arr1[i]<=arr2[j])    arr[k++] = arr1[i++];
            else    arr[k++] = arr2[j++];
        }
        while(i<m)  arr[k++] = arr1[i++];
        while(j<n)  arr[k++] = arr2[j++];

    }

    public static void mergeSort(int[] arr, int p, int r) {
        if(p<r) {
            int q = (p+r)/2;
            mergeSort(arr,p,q);
            mergeSort(arr,q+1,r);
            merge(arr,p,q,r);
        }
    }

}
