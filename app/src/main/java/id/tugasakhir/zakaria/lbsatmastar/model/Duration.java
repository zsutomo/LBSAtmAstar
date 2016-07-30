package id.tugasakhir.zakaria.lbsatmastar.model;

/**
 * Created by zsuto_000 on 7/28/2016.
 */
public class Duration {
    public String text;
    public long value;

    public Duration(String text, long value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
