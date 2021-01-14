package JavaObjects;

public class Admin extends Person{
    boolean isSuperAdmin;

    public Admin() {

    }

    public Admin(String cnp, String firstName, String lastName, String email, String password, String iban, String telephone_number, String contact_number, String address, boolean isSuperAdmin) {
        super(cnp, firstName, lastName, email, password, iban, telephone_number, contact_number, address);
        this.isSuperAdmin = isSuperAdmin;
    }

    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }
}
