package pe.edu.upc.tratohechotec.repositories;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.tratohechotec.models.Quotation;
import pe.edu.upc.tratohechotec.models.Specialist;

public class TratoHechoRepository {
    private static TratoHechoRepository instance;
    private Specialist specialist;
    private List<Quotation> quotations;

    public TratoHechoRepository() {
    }

    public static TratoHechoRepository getInstance() {
        if (instance==null) instance = new TratoHechoRepository();
        return instance;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public TratoHechoRepository setSpecialist(Specialist customer) {
        this.specialist = customer;
        return this;
    }

    public List<Quotation> getQuotations() {
        return quotations;
    }

    public List<Quotation> getApprovedQuotations() {
        List<Quotation> scheduleFilter = new ArrayList<>();
        for (int i = 0; i < quotations.size(); i++) {
            if (quotations.get(i).getState()==2) {
                scheduleFilter.add(quotations.get(i));
            }
        }
        return scheduleFilter;
    }

    public List<Quotation> getDisapprovedQuotations() {
        List<Quotation> scheduleFilter = new ArrayList<>();
        for (int i = 0; i < quotations.size(); i++) {
            if (quotations.get(i).getState()==3) {
                scheduleFilter.add(quotations.get(i));
            }
        }
        return scheduleFilter;
    }

    public List<Quotation> getPendingQuotations() {
        List<Quotation> scheduleFilter = new ArrayList<>();
        for (int i = 0; i < quotations.size(); i++) {
            if (quotations.get(i).getState()==1) {
                scheduleFilter.add(quotations.get(i));
            }
        }
        return scheduleFilter;
    }

    public List<Quotation> getSolicitedQuotations() {
        List<Quotation> scheduleFilter = new ArrayList<>();
        for (int i = 0; i < quotations.size(); i++) {
            if (quotations.get(i).getState()==0) {
                scheduleFilter.add(quotations.get(i));
            }
        }
        return scheduleFilter;
    }

    public TratoHechoRepository setQuotations(List<Quotation> quotations) {
        this.quotations = quotations;
        return this;
    }
}
