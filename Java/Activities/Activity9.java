package activities;

import java.util.ArrayList;

public class Activity9 {
    public static void main(String[] args) {
        ArrayList<String> myList = new ArrayList<String>();
        myList.add("Ramu");
        myList.add("Raji");
        myList.add("Mona");
        //Adding object at specific index
        myList.add(3, "Sweety");
        myList.add(1, "John");
        System.out.println("Print All the Objects:");
        for (String s : myList) {
            System.out.println(s);
        }

        System.out.println("3rd element in the list is: " + myList.get(2));
        System.out.println("Is Buddy is in list: " + myList.contains("Buddy"));
        System.out.println("Size of ArrayList: " + myList.size());

        myList.remove("Papaya");

        System.out.println("New Size of ArrayList: " + myList.size());
    }
}
