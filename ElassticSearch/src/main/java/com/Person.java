/**
 *  Copyright 2016 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com;

/**
 * @version 1.0, 03-Mar-2016
 * @author ankit
 */
public class Person {

    /*index: '{{index()}}',
    isActive: '{{bool()}}',
    balance: '{{floating(1000, 4000, 2, "0,0.00")}}',
    age: '{{integer(20, 40)}}',
    eyeColor: '{{random("blue", "brown", "green")}}',
    name: {
        firstName: '{{firstName()}}',
        lastName: '{{surname()}}'
      },
    gender: '{{gender()}}'
     }*/

    private String  index;
    private boolean isActive;
    private String  balance;
    private int     age;
    private String  eyeColor;
    private Name    name;
    private String  gender;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String toString() {
        return "First Name : " + this.getName().getFirstName() + ", Last Name : " + this.getName().getLastName() + ", index : " + this.getIndex() + ", Age :" + this.getAge()
                + ", Eyecolor : " + this.getEyeColor() + ", Gender : " + this.getGender();
    }
}

class Name {
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}