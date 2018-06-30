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
import pe.edu.upc.tratohechotec.viewcontrollers.adapters.ChecklistAdapter;
import pe.edu.upc.tratohechotec.viewcontrollers.adapters.ScheduleAdapter;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChecklistFragment extends Fragment {
    private RecyclerView quotationsRecyclerView;
    private ChecklistAdapter quotationsAdapter;
    private RecyclerView.LayoutManager quotationsLayoutManager;
    //private List<Quotation> quotations;
    private static final String TAG = "TH";

    public ChecklistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checklist, container, false);

        quotationsRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        //quotations = new ArrayList<>();
        TratoHechoRepository.getInstance().setQuotations(new ArrayList<Quotation>());
        quotationsAdapter = new ChecklistAdapter(TratoHechoRepository.getInstance().getQuotations());
        quotationsLayoutManager = new GridLayoutManager(
                view.getContext(), getSpanCount(getResources().getConfiguration()));
        quotationsRecyclerView.setAdapter(quotationsAdapter);
        quotationsRecyclerView.setLayoutManager(quotationsLayoutManager);

        updateData();
        return view;
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
                            quotationsAdapter.setQuotations(TratoHechoRepository.getInstance().getPendingQuotations());
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
