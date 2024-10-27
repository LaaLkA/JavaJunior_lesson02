package task1;

public class Person {
    private String name;
    private int age;

    public Person() {
        this.name = "Name";
        this.age = 25;
    }

    public void displayInfo(){
        System.out.printf("Name: %s, Age: %s", name, age);
    }
}
