public class Student2 {

    private String metricNo;
    private String name;

    public Student2(String metricNo, String name) {
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




}
