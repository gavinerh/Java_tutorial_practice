package Comparison;

public class Person {
    public String name;
    public int age;
    public String ic;

    public Person(String name, int age, String ic){
        this.name = name;
        this.age = age;
        this.ic = ic;
    }


    // you need to override both the equals and the hashcode method even if the implementation
    // for equals is different from that of hashcode (should be the same meaning)
    @Override
    public boolean equals(Object o){
        if(!(o instanceof Person)){
            return false;
        }
        Person p = (Person) o;
        if(p.name == this.name && p.age == this.age && p.ic == this.ic){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = prime;
        result += prime * name.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("Name: " + name + ", Age: " + age + ", IC: " + ic);
    }


}
