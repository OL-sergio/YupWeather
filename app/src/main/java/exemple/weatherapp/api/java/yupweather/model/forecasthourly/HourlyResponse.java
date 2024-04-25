package exemple.weatherapp.api.java.yupweather.model.forecasthourly;


import java.util.List;

public class HourlyResponse {

    private int cod;
    private int message;
    private int cnt;
    private List<HourlyData> list;

    public HourlyResponse() {
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<HourlyData> getList() {
        return list;
    }

    public void setList(List<HourlyData> list) {
        this.list = list;
    }
}
