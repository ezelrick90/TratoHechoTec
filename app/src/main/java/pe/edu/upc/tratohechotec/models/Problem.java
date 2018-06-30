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

public class Problem {
    private int id;
    private String title;
    private String description;
    private int state;
    private Customer customer;

    public Problem() {
    }

    public Problem(int id, String title, String description, int state, Customer customer) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.state = state;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public Problem setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Problem setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Problem setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getState() {
        return state;
    }

    public Problem setState(int state) {
        this.state = state;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Problem setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("Id", getId());
        bundle.putString("Title", getTitle());
        bundle.putString("Description", getDescription());
        bundle.putInt("State", getState());
        bundle.putBundle("Customer", getCustomer().toBundle());
        return bundle;
    }

    public static class Builder {
        Problem problem;
        List<Problem> problems;

        public Builder() {
            problem = new Problem();
            problems = new ArrayList<>();
        }

        public Builder(Problem problem) {
            this.problem = problem;
        }

        public Builder(List<Problem> problems) {
            this.problems = problems;
        }

        public Problem build() {
            return problem;
        }

        public List<Problem> buildAll() {
            return problems;
        }

        public static Builder from(JSONObject jsonProblem) {
            try{
                return new Problem.Builder(new Problem(
                        jsonProblem.getInt("id"),
                        jsonProblem.getString("title"),
                        jsonProblem.getString("description"),
                        jsonProblem.getInt("state"),
                        Customer.Builder.from(jsonProblem.getJSONObject("customer")).build()
                ));
            }
            catch (JSONException e){
                e.printStackTrace();;
                return null;
            }
        }

        public static Builder from(JSONArray jsonProblems) {
            int length = jsonProblems.length();
            List<Problem> problems = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                try {
                    problems.add(Builder.from(jsonProblems.getJSONObject(i)).build());
                } catch (JSONException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
            return new Builder(problems);
        }

        public static Builder from(Bundle bundle) {
            return new Builder(new Problem(
                    bundle.getInt("Id"),
                    bundle.getString("Title"),
                    bundle.getString("Description"),
                    bundle.getInt("State"),
                    Customer.Builder.from(bundle.getBundle("Customer")).build()
            ));
        }
    }
}
