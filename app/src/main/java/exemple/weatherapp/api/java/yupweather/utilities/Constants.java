package exemple.weatherapp.api.java.yupweather.utilities;


import exemple.weatherapp.api.java.yupweather.BuildConfig;

public final class Constants {

    public static final int REQUEST_CODE = 101 ;
    //public static final String API_KEY = "b67f25d3dbc12d7d9ed5c12b09e1bafd";
    public static final String API_KEY = BuildConfig.API_KEY;
    //public static final String BASE_URL = "https://pro.openweathermap.org/data/2.5/";
    public static final String BASE_URL = BuildConfig.BASE_URL;
    //https://pro.openweathermap.org/data/2.5/forecast/
    public static final String BASE_URL_HOURS = "https://pro.openweathermap.org/data/2.5/forecast/";

    //DataService
    public static final String HOURLY = "hourly";
    public static final String WEATHER = "weather";
    public static final String LAT = "lat";
    public static final String LON = "lon";
    public static final String APPID = "appid";



    //DeserializerConditions
    public static final String TEMP = "temp";
    public static final String HUMIDITY = "humidity";
    public static final String PRESSURE = "pressure";
    public static final String VISIBILITY = "visibility";
    public static final String SPEED = "speed";
    public static final String ID = "id";
    public static final String SYS = "sys";
    public static final String WIND = "wind";


    //DeserializerMain
    public static final String NAME = "name";
    public static final String MAIN = "main";
    public static final String COUNTRY = "country";
    public static final String DESCRIPTION = "description";
    public static final String ICON = "icon";
    public static final String DATE_TIME = "dt";
    public static final String TIME_ZONE = "timezone";

    //DataServiceHours
    public static final int CMD = 10;
    public static final String CNT = "cnt";
    public static final String UNITS = "units";
    public static final String UNITS_FORMAT = "metric";


    //SharedPreferenceLocation
    public static final String LOCATION = "location_gps";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";

}
