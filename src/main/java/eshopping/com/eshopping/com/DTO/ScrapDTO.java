package eshopping.com.eshopping.com.DTO;

public class ScrapDTO {

    public String LINK;

    @Override
    public String toString() {
        return "ScrapDTO{" +
                "LINK='" + LINK + '\'' +
                '}';
    }

    public String getLINK() {
        return LINK;
    }

    public void setLINK(String LINK) {
        this.LINK = LINK;
    }

    public ScrapDTO(String LINK) {
        this.LINK = LINK;
    }
    public ScrapDTO( ) {

    }
}
