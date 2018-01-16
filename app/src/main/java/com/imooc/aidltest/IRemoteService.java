package com.imooc.aidltest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 2018/1/16.
 */

public class IRemoteService extends Service {

    private List<Person> persons;
    /**
     * 当客户端绑定到该服务的时候 会执行
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        persons = new ArrayList<>();
        return iBinder;
    }

    private IBinder iBinder = new IImoocAidl.Stub() {
        @Override
        public List<Person> adds(Person person) throws RemoteException {
            persons.add(person);
            return persons;
        }
    };



//    private IBinder iBinder = new IImoocAidl.Stub() {
//
//        @Override
//        public int add(int num1, int num2) throws RemoteException {
//
//            Log.d("TAG", "收到了远程的请求，输入的参数是" + num1 + "和" +num2);
//
//            return num1 + num2;
//        }
//
//    };

//    private List<String> list ;
//    private IBinder iBinder2 = new IMyAidlInterface.Stub() {
//        @Override
//        public List<String> basicTypes(byte aByte, int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, char aChar, String aString, List<String> aList) throws RemoteException {
//            list = new ArrayList<>();
//            for (int i = 0; i < 10; i++) {
//                list.add("这是" + i);
//            }
//            return list;
//        }
//    };






}
