package activities;

public class Car {
    String color;
    String transmission;
    int make;
    int tyres;
    int doors;
    Car(int tyres, int doors){
        this.tyres=tyres;
        this.doors=doors;
    }
    public void displayCharacteristics(){
        System.out.println("Color of car:"+color);
        System.out.println("Transmission of car:"+transmission);
        System.out.println("Make of car:"+make);
        System.out.println("Number of tyres:"+tyres);
        System.out.println("Number of doors:"+doors);
    }
    public void accelerate(){
        System.out.println("Car is moving forward.");
    }
    public void brake(){
        System.out.println("Car has stopped.");
    }


}
