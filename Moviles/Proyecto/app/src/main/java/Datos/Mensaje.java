package Datos;

/**
 * Created by Alejandro on 11/04/2016.
 */
public class Mensaje {
    private Integer id;
    private Integer from;
    private Integer to;
    private String text;
    private String date;

    public String getDate() {
        return date;
    }

    public Integer getTo() {
        return to;
    }

    public Integer getId() {
        return id;
    }

    public Integer getFrom() {
        return from;
    }

    public String getText() {
        return text;
    }
    public void setFrom(Integer from) {
        this.from = from;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public void setText(String text) {
        this.text = text;
    }
}
