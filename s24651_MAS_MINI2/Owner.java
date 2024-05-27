public class Owner extends ObjectPlusPlus {
    public String name;
    public String surname;
    public String address;
    public String NIP;

    public Owner(String name, String surname, String address, String NIP) {
        super();
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.NIP = NIP;
    }

    @Override
    public String toString(){
        String nip = "";
        if (NIP != null) {
            nip = ", NIP: " + NIP;
        }
        return "Owner" +
                " -> name: " + name +
                ", surname: " + surname  +
                ", address: " + address +
                nip;
    }
}
