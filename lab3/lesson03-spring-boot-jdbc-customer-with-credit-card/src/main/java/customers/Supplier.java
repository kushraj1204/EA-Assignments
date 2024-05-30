package customers;

import java.util.Objects;

public class Supplier {
    private long supplierNumber;
    private String name;
    private String phone;

    public Supplier() {
    }

    public Supplier(long supplierNumber, String name, String phone) {
        this.supplierNumber = supplierNumber;
        this.name = name;
        this.phone = phone;
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

    public long getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(long supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return Objects.equals(name, supplier.name) && Objects.equals(phone, supplier.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone);
    }
}
