/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package src.mytest;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @version 1.0, 26-Nov-2015
 * @author ankit
 */
public class IteratorTest {
    public static void main(String[] args) {
        Child c1 = new Child("1", 2);
        Child c2 = new Child("2", 3);
        Child c3 = new Child("3", 3);
        Child c4 = new Child("4", 4);
        ArrayList<Child> list1 = new ArrayList<Child>();
        list1.add(c1);
        list1.add(c2);

        ArrayList<Child> list2 = new ArrayList<Child>();
        list2.add(c3);
        list2.add(c4);

        Parent p1 = new Parent("P1", list1);
        Parent p2 = new Parent("P2", list2);
        ArrayList<Parent> parentList = new ArrayList<Parent>();
        parentList.add(p1);
        parentList.add(p2);

        System.out.println("---------Before any logic------------");
        System.out.println(parentList);

        // Removing some children
        removeSomeChild(parentList);
        
        System.out.println("---------After removing some child------------");
        System.out.println(parentList);
    }

    private static void removeSomeChild(ArrayList<Parent> parentList) {
        for (Parent parent : parentList) {
            Iterator<Child> itr = parent.getListOfChild().iterator();
            while (itr.hasNext()) {
                if (itr.next().getAge() == 3) {
                    itr.remove();
                }
            }
        }
    }
}

class Parent {
    String           name;
    ArrayList<Child> listOfChild;

    Parent(String name, ArrayList<Child> childList) {
        this.name = name;
        this.listOfChild = childList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Child> getListOfChild() {
        return listOfChild;
    }

    public void setListOfChild(ArrayList<Child> listOfChild) {
        this.listOfChild = listOfChild;
    }

    public String toString() {
        return "Parent Details as : " + "Name : " + this.name + this.listOfChild;
    }
}

class Child {
    String childName;
    int    age;

    Child(String childName, int age) {
        this.childName = childName;
        this.age = age;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "Child Details as : " + "Name : " + this.childName + " Age : " + this.age;
    }
}