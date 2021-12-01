package java1;


public class Student<K> {
    K number;
    K name;
    public Student(K number, K name){
        this.number = number;
        this.name = name;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + number.hashCode();
        result = prime * result + name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Student)){
            return false;
        }
        Student instance = (Student) o;
        if(name.equals(instance.name) && number.equals(instance.number)){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return String.format("Number: " + number + " , Name: " + name);
    }

}
