package org.junior.java8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.google.gson.Gson;

public class JsonStock {
 
    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    private static Date getDateFromString(String date) throws ParseException {
        return new SimpleDateFormat("dd-MMM-yyyy", Locale.US).parse(date);
    }
   
    private static int getDayOfWeek(String date) throws ParseException {        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDateFromString(date));
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static void openAndClosePrices(String firstDate, String lastDate, String weekDay) {
        String json = "";
        try {
            json = readUrl("https://jsonmock.hackerrank.com/api/stocks");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Stock stock = gson.fromJson(json, Stock.class);
        
        stock.getData().stream()
        .filter((s) -> {
            try {
                List<String> WEEKDAYS = Arrays.asList( "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
                return (getDateFromString(s.date).compareTo(getDateFromString(firstDate)) >= 0) && 
                       (getDateFromString(s.date).compareTo(getDateFromString(lastDate)) <= 0) &&
                       (getDayOfWeek(s.date) == (WEEKDAYS.indexOf(weekDay) + 1) );
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return false;
        } ).
        forEach((data) -> {
            StringBuilder result = new StringBuilder()
                    .append(data.getDate()).append(" ")
                    .append(data.getOpen()).append(" ")
                    .append(data.getClose());
            System.out.println(result.toString());
        });
    }

    public static void main(String[] args) {

        String firstDate = "1-January-2000";
        String lastDate = "22-February-2000";
        String weekDay = "Monday";
        openAndClosePrices(firstDate, lastDate, weekDay);

    }

    // --------------- class Datum.java ---------------
    class Datum {

        private String date;
        private float open;
        private float high;
        private float low;
        private float close;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public float getOpen() {
            return open;
        }

        public void setOpen(float open) {
            this.open = open;
        }

        public float getHigh() {
            return high;
        }

        public void setHigh(float high) {
            this.high = high;
        }

        public float getLow() {
            return low;
        }

        public void setLow(float low) {
            this.low = low;
        }

        public float getClose() {
            return close;
        }

        public void setClose(float close) {
            this.close = close;
        }
    }

    // --------------- class Stock.java ---------------
    class Stock {

        private Integer page;
        private Integer perPage;
        private Integer total;
        private Integer totalPages;
        private List<Datum> data = null;

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getPerPage() {
            return perPage;
        }

        public void setPerPage(Integer perPage) {
            this.perPage = perPage;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
        }

        public List<Datum> getData() {
            return data;
        }

        public void setData(List<Datum> data) {
            this.data = data;
        }
    }
}
