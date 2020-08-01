package org.cxyxh.blogshow.model;

/**
 * @ahthor : cxyxh
 * @date : 2020/7/12 21:17
 * @describetion :
 */
public class Address {

    private String province;

    private String city;

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
