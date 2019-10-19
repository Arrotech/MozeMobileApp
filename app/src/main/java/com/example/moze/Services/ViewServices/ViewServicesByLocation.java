package com.example.moze.Services.ViewServices;

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
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moze.R;
import com.example.moze.Services.AddServices.AddServiceFragment;
import com.example.moze.Services.ServiceGenerator;
import com.example.moze.Services.interfaces.ServiceInterface;
import com.example.moze.Services.model.Service;
import com.example.moze.Services.model.ServiceList;
import com.example.moze.Services.model.ServiceResponse;
import com.example.moze.UserAccount.Register.Register;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewServicesByLocation extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ServiceListAdapter adapter;
    Button btnGetServicesLocation;
    Spinner spServicesOccupation, spServicesLocation;
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
    List<Service> serviceList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {

        View searchView = inflater.inflate(R.layout.fragment_view_services_by_location, container, false);
        setHasOptionsMenu(true);

        recyclerView = searchView.findViewById(R.id.rvGetServices);
        spServicesOccupation = (Spinner) searchView.findViewById(R.id.spServicesOccupation);
        spServicesLocation = (Spinner) searchView.findViewById(R.id.spServicesLocation);
        btnGetServicesLocation = (Button) searchView.findViewById(R.id.btnGetServicesLocation);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,occupation);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spServicesOccupation.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,location);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spServicesLocation.setAdapter(adapter2);

        btnGetServicesLocation.setOnClickListener(this);

        return searchView;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnGetServicesLocation:
                getServicesByLocation();
                break;
        }

    }

    public void getServicesByLocation(){

        String occupation = spServicesOccupation.getSelectedItem().toString();
        String location = spServicesLocation.getSelectedItem().toString();

        ServiceInterface serviceInterface = ServiceGenerator.createService(ServiceInterface.class);

        Call<ServiceList> call = serviceInterface.getServicesLocation(occupation, location);

        call.enqueue(new Callback<ServiceList>() {
            @Override
            public void onResponse(Call<ServiceList> call, Response<ServiceList> response) {

                if(response.isSuccessful()){
                    serviceList = response.body().getServicesLocation();
                    adapter = new ServiceListAdapter(getActivity(), serviceList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(adapter);
                }else {
                    Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ServiceList> call, Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong :(", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.option_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.add_service:
                startActivity(new Intent(getActivity(), AddServiceFragment.class));
                return true;
            case R.id.view_services:
                startActivity(new Intent(getActivity(), ViewServicesFragment.class));
                return true;
            case R.id.register:
                startActivity(new Intent(getActivity(), Register.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
