package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Example {

    private Boolean bool;
    private int number;
    private String[] arrrrr;
    private List<Integer> liczby;

    public Example() {
        bool = true;
        number = 2;
        arrrrr = new String[]{"a", "b", "c"};
        liczby = new ArrayList<Integer>();
        liczby.add(1);
        liczby.add(2);
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String[] getArrrrr() {
        return arrrrr;
    }

    public void setArrrrr(String[] arrrrr) {
        this.arrrrr = arrrrr;
    }

    public List<Integer> getLiczby() {
        return liczby;
    }

    public void setLiczby(List<Integer> liczby) {
        this.liczby = liczby;
    }
}
