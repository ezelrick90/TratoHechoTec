package pe.edu.upc.tratohechotec.viewcontrollers.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.edu.upc.tratohechotec.R;
import pe.edu.upc.tratohechotec.models.Quotation;

/**
 * Created by Ricardo Lazaro on 28/06/2018.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    List<Quotation> quotations;

    public ScheduleAdapter() {
    }

    public ScheduleAdapter(List<Quotation> quotations) {
        this.quotations = quotations;
    }

    @NonNull
    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_schedule, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapter.ViewHolder holder, int position) {
        holder.updateViews(quotations.get(position));
    }

    @Override
    public int getItemCount() {
        return quotations.size();
    }

    public ScheduleAdapter setQuotations(List<Quotation> quotations) {
        this.quotations = quotations;
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Quotation quotation;
        TextView titleLabel;
        TextView customerLabel;
        TextView finalPriceLabel;
        TextView quoButton;
        
        public ViewHolder(View itemView) {
            super(itemView);
            titleLabel = (TextView) itemView.findViewById(R.id.titleLabel);
            customerLabel = (TextView) itemView.findViewById(R.id.customerLabel);
            finalPriceLabel = (TextView) itemView.findViewById(R.id.finalPrice);
            quoButton = (TextView) itemView.findViewById(R.id.quotationButton);
        }
        
        public void updateViews(Quotation quotation) {
            this.quotation = quotation;
            titleLabel.setText(quotation.getProblem().getTitle());
            customerLabel.setText(quotation.getProblem().getCustomer().getLogin());
            finalPriceLabel.setText(String.valueOf(quotation.getFinalPrice()));
            quoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 28/06/2018
                }
            });
        }
    }
}
