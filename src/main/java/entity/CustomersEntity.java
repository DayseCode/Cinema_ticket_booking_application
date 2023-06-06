package entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "customers", schema = "bookingdb")
public class CustomersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_id")
    private int customerId;
    @Basic
    @Column(name = "customerName")
    private String customerName;
    @Basic
    @Column(name = "customerSurname")
    private String customerSurname;
    @Basic
    @Column(name = "customerPhone")
    private String customerPhone;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomersEntity that = (CustomersEntity) o;
        return customerId == that.customerId && Objects.equals(customerName, that.customerName) && Objects.equals(customerSurname, that.customerSurname) && Objects.equals(customerPhone, that.customerPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerName, customerSurname, customerPhone);
    }
}
