package exemple.weatherapp.api.java.yupweather.model;

public class ErrorResponse {
    private int cod;
    private String message;

    public ErrorResponse(
            int cod,
            String message
    ) {
        this.cod = cod;
        this.message = message;
    }

    public int getCod() {
        return cod;
    }

    public String getMessage() {
        return message;
    }

}

