package org.moj.cattool.datasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.moj.cattool.exceptions.MalformedHeader;

abstract public class DataSource {

    private String nomisID;
    @JsonProperty("nomis_id")

    public String getNomisID() {
        return nomisID;
    }

    public void setNomisID(String nomisID) {
        this.nomisID = nomisID;
    }

    public void setValues(String[] row) {
    }

    public void validateHeader(String[] row) throws MalformedHeader {
    }
}
