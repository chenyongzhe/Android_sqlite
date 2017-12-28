package com.example.a12763.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private  MydatabaseHelper dbRegeister;
    private  String id;
    private   String pw1;
    private   String pw2;
    private EditText idText;
    private EditText  pw1Text;
    private  EditText  pw2Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        idText =(EditText)findViewById(R.id.register_id);
        pw1Text=(EditText)findViewById(R.id.regester_password1);
        pw2Text=(EditText) findViewById(R.id.register_password2);

        dbRegeister=new MydatabaseHelper(RegisterActivity.this,"Chen.db",null,1);
    }
    public  void Regist(View view){
        id=idText.getText().toString();
        pw1=pw1Text.getText().toString();
        pw2=pw2Text.getText().toString();
       if(id.length()!=0&&pw1.length()!=0&&pw2.length()!=0&&pw1.equals(pw2)){
           SQLiteDatabase db=dbRegeister.getWritableDatabase();
           ContentValues values=new ContentValues();
           values.put("userid",id);
           values.put("password",pw1);
           db.insert("Login",null,values);
           Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
       }else{
            if(id.length()!=0&&pw1.length()!=0&&pw2.length()!=0&&pw1!=pw2) {
                Toast.makeText(RegisterActivity.this, "密码和确认密码不一致", Toast.LENGTH_SHORT).show();
            }
           if(id.equals("")||pw1.equals("")||pw2.equals("")){
               Toast.makeText(RegisterActivity.this, "请填完整再注册", Toast.LENGTH_SHORT).show();

           }
       }


    }
}
