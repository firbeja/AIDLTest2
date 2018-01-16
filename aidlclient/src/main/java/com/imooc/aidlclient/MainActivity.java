package com.imooc.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.imooc.aidltest.Person;
import com.imooc.aidltest.IImoocAidl;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEtNum1;
    private EditText mEtNum2;
    private EditText mEtRes;

    private Button mBtnAdd;

    IImoocAidl iImoocAidl;

    private ServiceConnection conn = new ServiceConnection() {
        //绑定上服务的时候
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            //拿到了远程的服务，只是一个远程服务的代理 Proxy
            iImoocAidl = IImoocAidl.Stub.asInterface(service);

        }

        //断开服务的时候
        @Override
        public void onServiceDisconnected(ComponentName name) {
            //回收资源
            iImoocAidl = null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        //软件以启动 就绑定服务
        bindService();

    }

    private void initView() {

        mEtNum1 = (EditText) findViewById(R.id.et_num1);
        mEtNum2 = (EditText) findViewById(R.id.et_num2);
        mEtRes = (EditText) findViewById(R.id.et_res);

        mBtnAdd = (Button) findViewById(R.id.btn_add);

        mBtnAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
//        bindService();

//        int num1 = Integer.parseInt(mEtNum1.getText().toString());
//        int num2 = Integer.parseInt(mEtNum2.getText().toString());
//        try {
//            //调用远程的服务
//            int res = iImoocAidl.add(num1, num2);
//
//            mEtRes.setText(res + "");
//        } catch (RemoteException e) {
//            e.printStackTrace();
//            mEtRes.setText("错误了");
//        }


        try {
            List<Person> persons = iImoocAidl.adds(new Person("Tom", 21));
            Log.d("tag", "-------->>>>>>>" + persons.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void bindService() {
        //获取到服务器

        Intent intent = new Intent();
        //新版本 必须 显示 Intent启动 绑定服务
        intent.setComponent(new ComponentName("com.imooc.aidltest", "com.imooc.aidltest.IRemoteService"));
//        bindService(intent, conn, Context.BIND_AUTO_CREATE);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
