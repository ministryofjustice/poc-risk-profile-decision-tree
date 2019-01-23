package org.moj.cattool.datasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.moj.cattool.exceptions.MalformedHeader;

public class Pras extends DataSource{

    private final int nomisIDIndex = 11;
    private final String nomisHeaderName = "NOMIS No.";

    @Override
    @JsonProperty("NOMIS No.")
    public String getNomisID() {
        return super.getNomisID();
    }

    @Override
    public void setValues(String [] row){
        this.setNomisID(row[nomisIDIndex]);

    }
    @Override
    public void validateHeader(String [] header) throws MalformedHeader {
        if ( ! nomisHeaderName.equals(header[nomisIDIndex]))
            throw new MalformedHeader("Expected " + nomisHeaderName + " Found: " + header[nomisIDIndex]);
    }
}
