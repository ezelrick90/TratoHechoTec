package pe.edu.upc.tratohechotec.viewcontrollers.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import pe.edu.upc.tratohechotec.R;
import pe.edu.upc.tratohechotec.models.Specialist;
import pe.edu.upc.tratohechotec.networks.TratoHechoApi;
import pe.edu.upc.tratohechotec.repositories.TratoHechoRepository;

public class LoginActivity extends AppCompatActivity {
    private ViewHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        holder = new ViewHolder();
    }

    private class ViewHolder {
        EditText userEditText;
        EditText pwdEditText;
        Button loginButton;
        Button registerButton;
        TextView rememberPwdBButton;
        String TAG = "TratoHecho";
        Specialist specialist;

        public ViewHolder() {
            userEditText = (EditText) findViewById(R.id.userEditText);
            pwdEditText = (EditText) findViewById(R.id.pwdEditText);
            loginButton = (Button) findViewById(R.id.loginButton);
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 6/06/2018 Login Method
                    specialistAuthentication(userEditText.getText().toString(), pwdEditText.getText().toString(), v);
                }
            });
            registerButton = (Button) findViewById(R.id.registerButton);
            rememberPwdBButton = (TextView) findViewById(R.id.rememberPwdButton);
        }

        public void specialistAuthentication(String login, final String password, final View view){
            AndroidNetworking.post(TratoHechoApi.postSpecialistAuthenticationsUrl())
                    .addBodyParameter("login", login)
                    .addBodyParameter("password", password)
                    .setTag(TAG)
                    .setPriority(Priority.LOW)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                specialist = Specialist.Builder.from(response).build();
                                specialist.setPassword(password);
                                TratoHechoRepository.getInstance().setSpecialist(specialist);
                                navigateToMain(view);
                            } catch(Exception e) {
                                Log.d(TAG, String.format("Response Exception: %s", e.getMessage()));
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Log.d(TAG, String.format("onError: %s", anError.getErrorDetail()));
                        }
                    });
        }

        private void navigateToMain(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }
    }
}
