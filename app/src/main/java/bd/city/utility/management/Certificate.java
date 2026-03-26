package bd.city.utility.management;

public class Certificate {
   public String fname, lname, date, gender, childNo, country, div, dist, upazila, ward, post, vil, roadHouse;

    public Certificate(String fname, String lname, String date, String gender, String childNo, String country, String div, String dist, String upazila, String ward, String post, String vil, String roadHouse) {
        this.fname = fname;
        this.lname = lname;
        this.date = date;
        this.gender = gender;
        this.childNo = childNo;
        this.country = country;
        this.div = div;
        this.dist = dist;
        this.upazila = upazila;
        this.ward = ward;
        this.post = post;
        this.vil = vil;
        this.roadHouse = roadHouse;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getChildNo() {
        return childNo;
    }

    public void setChildNo(String childNo) {
        this.childNo = childNo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDiv() {
        return div;
    }

    public void setDiv(String div) {
        this.div = div;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getUpazila() {
        return upazila;
    }

    public void setUpazila(String upazila) {
        this.upazila = upazila;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getVil() {
        return vil;
    }

    public void setVil(String vil) {
        this.vil = vil;
    }

    public String getRoadHouse() {
        return roadHouse;
    }

    public void setRoadHouse(String roadHouse) {
        this.roadHouse = roadHouse;
    }
}
