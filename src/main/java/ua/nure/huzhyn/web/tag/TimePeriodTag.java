package ua.nure.huzhyn.web.tag;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

public class TimePeriodTag extends SimpleTagSupport {

    private String dateFrom;
    private String dateTo;
    private String locale;

    @Override
    public void doTag() throws IOException {

        if (locale.equals("en")) {
            Duration between = Duration.between(LocalDateTime.parse(dateFrom), LocalDateTime.parse(dateTo));

            getJspContext().getOut().print(String.format("Days: %s Hours: %s Minutes: %s", between.toDays(),
                    between.toHours() % 24, between.toMinutes() % 60));
        }
        if (locale.equals("ru") || locale == null) {
            Duration between = Duration.between(LocalDateTime.parse(dateFrom), LocalDateTime.parse(dateTo));

            getJspContext().getOut().print(String.format("Дней: %s Часов: %s Минут: %s", between.toDays(),
                    between.toHours() % 24, between.toMinutes() % 60));
        }
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
