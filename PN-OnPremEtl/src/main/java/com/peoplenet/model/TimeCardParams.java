package com.peoplenet.model;

public class TimeCardParams {

    private String client;
    private String groupCode;
    private String ssn;
    private String pped;


    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getPped() {
        return pped;
    }

    public void setPped(String pped) {
        this.pped = pped;
    }

    public TimeCardParams(){

    }

    public TimeCardParams(String client, String groupCode, String ssn, String pped) {
        this.client = client;
        this.groupCode = groupCode;
        this.ssn = ssn;
        this.pped = pped;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeCardParams that = (TimeCardParams) o;

        if (client != null ? !client.equals(that.client) : that.client != null) return false;
        if (groupCode != null ? !groupCode.equals(that.groupCode) : that.groupCode != null) return false;
        if (ssn != null ? !ssn.equals(that.ssn) : that.ssn != null) return false;
        return pped != null ? pped.equals(that.pped) : that.pped == null;
    }

    @Override
    public int hashCode() {
        int result = client != null ? client.hashCode() : 0;
        result = 31 * result + (groupCode != null ? groupCode.hashCode() : 0);
        result = 31 * result + (ssn != null ? ssn.hashCode() : 0);
        result = 31 * result + (pped != null ? pped.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TimeCardParams{" +
                "client='" + client + '\'' +
                ", groupCode='" + groupCode + '\'' +
                ", ssn='" + ssn + '\'' +
                ", pped='" + pped + '\'' +
                '}';
    }
}
