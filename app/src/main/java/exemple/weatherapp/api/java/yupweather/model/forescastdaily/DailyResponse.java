package exemple.weatherapp.api.java.yupweather.model.forescastdaily;

import java.util.List;

public class DailyResponse {

    private String id;
    private String name;
    private String country;
    private String population;
    private String timezone;
    private String cod;
    private String message;
    private String cnt;
    private List<DailyData> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public List<DailyData> getList() {
        return list;
    }

    public void setList(List<DailyData> list) {
        this.list = list;
    }
}
