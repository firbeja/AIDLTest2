// IImoocAidl.aidl
package com.imooc.aidltest;

import com.imooc.aidltest.Person;
// Declare any non-default types here with import statements

interface IImoocAidl {

    //计算两个数的和
//    int add(int num1, int num2);

    List<Person> adds(in Person person);

}
