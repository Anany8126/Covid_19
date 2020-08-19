package com.example.covid_19;

public class IndiamModal {
    private String Sname,confirmed,recovered,deaths;

    public IndiamModal() {
    }

    public IndiamModal(String sname, String confirmed, String recovered,  String deaths) {
        this.Sname = sname;
        this.confirmed = confirmed;
        this.recovered = recovered;
        this.deaths = deaths;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getConfirmed()
    {
        return confirmed;
    }

    public void setConfirmed(String confirmed)
    {
        this.confirmed = confirmed;
    }

    public String getRecovered()
    {
        return recovered;
    }

    public void setRecovered(String recovered)
    {
        this.recovered = recovered;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }
}
