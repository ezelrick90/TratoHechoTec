package pe.edu.upc.tratohechotec.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo Lazaro on 28/06/2018.
 */

public class Customer {
    private int id;
    private String login;
    private String password;
    private String name;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String address;
    private String reference;
    private Double latitude;
    private Double longitude;
    private Double rate;
    private boolean online;
    private boolean state;
    private DocumentType document;

    public Customer() {
    }

    public Customer(int id, String login, String password, String name, String lastname, String email, String phoneNumber, String address, String reference, Double latitude, Double longitude, Double rate, boolean online, boolean state, DocumentType document) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.reference = reference;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rate = rate;
        this.online = online;
        this.state = state;
        this.document = document;
    }

    public int getId() {
        return id;
    }

    public Customer setId(int id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public Customer setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Customer setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public Customer setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Customer setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Customer setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getReference() {
        return reference;
    }

    public Customer setReference(String reference) {
        this.reference = reference;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Customer setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Customer setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Double getRate() {
        return rate;
    }

    public Customer setRate(Double rate) {
        this.rate = rate;
        return this;
    }

    public boolean isOnline() {
        return online;
    }

    public Customer setOnline(boolean online) {
        online = online;
        return this;
    }

    public boolean isState() {
        return state;
    }

    public Customer setState(boolean state) {
        this.state = state;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Customer setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("Id", getId());
        bundle.putString("Login", getLogin());
        bundle.putString("Password", getPassword());
        bundle.putString("Name", getName());
        bundle.putString("LastName", getLastname());
        bundle.putString("Email", getEmail());
        bundle.putString("PhoneNumber", getPhoneNumber());
        bundle.putString("Address", getAddress());
        bundle.putString("Reference", getReference());
        bundle.putDouble("Latitude", getLatitude());
        bundle.putDouble("Longitude", getLongitude());
        bundle.putDouble("Rate", getRate());
        bundle.putBoolean("Online", isOnline());
        bundle.putBoolean("State", isState());
        bundle.putBundle("Document", getDocument().toBundle());
        return bundle;
    }

    public DocumentType getDocument() {
        return document;
    }

    public Customer setDocument(DocumentType document) {
        this.document = document;
        return this;
    }

    public static class Builder {
        Customer customer;
        List<Customer> customers;

        public Builder() {
            customer = new Customer();
            customers = new ArrayList<>();
        }

        public Builder(Customer customer) {
            this.customer = customer;
        }

        public Builder(List<Customer> customers) {
            this.customers = customers;
        }

        public Customer build() {
            return customer;
        }

        public List<Customer> buildAll() {
            return customers;
        }

        public static Builder from(JSONObject jsonCustomer) {
            try{
                return new Builder(new Customer(
                        jsonCustomer.getInt("id"),
                        jsonCustomer.getString("login"),
                        jsonCustomer.getString("password"),
                        jsonCustomer.getString("name"),
                        jsonCustomer.getString("lastName"),
                        jsonCustomer.getString("email"),
                        jsonCustomer.getString("phoneNumber"),
                        jsonCustomer.getString("address"),
                        jsonCustomer.getString("reference"),
                        jsonCustomer.getDouble("latitude"),
                        jsonCustomer.getDouble("longitude"),
                        jsonCustomer.getDouble("rate"),
                        jsonCustomer.getBoolean("online"),
                        jsonCustomer.getBoolean("state"),
                        DocumentType.Builder.from(jsonCustomer.getJSONObject("document")).build()
                ));
            }
            catch (JSONException e){
                e.printStackTrace();;
                return null;
            }
        }

        public static Builder from(JSONArray jsonCustomers) {
            int length = jsonCustomers.length();
            List<Customer> customers = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                try {
                    customers.add(Builder.from(jsonCustomers.getJSONObject(i)).build());
                } catch (JSONException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
            return new Builder(customers);
        }

        public static Builder from(Bundle bundle) {
            return new Builder(new Customer(
                    bundle.getInt("Id"),
                    bundle.getString("Login"),
                    bundle.getString("Password"),
                    bundle.getString("Name"),
                    bundle.getString("LastName"),
                    bundle.getString("Email"),
                    bundle.getString("PhoneNumber"),
                    bundle.getString("Address"),
                    bundle.getString("Reference"),
                    bundle.getDouble("Latitude"),
                    bundle.getDouble("Longitude"),
                    bundle.getDouble("Rate"),
                    bundle.getBoolean("Online"),
                    bundle.getBoolean("State"),
                    DocumentType.Builder.from(bundle.getBundle("Document")).build()
            ));
        }
    }
}
