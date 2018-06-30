package pe.edu.upc.tratohechotec.viewcontrollers.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pe.edu.upc.tratohechotec.R;
import pe.edu.upc.tratohechotec.models.Quotation;

public class ProblemActivity extends AppCompatActivity {
    private ViewHolder viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);

        viewHolder = new ViewHolder();
        viewHolder.updateView(Quotation.Builder.from(getIntent().getExtras()).build());
    }

    private class ViewHolder {
        Quotation quotation;
        EditText titleEditText;
        EditText customerEditText;
        EditText descriptionEditText;
        Button quotationButton;

        public ViewHolder() {
            titleEditText = (EditText) findViewById(R.id.titleEditText);
            customerEditText = (EditText) findViewById(R.id.customerEditText);
            descriptionEditText = (EditText) findViewById(R.id.descriptionEditText);
            quotationButton = (Button) findViewById(R.id.quotationButton);
            quotationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, NewQuotationActivity.class);
                    intent.putExtras(quotation.toBundle());
                    context.startActivity(intent);
                }
            });
        }

        public void updateView(Quotation quotation) {
            this.quotation = quotation;
            titleEditText.setText(quotation.getProblem().getTitle());
            customerEditText.setText(quotation.getProblem().getCustomer().getLogin());
            descriptionEditText.setText(quotation.getProblem().getDescription());
        }
    }
}
