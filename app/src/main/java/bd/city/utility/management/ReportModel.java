package bd.city.utility.management;

public class ReportModel {
    byte[] imgArray;
    String id;
    String address;
    String type;
    String detail;
    String person;
    String textResult;

    public ReportModel(byte[] imgArray, String textResult) {
        this.imgArray = imgArray;
        this.textResult = textResult;
    }

    public ReportModel(byte[] imgArray, String id, String address, String type, String detail, String person, String status, String textResult) {
        this.imgArray = imgArray;
        this.id = id;
        this.address = address;
        this.type = type;
        this.detail = detail;
        this.person = person;
        this.status = status;
        this.textResult = textResult;
    }

    String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getImgArray() {
        return imgArray;
    }

    public void setImgArray(byte[] imgArray) {
        this.imgArray = imgArray;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTextResult() {
        return textResult;
    }

    public void setTextResult(String textResult) {
        this.textResult = textResult;
    }
}
