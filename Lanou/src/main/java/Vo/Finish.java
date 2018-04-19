package Vo;/**
 * Created by pc on 2018/3/23.
 */

/**
 * describe:
 *
 * @author xxx
 * @date4 {YEAR}/03/23
 */
public class Finish {
    private String name;
    private String phone;
    private String address;
    private String type;
    private String id;
    private String finishdate;
    private String huishouid;
    private String huishouname;
    private String huishouphone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFinishdate() {
        return finishdate;
    }

    public void setFinishdate(String finishdate) {
        this.finishdate = finishdate;
    }

    public String getHuishouid() {
        return huishouid;
    }

    public void setHuishouid(String huishouid) {
        this.huishouid = huishouid;
    }

    public String getHuishouname() {
        return huishouname;
    }

    public void setHuishouname(String huishouname) {
        this.huishouname = huishouname;
    }

    public String getHuishouphone() {
        return huishouphone;
    }

    public void setHuishouphone(String huishouphone) {
        this.huishouphone = huishouphone;
    }
    @Override
    public String toString() {
        return "Finish{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", finishdate='" + finishdate + '\'' +
                ", huishouid='" + huishouid + '\'' +
                ", huishouname='" + huishouname + '\'' +
                ", huishouphone='" + huishouphone + '\'' +
                '}';
    }

}
