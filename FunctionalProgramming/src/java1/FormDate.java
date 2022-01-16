package java1;

import java.time.LocalDate;

public class FormDate {
    private LocalDate start;
    private LocalDate end;

    public FormDate(LocalDate start, LocalDate end){
        this.end = end;
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }
    public LocalDate getStart(){return start;}

    public void setStart(LocalDate start){this.start = start;}
    public void setEnd(LocalDate end){this.end = end;}


}
