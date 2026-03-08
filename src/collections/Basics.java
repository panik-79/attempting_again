package collections;

// custom class
class Data {
    private String name;
    private int age;

    Data (String name, int age) {
        this.name = name;
        this.age = age;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

public class Basics {

    public static void main(String[] args) {

        Data d1 = new Data("Pujan", 22);
        Data d2 = new Data("Ishita", 23);

        System.out.println("d1 at time t - Name: " + d1.getName() + ", Age: " + d1.getAge());
        System.out.println("d2 at time t - Name: " + d2.getName() + ", Age: " + d2.getAge());

        d1.setAge(d1.getAge() + 2);

        System.out.println("d1 after t+2 yrs - Name: " + d1.getName() + ", Age: " + d1.getAge());
    }
}