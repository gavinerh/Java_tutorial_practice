package Comparison;

import java.time.*;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args){
//        Person p1 = new Person("def", 22, "s00004");
//        Person p5 = new Person("def", 22, "s00004");
//        Person p2 = new Person("elvis", 22, "s00003");
//        Person p4 = new Person("abc", 24, "s00002");
//        Person p3 = new Person("god", 24, "s00001");
//        Set<Person> people = new HashSet<>();
//        people.add(p1); people.add(p2); people.add(p3);
//        people.add(p4); people.add(p5);
////        System.out.println(p1.equals(p2));
//        for(Person p : people){
//            System.out.println(p);
//        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        Instant from = LocalDateTime.parse("2021-12-10 13:00", df).atZone(ZoneId.of("Asia/Singapore")).toInstant();
////        Date.from(from);
//        System.out.println(Date.from(from));
//
//        Instant date = LocalDateTime.now().atZone(ZoneId.of("Asia/Singapore")).toInstant();
//        System.out.println(Date.from(date));
        String startTime = "2021-12-25";
        startTime += " 00:00";

        String endTime = "2021-12-26";
        endTime += " 12:00";
        Instant start = LocalDateTime.parse(startTime, df).atZone(ZoneId.of("Asia/Singapore")).toInstant();
        Date dateStart = Date.from(start);
        Instant end = LocalDateTime.parse(endTime, df).atZone(ZoneId.of("Asia/Singapore")).toInstant();
        Date dateEnd = Date.from(end);

        Long endSec = dateEnd.toInstant().getEpochSecond();
        Long startSec = dateStart.toInstant().getEpochSecond();
        Long diff = endSec - startSec;

        double days = (double) diff / 86400;
//        System.out.println(days);

//        System.out.println(dateStart);
        Instant instant = dateEnd.toInstant();

        DateTimeFormatter df2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String localDate = instant.atZone(ZoneId.of("Asia/Singapore")).toLocalDate().toString();
//        System.out.println(localDate);


        String time = "2021-12-25";
        time += " 24:00";

        Instant i = LocalDateTime.parse(time, df).atZone(ZoneId.of("Asia/Singapore")).toInstant();
        Date d = Date.from(i);
        System.out.println(d);
//        LocalTime localtime = instant.atZone(ZoneId.of("Asia/Singapore")).toLocalTime();
//        System.out.println(localtime.toString());
    }

}
