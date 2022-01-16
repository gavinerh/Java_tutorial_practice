public class Student implements Comparable<Student> {

    private String metricNo;
    private String name;

    public Student(String metricNo, String name) {
        this.metricNo = metricNo;
        this.name = name;
    }

    public String getMetricNo() {
        return metricNo;
    }

    public void setMetricNo(String metricNo) {
        this.metricNo = metricNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "metricNo='" + metricNo + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        return metricNo.compareTo(o.metricNo);
    }
}
