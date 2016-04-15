package Datos;

/**
 * Created by Alejandro on 11/04/2016.
 */
public class Archivo {
    private Integer id;
    private String name;
    private String contentType;
    private Integer from;
    private Integer to;
    private String date;

    public Integer getFrom() {
        return from;
    }

    public Integer getId() {
        return id;
    }

    public Integer getTo() {
        return to;
    }

    public String getContentType() {
        return contentType;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }
}
