package manager;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;

public class DateManager {

    public static LocalDate dateAdded(String date) {

        String mesPorExtenso = date.substring(0, date.indexOf(" "));
        String mes = String.valueOf(Month.valueOf(mesPorExtenso.toUpperCase()).getValue());
        String dia = date.substring(date.indexOf(" "), date.indexOf(",")).trim();
        String ano = date.substring(date.length() - 4);

        if (Integer.valueOf(mes) < 10) {
            mes = "0" + mes;
        }

        if (Integer.valueOf(dia) < 10) {
            dia = "0" + dia;
        }

        String dataFinal = mes + dia + ano;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddyyyy");

        LocalDate localDate = LocalDate.parse(dataFinal, dtf);

        return localDate;

    }

    public static Year releaseYear(String year) {
        return Year.parse(year, DateTimeFormatter.ofPattern("yyyy"));
    }
}
