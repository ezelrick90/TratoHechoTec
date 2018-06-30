package pe.edu.upc.tratohechotec.viewcontrollers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import pe.edu.upc.tratohechotec.R;
import pe.edu.upc.tratohechotec.models.Specialist;
import pe.edu.upc.tratohechotec.repositories.TratoHechoRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private ViewHolder viewHolder;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        viewHolder = new ViewHolder(view);
        viewHolder.updateView(TratoHechoRepository.getInstance().getSpecialist());
        return view;
    }

    private class ViewHolder {
        Specialist specialist;
        ImageView imageView;
        EditText loginEditText;
        EditText passwordEditText;
        RatingBar ratingBar;
        EditText nameEditText;
        EditText lastNamesEditText;
        EditText emailEditText;
        EditText companyEditText;
        EditText serviceEditText;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.imageView);
            loginEditText = (EditText) view.findViewById(R.id.loginEditText);
            passwordEditText = (EditText) view.findViewById(R.id.passwordEditText);
            ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            nameEditText = (EditText) view.findViewById(R.id.nameEditText);
            lastNamesEditText = (EditText) view.findViewById(R.id.lastNamesEditText);
            emailEditText = (EditText) view.findViewById(R.id.emailEditText);
            companyEditText = (EditText) view.findViewById(R.id.companyEditText);
            serviceEditText = (EditText) view.findViewById(R.id.serviceEditText);
        }

        public void updateView(Specialist specialist) {
            loginEditText.setText(specialist.getLogin());
            passwordEditText.setText(specialist.getPassword());
            ratingBar.setRating(specialist.getRate().floatValue());
            nameEditText.setText(specialist.getName());
            lastNamesEditText.setText(specialist.getLastName());
            emailEditText.setText(specialist.getEmail());
            companyEditText.setText(specialist.getCompanyName());
            serviceEditText.setText(specialist.getServiceDescription());
        }
    }
}
