import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        String str = "suricata[46478]: [1:2010935:3] ET SCAN Suspicious inbound to MSSQL port 1433 [Classification: Potentially Bad Traffic] [Priority: 2] {TCP} 119.187.144.206:31868 -> 137.132.22.20:1433";
        String str1 = "suricata[44820]: [1:2010938:3] ET SCAN Suspicious inbound to mSQL port 4333 [Classification: Potentially Bad Traffic] [Priority: 2] {TCP} 198.199.94.207:54828 -> 137.132.22.21:4333";
        String str2 = "suricata[1380]: [1:2010936:3] ET SCAN Suspicious inbound to Oracle SQL port 1521 [Classification: Potentially Bad Traffic] [Priority: 2] {TCP} 85.2.236.60:56797 -> 137.132.22.20:1521";
        String arr = str.replace("] ", ",");
        List<String> list = new ArrayList<>();
        String title = "\"Source\",\"Message\",\"Priority\",\"IP Routing\"";
        System.out.println(title);
        list.add(str.replace("] ", ","));
        list.add(str1.replace("] ", ","));
        list.add(str2.replace("] ", ","));
        writeToCSV(list, title);
    }
    public static void writeToCSV(List<String> list, String title){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\gavin\\OneDrive\\Desktop\\logFile\\logs.csv"))){
            for(String s : list){
                writer.write(s);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
