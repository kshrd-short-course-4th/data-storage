package com.example.rathana.datastoragedemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rathana.datastoragedemo.entity.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InternalStorageActivity extends AppCompatActivity {
    private static final String FILE_NAME = "store_user.hrd";
    private static final String USER_INFO = "user.data";
    @BindView(R.id.username) EditText username;
    @BindView(R.id.gender) EditText gender;
    @BindView(R.id.email) EditText email;
    @BindView(R.id.address) EditText address;
    @BindView(R.id.display) TextView display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_save_to_internal_storage)
    public void onSaveToInternalStorage(View view) {
        //get User data from UI
        User user=new User();
        user.setName(username.getText().toString());
        user.setGender(gender.getText().toString());
        user.setAddress(address.getText().toString());
        user.setEmail(email.getText().toString());
        //save user to file
        //saveToStorage(user);

        //save object to internal storage
        saveUserObjectToStorage(user);
        clearForm();
        Toast.makeText(this, "user save success", Toast.LENGTH_SHORT).show();

    }
    @OnClick(R.id.btn_get_user)
    public void getUserFromInternalStorage(){
        //display.setText(readUser());
        User user=readUserObject();
        setDataToForm(user);

    }

    public  void saveToStorage(User user){
        try {
            OutputStream ous = openFileOutput(FILE_NAME,Context.MODE_PRIVATE);
            String data="name :" +user.getName()+
                    "gender :" +user.getGender()+
                    "email :"+ user.getEmail()+
                    "address :"+user.getAddress();
            //start to write data to file
            ous.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveUserObjectToStorage(User user){
        OutputStream ous=null;
        ObjectOutputStream objectOutputStream=null;
        try {
             ous= openFileOutput(USER_INFO,Context.MODE_PRIVATE);
            objectOutputStream=new ObjectOutputStream(ous);
            //start to write data to file
            objectOutputStream.writeObject(user);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                objectOutputStream.close();
                ous.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public String  readUser(){
        InputStream ins=null;
        String data="";
        try {
            ins=openFileInput(FILE_NAME);
            int i=0;
            while ((i=ins.read()) !=-1){
                data=data + (char) i;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
               ins.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public User readUserObject(){
        InputStream ins=null;
        ObjectInputStream objectInputStream=null;
        User user=null;
        try {
            ins=openFileInput(USER_INFO);
            objectInputStream=new ObjectInputStream(ins);
            user =(User) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void setDataToForm(User user){
        username.setText(user.getName());
        gender.setText(user.getGender());
        email.setText(user.getEmail());
        address.setText(user.getAddress());
    }

    public void clearForm(){
        username.setText("");
        address.setText("");
        email.setText("");
        gender.setText("");
    }

}
