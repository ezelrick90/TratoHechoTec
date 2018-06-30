package pe.edu.upc.tratohechotec.viewcontrollers.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.google.gson.Gson;

import pe.edu.upc.tratohechotec.R;
import pe.edu.upc.tratohechotec.models.Quotation;
import pe.edu.upc.tratohechotec.networks.TratoHechoApi;

public class NewQuotationActivity extends AppCompatActivity {
    private ViewHolder viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quotation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewHolder = new ViewHolder();
        viewHolder.setQuotation(Quotation.Builder.from(getIntent().getExtras()).build());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.updateQuotation();
                //viewHolder.putQuotation();
                viewHolder.parseQuotationToJson();
            }
        });
    }

    private class ViewHolder {
        Quotation quotation;
        EditText descriptionEditText;
        EditText priceEditText;
        EditText estimatedTimeEditText;
        CheckBox includesMaterialsCheckbox;
        TextView jsonText;
        String TAG = "TratoHecho";

        public ViewHolder() {
            descriptionEditText = (EditText) findViewById(R.id.descriptionEditText);
            priceEditText = (EditText) findViewById(R.id.priceEditText);
            estimatedTimeEditText = (EditText) findViewById(R.id.estimatedTimeEditText);
            includesMaterialsCheckbox = (CheckBox) findViewById(R.id.includesMaterialsCheckbox);
            jsonText = (TextView) findViewById(R.id.jsonText);
        }

        public ViewHolder setQuotation(Quotation quotation) {
            this.quotation = quotation;
            return this;
        }

        public void updateQuotation() {
            this.quotation.setDescription(descriptionEditText.getText().toString());
            this.quotation.setFinalPrice(Double.valueOf(priceEditText.getText().toString()));
            this.quotation.setEstimatedTime(Integer.valueOf(estimatedTimeEditText.getText().toString()));
            this.quotation.setIncludesMaterial(includesMaterialsCheckbox.isChecked());
            this.quotation.setState(1);
        }

        public void putQuotation() {
            AndroidNetworking.put(TratoHechoApi.putSpecialistQuotationsUrl(quotation.getSpecialist().getId(), quotation.getId()))
                    .addBodyParameter("problem", parseQuotationToJson())
                    .addBodyParameter("description", quotation.getDescription())
                    .addBodyParameter("price", String.valueOf(quotation.getPrice()))
                    .addBodyParameter("estimatedTime", String.valueOf(quotation.getEstimatedTime()))
                    .addBodyParameter("includesMaterial", String.valueOf(quotation.getIncludesMaterial()))
                    .addBodyParameter("state", String.valueOf(quotation.getState()))
                    .addBodyParameter("startDate", quotation.getStartDate().toString())
                    .addBodyParameter("finishDate", quotation.getFinishDate().toString())
                    .addBodyParameter("specialistRate", String.valueOf(quotation.getSpecialistRate()))
                    .addBodyParameter("specialistComment", quotation.getSpecialistComment())
                    .addBodyParameter("customerRate", String.valueOf(quotation.getCustomerRate()))
                    .addBodyParameter("customerComment", quotation.getCustomerComment())
                    .setTag(TAG)
                    .setPriority(Priority.LOW)
                    .build();
        }

        public String parseQuotationToJson() {
            Gson gson = new Gson();
            jsonText.setText(gson.toJson(new putProblem(quotation.getProblem().getId())));
            return jsonText.getText().toString();
        }
    }

    private class putProblem {
        int id;

        public putProblem(int id) {
            this.id = id;
        }
    }

}
