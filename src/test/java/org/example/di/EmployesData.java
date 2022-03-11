package org.example.di;

import net.thucydides.core.annotations.Steps;

public class EmployesData {

    @Steps(shared=true)
    PersonalData data;

    public void setData(PersonalData data) {
        this.data = data;
    }

    public String getNameOfBestEmployee() {
        return data.getName();
    }

}
