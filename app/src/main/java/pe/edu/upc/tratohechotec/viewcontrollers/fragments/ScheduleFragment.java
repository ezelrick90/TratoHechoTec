package pe.edu.upc.tratohechotec.viewcontrollers.fragments;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.util.ArrayList;

import pe.edu.upc.tratohechotec.R;
import pe.edu.upc.tratohechotec.models.Quotation;
import pe.edu.upc.tratohechotec.networks.TratoHechoApi;
import pe.edu.upc.tratohechotec.repositories.TratoHechoRepository;
import pe.edu.upc.tratohechotec.viewcontrollers.adapters.ScheduleAdapter;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {
    private RecyclerView quotationsRecyclerView;
    private ScheduleAdapter quotationsAdapter;
    private RecyclerView.LayoutManager quotationsLayoutManager;
    //private List<Quotation> quotations;
    private static final String TAG = "TH";
    private Spinner filterSpinner;
    private ArrayAdapter<CharSequence> filterAdapter;

    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        quotationsRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        //quotations = new ArrayList<>();
        TratoHechoRepository.getInstance().setQuotations(new ArrayList<Quotation>());
        quotationsAdapter = new ScheduleAdapter(TratoHechoRepository.getInstance().getQuotations());
        quotationsLayoutManager = new GridLayoutManager(
                view.getContext(), getSpanCount(getResources().getConfiguration()));
        quotationsRecyclerView.setAdapter(quotationsAdapter);
        quotationsRecyclerView.setLayoutManager(quotationsLayoutManager);
        filterSpinner = (Spinner) view.findViewById(R.id.filterSpinner);
        filterAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.quotations_states_array, android.R.layout.simple_spinner_item);
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(filterAdapter);
        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateQuotationAdapter(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        updateData();
        return view;
    }

    private void updateQuotationAdapter(int position) {
        switch (position) {
            case 0:
                quotationsAdapter.setQuotations(TratoHechoRepository.getInstance().getQuotations());
                break;
            case 1:
                quotationsAdapter.setQuotations(TratoHechoRepository.getInstance().getPendingQuotations());
                break;
            case 2:
                quotationsAdapter.setQuotations(TratoHechoRepository.getInstance().getDisapprovedQuotations());
                break;
            case 3:
                quotationsAdapter.setQuotations(TratoHechoRepository.getInstance().getApprovedQuotations());
                break;
            default:
                quotationsAdapter.setQuotations(TratoHechoRepository.getInstance().getQuotations());
        }
        quotationsAdapter.notifyDataSetChanged();
    }

    private void updateData() {
        AndroidNetworking.get(TratoHechoApi.getSpecialistQuotationsUrl(
                TratoHechoRepository.getInstance().getSpecialist().getId()))
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d(TAG, String.format(response.toString()));
                            TratoHechoRepository.getInstance()
                                    .setQuotations(Quotation.Builder.from(response.getJSONArray("quotations"))
                                            .buildAll());
                            quotationsAdapter.setQuotations(TratoHechoRepository.getInstance().getQuotations());
                            quotationsAdapter.notifyDataSetChanged();
                        } catch (Exception e){
                            Log.d(TAG, String.format("Response Exception: %s", e.getMessage()));
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    private int getSpanCount(Configuration configuration) {
        return configuration.orientation == ORIENTATION_PORTRAIT ? 1 : 2;
    }

    private void updateLayout(Configuration configuration) {
        ((GridLayoutManager) quotationsLayoutManager)
                .setSpanCount(getSpanCount(configuration));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateLayout(newConfig);
    }
}
