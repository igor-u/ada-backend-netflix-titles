package manager;

public class TimeManager {

    public static Integer duration(String time) {

        return Integer.valueOf(time.substring(0, time.indexOf(" ")));

    }

}
