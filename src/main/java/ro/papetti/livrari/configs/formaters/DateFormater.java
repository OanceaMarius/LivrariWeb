package ro.papetti.livrari.configs.formaters;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
@Slf4j
@Component
@Getter
@Setter
@ToString
//@PropertySource("classpath:application.properties")
public class DateFormater {

    @Value("${app.date.format}")
    private  String dateFormatPattern;

    @Value("${app.date.zone-id}")
    private  String zoneIdString;

    @Value("${app.datetime.format}")
    private  String dateTimeFormatPattern;

    @Autowired
    public DateFormater(@Value("${app.datetime.format}") String dateFormatPattern, @Value("${app.date.zone-id}") String zoneIdString, @Value("${app.date.format}") String dateTimeFormatPattern) {
        this.dateFormatPattern = dateFormatPattern;
        this.zoneIdString = zoneIdString;
        this.dateTimeFormatPattern = dateTimeFormatPattern;
    }

    public DateFormater() {

    }

    public String inFormatulDoarData(Date date) {
        ZonedDateTime zonedDateTime = getZonedDateTime(date);

        if (dateFormatPattern==null)
            dateFormatPattern="dd.MM.yyyy";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatPattern);
        return zonedDateTime.format(formatter); // Format the Instant
    }

    public String inFormatulDataTimp(Date date) {
        ZonedDateTime zonedDateTime = getZonedDateTime(date);

        if (dateTimeFormatPattern==null)
            dateFormatPattern="dd.MM.yyyy HH:mm:ss";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatPattern);
        return zonedDateTime.format(formatter); // Format the Instant
    }

    private @NotNull ZonedDateTime getZonedDateTime(Date date) {
        long fastTime = date.getTime();
        Date date2 = new Date(fastTime);
        Instant instant = date2.toInstant(); // Convert Date to Instant
        ZoneId zoneId;
        if (zoneIdString==null){
            zoneId=ZoneId.systemDefault();
        }else {
            zoneId = ZoneId.of(zoneIdString);
        }
        return instant.atZone(zoneId);
    }

}
