package activities;

public class Activity1 {
    public static void main(String[] args){
        Car carName = new Car(4,4);
        carName.make=2014;
        carName.color="Black";
        carName.transmission="Manual";
        carName.displayCharacteristics();
        carName.accelerate();
        carName.brake();
    }

}
