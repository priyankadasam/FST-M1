package activities;

public class Activity2 {
    public static boolean result(int[] a){
        int temp=0;
        boolean resultsum=false;
        for(int i=0;i<a.length;i++){
            if(a[i]==10){
                temp=temp+a[i];
            }
            if(temp>30)
                break;
        }
        if(temp==30)
            resultsum=true;
        return resultsum;
    }
    public static void main(String[] args){
        int a[]={10, 77, 10, 54, -11, 10};
        System.out.println("Result is:"+result(a));

    }
}
