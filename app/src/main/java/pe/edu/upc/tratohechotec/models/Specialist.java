package pe.edu.upc.tratohechotec.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Specialist {
    private int id;
    private String login;
    private String password;
    private String name;
    private String lastName;
    private String email;
    private String companyName;
    private String serviceDescription;
    private String phoneNumber;
    private String facebook;
    private String webSite;
    private String address;
    private String reference;
    private Double latitude;
    private Double longitude;
    private Boolean acredited;
    private Boolean membership;
    private Double rate;
    private Boolean online;
    private Boolean state;
    private DocumentType document;

    public Specialist() {
    }

    public Specialist(int id, String login, String password, String name, String lastName, String email, String companyName, String serviceDescription, String phoneNumber, String facebook, String webSite, String address, String reference, Double latitude, Double longitude, Boolean acredited, Boolean membership, Double rate, Boolean online, Boolean state, DocumentType document) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.companyName = companyName;
        this.serviceDescription = serviceDescription;
        this.phoneNumber = phoneNumber;
        this.facebook = facebook;
        this.webSite = webSite;
        this.address = address;
        this.reference = reference;
        this.latitude = latitude;
        this.longitude = longitude;
        this.acredited = acredited;
        this.membership = membership;
        this.rate = rate;
        this.online = online;
        this.state = state;
        this.document = document;
    }

    public int getId() {
        return id;
    }

    public Specialist setId(int id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public Specialist setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Specialist setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public Specialist setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Specialist setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Specialist setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Specialist setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public Specialist setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Specialist setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getFacebook() {
        return facebook;
    }

    public Specialist setFacebook(String facebook) {
        this.facebook = facebook;
        return this;
    }

    public String getWebSite() {
        return webSite;
    }

    public Specialist setWebSite(String webSite) {
        this.webSite = webSite;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Specialist setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getReference() {
        return reference;
    }

    public Specialist setReference(String reference) {
        this.reference = reference;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Specialist setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Specialist setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Boolean getAcredited() {
        return acredited;
    }

    public Specialist setAcredited(Boolean acredited) {
        this.acredited = acredited;
        return this;
    }

    public Boolean getMembership() {
        return membership;
    }

    public Specialist setMembership(Boolean membership) {
        this.membership = membership;
        return this;
    }

    public Double getRate() {
        return rate;
    }

    public Specialist setRate(Double rate) {
        this.rate = rate;
        return this;
    }

    public Boolean getOnline() {
        return online;
    }

    public Specialist setOnline(Boolean online) {
        this.online = online;
        return this;
    }

    public Boolean getState() {
        return state;
    }

    public Specialist setState(Boolean state) {
        this.state = state;
        return this;
    }

    public DocumentType getDocument() {
        return document;
    }

    public Specialist setDocument(DocumentType document) {
        this.document = document;
        return this;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("Id", getId());
        bundle.putString("Login", getLogin());
        bundle.putString("Password", getPassword());
        bundle.putString("Name", getName());
        bundle.putString("LastName", getLastName());
        bundle.putString("Email", getEmail());
        bundle.putString("CompanyName", getCompanyName());
        bundle.putString("ServiceDescription", getServiceDescription());
        bundle.putString("PhoneNumber", getPhoneNumber());
        bundle.putString("Facebook", getFacebook());
        bundle.putString("Website", getWebSite());
        bundle.putString("Address", getAddress());
        bundle.putString("Reference", getReference());
        bundle.putDouble("Latitude", getLatitude());
        bundle.putDouble("Longitude", getLongitude());
        bundle.putBoolean("Acredited", getAcredited());
        bundle.putBoolean("Membership", getMembership());
        bundle.putDouble("Rate", getRate());
        bundle.putBoolean("Online", getOnline());
        bundle.putBoolean("State", getState());
        bundle.putBundle("Document", getDocument().toBundle());
        return bundle;
    }

    public static class Builder {
        Specialist specialist;
        List<Specialist> specialists;

        public Builder() {
            specialist = new Specialist();
            specialists = new ArrayList<>();
        }

        public Builder(Specialist specialist) {
            this.specialist = specialist;
        }

        public Builder(List<Specialist> specialists) {
            this.specialists = specialists;
        }

        public Specialist build() {
            return specialist;
        }

        public List<Specialist> buildAll() {
            return specialists;
        }

        public static Builder from(JSONObject jsonSpecialist) {
            try{
                return new Builder(new Specialist(
                        jsonSpecialist.getInt("id"),
                        jsonSpecialist.getString("login"),
                        jsonSpecialist.getString("password"),
                        jsonSpecialist.getString("name"),
                        jsonSpecialist.getString("lastName"),
                        jsonSpecialist.getString("email"),
                        jsonSpecialist.getString("companyName"),
                        jsonSpecialist.getString("serviceDescription"),
                        jsonSpecialist.getString("phoneNumber"),
                        jsonSpecialist.getString("facebook"),
                        jsonSpecialist.getString("webSite"),
                        jsonSpecialist.getString("address"),
                        jsonSpecialist.getString("reference"),
                        jsonSpecialist.getDouble("latitude"),
                        jsonSpecialist.getDouble("longitude"),
                        jsonSpecialist.getBoolean("acredited"),
                        jsonSpecialist.getBoolean("memberShip"),
                        jsonSpecialist.getDouble("rate"),
                        jsonSpecialist.getBoolean("online"),
                        jsonSpecialist.getBoolean("state"),
                        DocumentType.Builder.from(jsonSpecialist.getJSONObject("document")).build()
                ));
            }
            catch (JSONException e){
                e.printStackTrace();;
                return null;
            }
        }

        public static Builder from(JSONArray jsonSpecialists) {
            int length = jsonSpecialists.length();
            List<Specialist> specialists = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                try {
                    specialists.add(Builder.from(jsonSpecialists.getJSONObject(i)).build());
                } catch (JSONException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
            return new Builder(specialists);
        }

        public static Builder from(Bundle bundle) {
            return new Builder(new Specialist(
                    bundle.getInt("Id"),
                    bundle.getString("Login"),
                    bundle.getString("Password"),
                    bundle.getString("Name"),
                    bundle.getString("LastName"),
                    bundle.getString("Email"),
                    bundle.getString("CompanyName"),
                    bundle.getString("ServiceDescription"),
                    bundle.getString("PhoneNumber"),
                    bundle.getString("Facebook"),
                    bundle.getString("Website"),
                    bundle.getString("Address"),
                    bundle.getString("Reference"),
                    bundle.getDouble("Latitude"),
                    bundle.getDouble("Longitude"),
                    bundle.getBoolean("Acredited"),
                    bundle.getBoolean("Membership"),
                    bundle.getDouble("Rate"),
                    bundle.getBoolean("Online"),
                    bundle.getBoolean("State"),
                    DocumentType.Builder.from(bundle.getBundle("Document")).build()
            ));
        }
    }
}

