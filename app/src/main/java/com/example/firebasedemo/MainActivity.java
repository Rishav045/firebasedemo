package com.example.firebasedemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    EditText etName, etDepartmentName;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.etName);
        etDepartmentName = findViewById(R.id.etDepartmentName);
        db = FirebaseFirestore.getInstance();
        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener((view) -> {
            Student student = new Student(etName.getText().toString(),
                    etDepartmentName.getText().toString());
            db.collection("students")
                    .add(student)
                    .addOnSuccessListener(s -> {
                        Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(err -> {
                        Toast.makeText(this, "Err " + err, Toast.LENGTH_SHORT).show();
                    });
        });
        Button btnRetrieve = findViewById(R.id.btnRetrieve);
        btnRetrieve.setOnClickListener((view) -> {
            //etName.setText(students.get(0).getName());
            //etDepartmentName.setText(students.get(0).getDepartmentName());
            db.collection("students")
                    .whereEqualTo("departmentName", "cse")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Student s = document.toObject(Student.class);
                                    etName.setText(s.getName());
                                    etDepartmentName.setText(s.getDepartmentName());
                                }
                            } else {
                                Log.w("TAG", "Error", task.getException());
                            }
                        }
                    });
        });
    }
}