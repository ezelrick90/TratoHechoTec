package pe.edu.upc.tratohechotec.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ricardo Lazaro on 28/06/2018.
 */

public class Quotation {
    private int id;
    private String description;
    private Double price;
    private int estimatedTime;
    private Boolean includesMaterial;
    private int state;
    private Date startDate;
    private Date finishDate;
    private Double finalPrice;
    private Double specialistRate;
    private String specialistComment;
    private Double customerRate;
    private String customerComment;
    private Problem problem;
    private Specialist specialist;

    public Quotation() {
    }

    public Quotation(int id, String description, Double price, int estimatedTime, Boolean includesMaterial, int state, Date startDate, Date finishDate, Double finalPrice, Double specialistRate, String specialistComment, Double customerRate, String customerComment, Problem problem, Specialist specialist) {
        this.id = id;
        this.description = description;
        this.estimatedTime = estimatedTime;
        this.price = price;
        this.includesMaterial = includesMaterial;
        this.state = state;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.finalPrice = finalPrice;
        this.specialistRate = specialistRate;
        this.specialistComment = specialistComment;
        this.customerRate = customerRate;
        this.customerComment = customerComment;
        this.problem = problem;
        this.specialist = specialist;
    }


    public int getId() {
        return id;
    }

    public Quotation setId(int id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Quotation setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public Quotation setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
        return this;
    }

    public Boolean getIncludesMaterial() {
        return includesMaterial;
    }

    public Quotation setIncludesMaterial(Boolean includesMaterial) {
        this.includesMaterial = includesMaterial;
        return this;
    }

    public int getState() {
        return state;
    }

    public Quotation setState(int state) {
        this.state = state;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Quotation setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public Quotation setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
        return this;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public Quotation setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
        return this;
    }

    public Double getSpecialistRate() {
        return specialistRate;
    }

    public Quotation setSpecialistRate(Double specialistRate) {
        this.specialistRate = specialistRate;
        return this;
    }

    public String getSpecialistComment() {
        return specialistComment;
    }

    public Quotation setSpecialistComment(String specialistComment) {
        this.specialistComment = specialistComment;
        return this;
    }

    public Double getCustomerRate() {
        return customerRate;
    }

    public Quotation setCustomerRate(Double customerRate) {
        this.customerRate = customerRate;
        return this;
    }

    public String getCustomerComment() {
        return customerComment;
    }

    public Quotation setCustomerComment(String customerComment) {
        this.customerComment = customerComment;
        return this;
    }

    public Problem getProblem() {
        return problem;
    }

    public Quotation setProblem(Problem problem) {
        this.problem = problem;
        return this;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public Quotation setSpecialist(Specialist specialist) {
        this.specialist = specialist;
        return this;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("Id",getId());
        bundle.putString("Description",getDescription());
        bundle.putDouble("Price", getPrice());
        bundle.putInt("EstimatedTime",getEstimatedTime());
        bundle.putBoolean("IncludesMaterial", getIncludesMaterial());
        bundle.putInt("State", getState());
        bundle.putString("StartDate", getStartDate().toString());
        bundle.putString("FinishDate", getFinishDate().toString());
        bundle.putDouble("FinalPrice", getFinalPrice());
        bundle.putDouble("SpecialistRate",getSpecialistRate());
        bundle.putString("SpecialistComment", getSpecialistComment());
        bundle.putDouble("CustomerRate", getCustomerRate());
        bundle.putString("CustomerComment", getCustomerComment());
        bundle.putBundle("Problem", getProblem().toBundle());
        bundle.putBundle("Specialist", getSpecialist().toBundle());
        return bundle;
    }

    public Double getPrice() {
        return price;
    }

    public Quotation setPrice(Double price) {
        this.price = price;
        return this;
    }

    public static class Builder {
        Quotation quotation;
        List<Quotation> quotations;

        public Builder() {
            quotation = new Quotation();
            quotations = new ArrayList<>();
        }

        public Builder(Quotation quotation) {
            this.quotation = quotation;
        }

        public Builder(List<Quotation> quotations) {
            this.quotations = quotations;
        }

        public Quotation build() {
            return quotation;
        }

        public List<Quotation> buildAll() {
            return quotations;
        }

        public static Builder from(JSONObject jsonQuotation) {
            try{
                return new Builder(new Quotation(
                        jsonQuotation.getInt("id"),
                        jsonQuotation.getString("description"),
                        jsonQuotation.getDouble("price"),
                        jsonQuotation.getInt("estimatedTime"),
                        jsonQuotation.getBoolean("includesMaterial"),
                        jsonQuotation.getInt("state"),
                        stringToDate(jsonQuotation.getString("startDate")),
                        stringToDate(jsonQuotation.getString("finishDate")),
                        jsonQuotation.getDouble("finalPrice"),
                        jsonQuotation.getDouble("specialistRate"),
                        jsonQuotation.getString("specialistComment"),
                        jsonQuotation.getDouble("customerRate"),
                        jsonQuotation.getString("customerComment"),
                        Problem.Builder.from(jsonQuotation.getJSONObject("problem")).build(),
                        Specialist.Builder.from(jsonQuotation.getJSONObject("specialist")).build()
                ));
            }
            catch (JSONException e){
                e.printStackTrace();;
                return null;
            }
        }

        public static Builder from(JSONArray jsonQuotations) {
            int length = jsonQuotations.length();
            List<Quotation> quotations = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                try {
                    quotations.add(Builder.from(jsonQuotations.getJSONObject(i)).build());
                } catch (JSONException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
            return new Builder(quotations);
        }

        public static Builder from(Bundle bundle) {
            return new Builder(new Quotation(
            bundle.getInt("Id"),
            bundle.getString("Description"),
            bundle.getDouble("Price"),
            bundle.getInt("EstimatedTime"),
            bundle.getBoolean("IncludesMaterial"),
            bundle.getInt("State"),
            stringToDate(bundle.getString("StartDate")),
            stringToDate(bundle.getString("FinishDate")),
            bundle.getDouble("FinalPrice"),
            bundle.getDouble("SpecialistRate"),
            bundle.getString("SpecialistComment"),
            bundle.getDouble("CustomerRate"),
            bundle.getString("CustomerComment"),
            Problem.Builder.from(bundle.getBundle("Problem")).build(),
            Specialist.Builder.from(bundle.getBundle("Specialist")).build()
            ));
        }

        public static Date stringToDate(String dtStart) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            Date date = new Date();
            try {
                date = format.parse(dtStart);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        }
    }
}

