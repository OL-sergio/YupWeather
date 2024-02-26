package exemple.weatherapp.api.java.yupweather.model;

public class WeatherDay {
     /*{
        "coord": {
            "lon": 10.99,
             "lat": 44.34
    },
        "weather": [
        {
                "id": 803,
                "main": "Clouds",
                "description": "broken clouds",
                "icon": "04n"
        }
],
        "base": "stations",
            "main": {
                "temp": 277.29,
                "feels_like": 277.29,
                "temp_min": 275.52,
                "temp_max": 279.19,
                "pressure": 1033,
                "humidity": 65,
                "sea_level": 1033,
                "grnd_level": 943
    },
        "visibility": 10000,
            "wind": {
                "speed": 0.17,
                "deg": 198,
                "gust": 0.22
    },
        "clouds": {
        "all": 62
    },
        "dt": 1706719247,
            "sys": {
                "type": 2,
                "id": 2004688,
                "country": "IT",
                "sunrise": 1706682971,
                "sunset": 1706718145
    },
        "timezone": 3600,
            "id": 3163858,
            "name": "Zocca",
            "cod": 200
    }*/

    private int id;
    private String main;
    private String description;
    private String icon;
    private float temp;
    private String pressure;
    private String visibility;
    private String humidity;
    private float speed;
    private String country;
    private String name;

    public WeatherDay(
            int id,
            String main,
            String description,
            String icon,
            float temp,
            String pressure,
            String visibility,
            String humidity,
            float speed,
            String country,
            String name
    )

    {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
        this.temp = temp;
        this.pressure = pressure;
        this.visibility = visibility;
        this.humidity = humidity;
        this.speed = speed;
        this.country = country;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
