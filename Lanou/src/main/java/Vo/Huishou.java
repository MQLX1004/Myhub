package Vo;/**
 * Created by pc on 2018/3/10.
 */

/**
 * describe:
 *
 * @author xxx
 * @date4 {YEAR}/03/10
 */
public class Huishou {
    private String name;
    private String phone;
    private String id;
    private String status;
    private String lzdate;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLzdate() {
        return lzdate;
    }

    public void setLzdate(String lzdate) {
        this.lzdate = lzdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Huishou{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
