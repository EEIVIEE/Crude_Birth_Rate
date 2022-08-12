package sg.edu.rp.c346.id21021397.crudebirthrate;

public class Birth {
    private String value;
    private String year;

    public Birth(String value, String year) {
        this.value = value;
        this.year = year;
    }



    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Birth{" +
                ", value='" + value + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
