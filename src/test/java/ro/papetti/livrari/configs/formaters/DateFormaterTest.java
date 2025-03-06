package ro.papetti.livrari.configs.formaters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

class DateFormaterTest {
    @BeforeEach
    void setUp() {

    }


    @Test
    void inFormatulDoarDataIntoarceFormatulRomanesc() {

        long fastTime = 1737324000000L;
        Date date2 = new Date(fastTime);
        DateFormater dateFormater= new DateFormater("dd.MM.yyyy","Europe/Bucharest","dd.MM.yyyy HH");
//        LocalDate localDate = LocalDate.parse("2025-01-20");
//        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(date2);
        System.out.println(dateFormater);
        System.out.println(dateFormater.inFormatulDoarData(date2));
        assertEquals("20.01.2025",dateFormater.inFormatulDoarData(date2));
    }

    @Test
    void inFormatulDataTimpIntoarceFormatulRomanesc() {

        long fastTime2 = 1737324050563L;
        Date date2 = new Date(fastTime2);
        DateFormater dateFormater= new DateFormater();
        System.out.println(date2);
        System.out.println(dateFormater.inFormatulDataTimp(date2));
        assertEquals("20.01.2025 00:00:50",dateFormater.inFormatulDoarData(date2));
    }


}