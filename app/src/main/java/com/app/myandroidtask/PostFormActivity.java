package com.app.myandroidtask;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.myandroidtask.databinding.ActivityGridNumberBinding;
import com.app.myandroidtask.databinding.ActivityPostFormBinding;
import com.app.myandroidtask.models.ProfileModel;

import java.util.Calendar;
import java.util.List;

public class PostFormActivity extends AppCompatActivity {
    ActivityPostFormBinding  b;
    String name="";
    String email="";
    String adhar="";
    String gender="";
    String dateOfJoining="";
    String dateOfBirth="";
    SessionManager sessionManager;
    String[] genderSelect = {"Select Gender", "Male", "Female"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityPostFormBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);
        sessionManager=new SessionManager(PostFormActivity.this);
        genderSpinner();
        b.tvDateOfBirth.setOnClickListener(v -> showDatePickerDialog());













       /* b.tvDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(PostFormActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateOfBirth = String.format("%s-%s-%s", year, String.format("%02d", month + 1), String.format("%02d", dayOfMonth));
                        b.tvDateOfBirth.setText(dateOfBirth);


                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        });*/

        b.tvDateOfJoining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(PostFormActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateOfJoining = String.format("%s-%s-%s", year, String.format("%02d", month + 1), String.format("%02d", dayOfMonth));
                        b.tvDateOfJoining.setText(dateOfJoining);


                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        });
        b.mbSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkForm()){
                  addUser();
                }
            }
        });


    }

    private void addUser() {
        List<ProfileModel> list = sessionManager.getUserList();
        list.add(new ProfileModel(System.currentTimeMillis(), name,email,adhar,gender,dateOfJoining,dateOfBirth));
        sessionManager.setUserList(list);
        Intent intent = new Intent(PostFormActivity.this,UserListActivity.class);
        Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }
    private boolean checkForm() {
        name = b.etName.getText().toString().trim();
        email = b.etEmail.getText().toString().trim();
        adhar = b.etAdhar.getText().toString().trim();
        // Example Aadhaar number
        boolean isValid = VerhoeffAlgorithm.validateAadhaarNumber(adhar);



        if (name.isEmpty()) {
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            b.etName.setError("Name is required");
            return false;
        }
        if (email.isEmpty()) {
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
            b.etEmail.setFocusableInTouchMode(true);
            b.etEmail.requestFocus();
            return false;
        } else if (!Utils.myEmailValid(email)) {
            Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();
            b.etEmail.setFocusableInTouchMode(true);
            b.etEmail.requestFocus();
            return false;
        }
        if (adhar.isEmpty()) {
            Toast.makeText(this, "Enter Your Adhar Number", Toast.LENGTH_SHORT).show();
            b.etAdhar.setError("Adhar is required");
            return false;
        }
        if (!isValid) {
            Toast.makeText(this, "Aadhaar number is invalid.", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (gender.isEmpty()) {
            Toast.makeText(this, "Select your gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (dateOfJoining.isEmpty()) {
            Toast.makeText(this, "Select Date of Joining", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (dateOfBirth.isEmpty()) {
            Toast.makeText(this, "Select Date of Birth", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    void genderSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genderSelect);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        b.tvSignupGender.setAdapter(adapter);
        b.tvSignupGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = parent.getItemAtPosition(position).toString();
                Toast.makeText(PostFormActivity.this, "Selected: " + gender, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void showDatePickerDialog() {
        // Get the current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Subtract 18 years from the current date
        calendar.add(Calendar.YEAR, -18);

        // Create and show the DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, yearSelected, monthSelected, daySelected) -> {
                    // Ensure the user is at least 18 years old
                    if (isValidAge(yearSelected, monthSelected, daySelected)) {
                        String selectedDate = daySelected + "/" + (monthSelected + 1) + "/" + yearSelected;
                        b.tvDateOfBirth.setText("Selected Date: " + selectedDate);
                        dateOfBirth=selectedDate;
                    } else {
                        b.tvDateOfBirth.setText("Age must be 18 or older.");
                    }
                },
                year,
                month,
                day
        );

        // Set the maximum date to 18 years ago
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }

    private boolean isValidAge(int year, int month, int day) {
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(year, month, day);

        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
            age--;  // Adjust age if the birthday has not occurred yet this year
        }

        return age >= 18;
    }






}