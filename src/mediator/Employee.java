package mediator;

public class Employee {

    private int eid;
    private String name;
    private String country;
    private String prov;
    private String city;
    private String postcode;
    private String addr;

    public Employee(int eid, String name, String country, String prov, String city, String postcode, String addr) {
        this.eid = eid;
        this.name = name;
        this.country = country;
        this.prov = prov;
        this.city = city;
        this.postcode = postcode;
        this.addr = addr;
    }


    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
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

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
