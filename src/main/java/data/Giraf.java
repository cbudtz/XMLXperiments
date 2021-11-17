package data;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Giraf {
    private String name;
    private int serialNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }
}
