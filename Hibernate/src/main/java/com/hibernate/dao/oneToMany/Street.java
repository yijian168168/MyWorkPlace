package com.hibernate.dao.oneToMany;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Administrator on 2016/1/6 0006.
 */
@Entity
public class Street {

    @Id
    @GeneratedValue
    private int id;

    private String streetName;

    @OneToMany(mappedBy = "street",fetch = FetchType.EAGER)
    private Set<Address> addresses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
}
