package pe.edu.upc.tratohechotec.viewcontrollers.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.edu.upc.tratohechotec.R;
import pe.edu.upc.tratohechotec.models.Quotation;
import pe.edu.upc.tratohechotec.viewcontrollers.activities.ProblemActivity;

public class ChecklistAdapter extends RecyclerView.Adapter<ChecklistAdapter.ViewHolder> {
    List<Quotation> quotations;

    public ChecklistAdapter() {
    }

    public ChecklistAdapter(List<Quotation> quotations) {
        this.quotations = quotations;
    }

    @NonNull
    @Override
    public ChecklistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_checklist, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ChecklistAdapter.ViewHolder holder, int position) {
        holder.updateViews(quotations.get(position));
    }

    @Override
    public int getItemCount() {
        return quotations.size();
    }

    public List<Quotation> getQuotations() {
        return quotations;
    }

    public ChecklistAdapter setQuotations(List<Quotation> quotations) {
        this.quotations = quotations;
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Quotation quotation;
        TextView titleLabel;
        TextView customerLabel;
        TextView descriptionLabel;
        TextView problemButton;

        public ViewHolder(View itemView) {
            super(itemView);
            titleLabel = (TextView) itemView.findViewById(R.id.titleLabel);
            customerLabel = (TextView) itemView.findViewById(R.id.customerLabel);
            descriptionLabel = (TextView) itemView.findViewById(R.id.descriptionLabel);
            problemButton = (TextView) itemView.findViewById(R.id.problemButton);
            problemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ProblemActivity.class);
                    intent.putExtras(quotation.toBundle());
                    context.startActivity(intent);
                }
            });
        }

        public void updateViews(Quotation quotation) {
            this.quotation = quotation;
            titleLabel.setText(quotation.getProblem().getTitle());
            customerLabel.setText(quotation.getProblem().getCustomer().getLogin());
            descriptionLabel.setText(String.valueOf(quotation.getProblem().getDescription()));
        }
    }
}
