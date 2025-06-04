package DAO;

public class requestDAO {
    int ID;
    String location;
    String requestDate;

    public requestDAO(int ID, String location, String requestDate) {
        this.location = location;
        this.requestDate = requestDate;
        this.ID = ID;
    }
    public String getLocation() {
       return location;
    }
    public String getRequestDate() {
        return requestDate;
    }
    public int getID() {
        return ID;
    }


}
