import javax.swing.*;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Title : WeekData.java
 * Description: This class represent one week's data
 * @author Zhaoxiao Su
 * @version 1.0
 */
public class WeekData {

    public static List<DayData> list;

    public WeekData() {
        list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            list.add(new DayData());
        }
        getData();
    }

    public static boolean isThisWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(new Date(time));
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        if (paramWeek == currentWeek) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        new WeekData();
    }

    public void getData() {
        File file = new File("all.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(null,"data file"+file.getAbsolutePath()+"not exist!");
            return;
        }

        String allFile = FileUtils.readFile(file.getAbsolutePath(), "UTF-8");
        String datePattern = "\\d{4}/\\d{2}/\\d{2}";

        Pattern pattern = Pattern.compile("Fixed Price(.*?)(" + datePattern + ")/?", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(allFile);
        while (matcher.find()) {
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            try {
                date = df.parse(matcher.group(2));
                if (isThisWeek(date.getTime())) {
                    addOneOrder(matcher.group(1), date);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    public void addOneOrder(String order, Date date) {
        String strs[] = order.split("\n");
        DayData dayData = new DayData();
        for (int i = 0; i < strs.length; i++) {
            dayData = new DayData();
            String lineData = strs[i].toLowerCase().trim();
            if (lineData.startsWith("soup:")) { // Soup
                String tmp = lineData.substring(5).trim().toLowerCase();
                if (tmp.equals("tonkotsu")) {
                    dayData.soup[0] = dayData.soup[0] + 1;
                }
                if (tmp.equals("shoyu")) {
                    dayData.soup[1] = dayData.soup[1] + 1;
                }
                if (tmp.equals("shio")) {
                    dayData.soup[2] = dayData.soup[2] + 1;
                }
            }

            if (lineData.startsWith("spiciness:")) { //Spiciness
                String tmp = lineData.substring(10).trim().toLowerCase();
                Pattern pattern = Pattern.compile("\\d{1}");
                Matcher matcher = pattern.matcher(tmp);
                if (matcher.find()) {
                    int num = Integer.parseInt(matcher.group());
                    dayData.spiciness[num] = dayData.spiciness[num] + 1;
                }
            }

            if (lineData.startsWith("noodles:")) { // Soup
                String tmp = lineData.substring(8).trim().toLowerCase();
                if (tmp.equals("soft")) {
                    dayData.noddles[0] = dayData.noddles[0] + 1;
                }
                if (tmp.equals("medium")) {
                    dayData.noddles[1] = dayData.noddles[1] + 1;
                }
                if (tmp.equals("firm")) {
                    dayData.noddles[2] = dayData.noddles[2] + 1;
                }
            }

            if (lineData.startsWith("spring onion:")) { // Soup
                String tmp = lineData.substring(13).trim().toLowerCase();
                if (tmp.equals("no")) {
                    dayData.springOnion[0] = dayData.springOnion[0] + 1;
                }
                if (tmp.equals("just a little")) {
                    dayData.springOnion[1] = dayData.springOnion[1] + 1;
                }
                if (tmp.equals("a lot")) {
                    dayData.springOnion[2] = dayData.springOnion[2] + 1;
                }
            }

            if (lineData.startsWith("extra nori:")) {
                String tmp = lineData.substring("Extra Nori:".length()).trim().toLowerCase();
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(tmp);
                if (matcher.find()) {
                    int num = Integer.parseInt(matcher.group());
                    dayData.extraNori = dayData.extraNori + num;
                }
            }

            if (lineData.startsWith("extra boiled egg:")) {
                String tmp = lineData.substring("Extra Boiled Egg:".length()).trim().toLowerCase();
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(tmp);
                if (matcher.find()) {
                    int num = Integer.parseInt(matcher.group());
                    dayData.extraBoiledEgg = dayData.extraBoiledEgg + num;
                }
            }

            if (lineData.startsWith("bamboo shoots:")) {
                String tmp = lineData.substring("Bamboo Shoots:".length()).trim().toLowerCase();
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(tmp);
                if (matcher.find()) {
                    int num = Integer.parseInt(matcher.group());
                    dayData.bambooShoots = dayData.bambooShoots + num;
                }
            }

            if (lineData.startsWith("extra chashu:")) {
                String tmp = lineData.substring("Extra Chashu:".length()).trim().toLowerCase();
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(tmp);
                if (matcher.find()) {
                    System.out.println(4);
                    int num = Integer.parseInt(matcher.group());
                    dayData.extraChashu = dayData.extraChashu + num;
                }
            }

            if (lineData.startsWith("nori:")) {
                String tmp = lineData.substring("nori:".length()).trim().toLowerCase();
                Pattern pattern = Pattern.compile("\\S+");
                Matcher matcher = pattern.matcher(tmp);
                if (matcher.find()){
                    String str = matcher.group();
                    if(str.equals("yes")){
                        dayData.nori = dayData.nori + 1;
                    }
                }

            }

            if (lineData.startsWith("chashu:")) {
                String tmp = lineData.substring("chashu:".length()).trim().toLowerCase();
                Pattern pattern = Pattern.compile("\\S+");
                Matcher matcher = pattern.matcher(tmp);
                if (matcher.find()){
                    String str = matcher.group();
                    if(str.equals("yes")){
                        dayData.chashu = dayData.chashu + 1;
                    }
                }
            }

            if (lineData.startsWith("boiled egg:")) {
                String tmp = lineData.substring("boiled egg:".length()).trim().toLowerCase();
                Pattern pattern = Pattern.compile("\\S+");
                Matcher matcher = pattern.matcher(tmp);
                if (matcher.find()){
                    String str = matcher.group();
                    if(str.equals("yes")){
                        dayData.boiledEgg = dayData.boiledEgg + 1;
                    }
                }

            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int day = calendar.get(Calendar.DAY_OF_WEEK) == 1 ? (6) : (calendar.get(Calendar.DAY_OF_WEEK) - 2);
            DayData tmpDayDate = list.get(day);

            tmpDayDate.addAll(dayData);
        }
    }


}
