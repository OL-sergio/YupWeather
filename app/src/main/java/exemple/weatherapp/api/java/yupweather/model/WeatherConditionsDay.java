package exemple.weatherapp.api.java.yupweather.model;
/*
{
  "coord": {
    "lon": 10.99,
    "lat": 44.34
  },
      "weather": [
        {
          "id": 501,
          "main": "Rain",
          "description": "moderate rain",
          "icon": "10d"
        }
      ],
  "base": "stations",
      "main": {
        "temp": 298.48,
        "feels_like": 298.74,
        "temp_min": 297.56,
        "temp_max": 300.05,
        "pressure": 1015,
        "humidity": 64,
        "sea_level": 1015,
        "grnd_level": 933
      },
  "visibility": 10000,
  "wind": {
        "speed": 0.62,
        "deg": 349,
        "gust": 1.18
          },
          "rain": {
            "1h": 3.16
          },
          "clouds": {
            "all": 100
          },
      "dt": 1661870592,
          "sys": {
            "type": 2,
            "id": 2075663,
            "country": "IT",
            "sunrise": 1661834187,
            "sunset": 1661882248
      },
          "timezone": 7200,
          "id": 3163858,
          "name": "Zocca",
          "cod": 200
}
*/

public class WeatherConditionsDay {

    private int id;
    private float temp;
    private String pressure;
    private String visibility;
    private String humidity;
    private float speed;


    public WeatherConditionsDay(
            int id,
            float temp,
            String pressure,
            String visibility,
            String humidity,
            float speed
    )
    {
        this.id = id;
        this.pressure = pressure;
        this.temp = temp;
        this.visibility = visibility;
        this.humidity = humidity;
        this.speed = speed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
