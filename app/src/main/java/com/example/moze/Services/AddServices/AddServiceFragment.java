package com.example.moze.Services.AddServices;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.moze.R;
import com.example.moze.UserAccount.Register.Register;
import com.example.moze.Services.model.Service;
import com.example.moze.Services.ServiceGenerator;
import com.example.moze.Services.interfaces.ServiceInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddServiceFragment extends Fragment implements View.OnClickListener {

    private EditText etName, etBusinessName, etSocialLink, etDescription, etPhone, etWorkingHours, etCost;
    private Spinner spPortfolio, spOccupation, spLocation;
    private ProgressDialog pDialog;
    String[] portfolio = {"Education", "Health", "Technical", "Entertainment", "Domestic"};
    String[] occupation = {"Carpentry", "Music", "Foods & Drinks", "Electrician", "Computer Services", "Mechanic", "Teacher", "Gym", "Transport Services", "Farming Services", "Doctor"};
    String[] location = {
            "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla",
            "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas",
            "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia",
            "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam",
            "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands",
            "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia",
            "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire",
            "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic",
            "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia",
            "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana",
            "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar",
            "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti",
            "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India",
            "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan",
            "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait",
            "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya",
            "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar",
            "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius",
            "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat",
            "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles",
            "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands",
            "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn",
            "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda",
            "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino",
            "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore",
            "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa",
            "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon",
            "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic",
            "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga",
            "Trinidad and Tobago", "Tunisia", "Türkiye", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine",
            "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay",
            "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)",
            "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"
    };
    Button btnAdd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View add_view = inflater.inflate(R.layout.fragment_add, container, false);
        setHasOptionsMenu(true);

        etName = (EditText) add_view.findViewById(R.id.etName);
        etBusinessName = (EditText) add_view.findViewById(R.id.etBusinessName);
        spPortfolio = (Spinner) add_view.findViewById(R.id.spPortfolio);
        spOccupation = (Spinner) add_view.findViewById(R.id.spOccupation);
        etSocialLink = (EditText) add_view.findViewById(R.id.etSocialLink);
        etDescription = (EditText) add_view.findViewById(R.id.etDescription);
        etPhone = (EditText) add_view.findViewById(R.id.etPhone);
        spLocation = (Spinner) add_view.findViewById(R.id.spLocation);
        etWorkingHours = (EditText) add_view.findViewById(R.id.etWorkingHours);
        etCost = (EditText) add_view.findViewById(R.id.etCost);
        btnAdd = (Button) add_view.findViewById(R.id.btnAdd);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,portfolio);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPortfolio.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,occupation);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOccupation.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,location);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLocation.setAdapter(adapter3);

        btnAdd.setOnClickListener(this);


        return add_view;

    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.option_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.register:
                startActivity(new Intent(getActivity(), Register.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnAdd:
                String name = etName.getText().toString();
                String business_name = etBusinessName.getText().toString();
                String portfolio = spPortfolio.getSelectedItem().toString();
                String occupation = spOccupation.getSelectedItem().toString();
                String social_link = etSocialLink.getText().toString();
                String description = etDescription.getText().toString();
                String phone = etPhone.getText().toString();
                String location = spLocation.getSelectedItem().toString();
                String working_hours = etWorkingHours.getText().toString();
                String cost = etCost.getText().toString();
                Service service = new Service(name, business_name, portfolio, occupation, social_link, description, phone, location, working_hours, cost);
                addService(service);
                break;

        }
    }

    private void addService(Service service){

        String name = etName.getText().toString().trim();
        String business_name = etBusinessName.getText().toString().trim();
        String social_link = etSocialLink.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String working_hours = etWorkingHours.getText().toString().trim();
        String cost = etCost.getText().toString().trim();

        if (name.isEmpty()) {
            etName.setError("Name required");
            etName.requestFocus();
            return;
        }

        if (business_name.isEmpty()) {
            etBusinessName.setError("Business name required");
            etBusinessName.requestFocus();
            return;
        }

        if (social_link.isEmpty()) {
            etSocialLink.setError("Website or social link required");
            etSocialLink.requestFocus();
            return;
        }

        if (description.isEmpty()) {
            etDescription.setError("Description required");
            etDescription.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            etPhone.setError("Phone number required");
            etPhone.requestFocus();
            return;
        }

        if (working_hours.isEmpty()) {
            etWorkingHours.setError("Email required");
            etWorkingHours.requestFocus();
            return;
        }

        if (cost.isEmpty()) {
            etCost.setError("Password required");
            etCost.requestFocus();
            return;
        }

        pDialog = new ProgressDialog(getActivity());
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Processing...");
        pDialog.setCancelable(false);

        showpDialog();

        ServiceInterface serviceInterface = ServiceGenerator.createService(ServiceInterface.class);

        Call<Service> call = serviceInterface.addService(service);

        call.enqueue(new Callback<Service>() {
            @Override
            public void onResponse(Call<Service> call, Response<Service> response) {

                hidepDialog();

                if(response.isSuccessful()){

                    Toast.makeText(getActivity(), "You have added the service successfully", Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(getActivity(), "Check your credentials and try again", Toast.LENGTH_SHORT).show();

                }



            }

            @Override
            public void onFailure(Call<Service> call, Throwable t) {

                hidepDialog();

                Toast.makeText(getActivity(), "something went wrong :(", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
