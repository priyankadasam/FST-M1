package activities;

import java.util.Arrays;

public class Activity4 {
    public static void sort(int[] a){
        int temp=0;
        for(int i=0;i<a.length;i++){
            for(int j=i+1;j<a.length;j++){
                if(a[i]>a[j]){
                    temp=a[i];
                    a[i]=a[j];
                    a[j]=temp;
                }
            }
        }

    }
    public static void main(String[] args){
        int[] a={3, 7, 2, 6};
        sort(a);
        System.out.println("Sorted array:" + Arrays.toString(a));
    }
}
