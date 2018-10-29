package com.xiaoorz.savedata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    Button button,write;
    TextView name,age,job;

    List<UserModel>userModels=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


           button=findViewById(R.id.button);
           write=findViewById(R.id.button2);
           name=findViewById(R.id.textView);
           age=findViewById(R.id.textView2);
           job=findViewById(R.id.textView3);



             button.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {



                     savedata();


                 }
             });


             write.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {



                     writedata();


                 }
             });






    }



    private void savedata() {

//save single value to the database

//          String a ="hello world";
//          String b="123";
//          String c="aaa";
//
//          FirebaseDatabase.getInstance().getReference().child("value").push().setValue(a);
//          FirebaseDatabase.getInstance().getReference().child("value").push().setValue(b);
//
//        FirebaseDatabase.getInstance().getReference().child("value").push().setValue(c);




 //save multiple value to the database

           UserModel userModel=new UserModel();
           userModel.age="12";
           userModel.name="Bob";
           userModel.job="student";

        FirebaseDatabase.getInstance().getReference().child("value").child("Bob").setValue(userModel);

         userModel=new UserModel();
        userModel.age="15";
        userModel.name="Jack";
        userModel.job="student";

        FirebaseDatabase.getInstance().getReference().child("value").child("Jack").setValue(userModel);


    }




    private void writedata() {

//write one user's data
//            FirebaseDatabase.getInstance().getReference().child("value").child("Bob").addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//
//                    UserModel userModel=dataSnapshot.getValue(UserModel.class);
//                    name.setText("name is : "+userModel.name);
//                    age.setText("age is : "+userModel.age);
//                    job.setText("job is : "+userModel.job);
//
//
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });



//write various users' data
        FirebaseDatabase.getInstance().getReference().child("value").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                userModels.clear();
                for (DataSnapshot item:dataSnapshot.getChildren()){


                    UserModel userModel=item.getValue(UserModel.class);
                    userModels.add(userModel);

                }

// must show data here,if you write the code outside,the usermodels will be null;
                UserModel userModel=userModels.get(1);
                   name.setText("name is : "+userModel.name);
                    age.setText("age is : "+userModel.age);
                    job.setText("job is : "+userModel.job);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }





}
