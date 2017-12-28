package com.example.a12763.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    private static final String TAG="chen";
    private  String name;
    private   String pw;
    private    EditText username;
    private   EditText password;
    private   MydatabaseHelper  dbHelper;
   // private Connection con=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"我是陈永喆1");
        Log.d(TAG, "onCreate: 我是陈永喆2");
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        Button bu=(Button)findViewById(R.id.button1);
        bu.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this,"我点击了长按",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
   dbHelper=new MydatabaseHelper(MainActivity.this,"Chen.db",null,1);


    }
    public void cl(View v){
        name=username.getText().toString();
        pw=password.getText().toString();

        SQLiteDatabase db=dbHelper.getWritableDatabase();
     Cursor cursor = db.rawQuery("select password from Login where userid='"+name+"'",null);
        if(cursor.moveToFirst()) {
            do {
                  String pass=cursor.getString(cursor.getColumnIndex("password"));
                          if (pass.equals(pw)){

                              Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                              Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                             // Toast.makeText(MainActivity.this,"我是陈永喆",Toast.LENGTH_LONG).show();
                              startActivity(intent);
                          }else{

                             // Toast.makeText(MainActivity.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                              MyDialogFragment f=new MyDialogFragment("账号或密码错误");
                               // f.in="账号或密码错误";
                              f.show(getFragmentManager(),"mydilog");

                          }
            } while (cursor.moveToNext());
        }else{
            if(name.length()==0){
                Toast.makeText(MainActivity.this,"请输入账号",Toast.LENGTH_LONG).show();

            }else{

                MyDialogFragment f1=new MyDialogFragment("此账号不存在");
                //f1.in="此账号不存在";
               // f1.builder.setMessage("此账号不存在");
                f1.show(getFragmentManager(),"mydilog");

            }





        }
        /*
        if(name.equals("chen")&&pw.equals("123456")) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            Toast.makeText(MainActivity.this,"我是陈永喆",Toast.LENGTH_LONG).show();
            startActivity(intent);
        }else{
            //Toast.makeText(MainActivity.this,"账号密码错误",Toast.LENGTH_SHORT).show();
             MyDialogFragment f=new MyDialogFragment();
            f.show(getFragmentManager(),"mydilog");
        }
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.chen,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(MainActivity.this,"我点击了添加",Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
               // Toast.makeText(MainActivity.this,"我点击了移除",Toast.LENGTH_SHORT).show();
               finish();
                break;
            default:
        }
        return true;
    }

    public void CreateBase(View v1){

   Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(intent);

    }


}
