package org.moj.cattool.storagelayer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.moj.cattool.datasource.DataSource;
import org.moj.cattool.datasource.OCGM;
import org.moj.cattool.datasource.Pras;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

@JsonPropertyOrder({"nomis_id", "viper_score", "anythingelse"})
class ViperEx extends DataSource {
    @JsonProperty("viper_score")
    private double viperScore;
    @JsonProperty("anythingelse")
    private double anythingelse;


    public double getViperScore() {
        return viperScore;
    }

    public void setViperScore(double viperScore) {
        this.viperScore = viperScore;
    }

    public double getAnythingelse() {
        return anythingelse;
    }

    public void setAnythingelse(double anythingelse) {
        this.anythingelse = anythingelse;
    }

}
public class TestLocalFileSystem {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private String viperContent = "nomis_id,viper_score,anythingelse\n" +
            "1,2,3\n" +
            "2,3,4\n" +
            "5,6,7";

    private String ocgmContent = String.format("Nomis No.,Date of Birth,PNC Number,CRO Number,Ocg Id Number,New " +
            "Establishment,Prison Group,Police Region,Release Date,Release Type,Sentence Length In Days,OCGM: " +
            "Band,Standing Within Ocg,Role: Corrupter,Priority Status,Nationality,Gender,Security Cat," +
            "Date Security Cat " +
            "Review,Date Secirity Cat Changed,Main Offonce,Religion\nAE23,07/10/1900,PNCNUMBER,CRONUMBER,uo,,,,,,," +
            "1a- 5c,\"Principal subject, significant member, peripheral subject \",,,,,,,,,religion");

    private String ocgmContentci = String.format("Date of BIRTH,PNC Number,CRO Number,Ocg Id Number," +
            "New Establishment,PrisOn Group,Police Region,Release Date,Release Type,Sentence Length In Days,OCGM: " +
            "Band,Standing Within Ocg,Role: Corrupter,Priority Status,Nationality,Gender,Security Cat," +
            "Date Security Cat " +
            "Review,Date Secirity Cat Changed,Main Offonce,Religion,NOMIS No.\n07/10/1900,PNCNUMBER,CRONUMBER,uo,,,,,,," +
            "1a- 5c,\"Principal subject, significant member, peripheral subject \",,,,,,,,,religion,AE23");

    private String prasContent = "CT / SOC,STATUS,REMOVED AS A RESULT OF REGIONAL ASSESSMENT AT PROVISIONAL STAGE,\"Q6 PI  ?\n" +
            "(2018)\",\"A PI In Previous Cycles ?\n" +
            "(1-5)\",\"NOMINATIONS\n" +
            "Yellow = NCA,\n" +
            "Blue = Regional - SOC]\",SURNAME,FORENAME,MIDDLENAME,DOB,PNCID,NOMIS No.,BOOKING NUMBER,\"ESTABLISHMENT \n" +
            "(As of 3rd Dec)\",REGION,MAIN OFFENCE,OTHER OFFENCES,\"OCG NOMINAL ?\n" +
            "\",NUMBER OF OCG GROUPS NOMINAL IS IN,\"BANDING OF OCG / STANDING WITHIN GROUP - \n" +
            "[Most significant if aligned to more than 1] \n" +
            "\",\"ON NCA SOI LIST\n" +
            "(Y/N)\",EARLIEST RELEASE DATE,\"HMPS RELEASE DATE\n" +
            "(As of 3rd Dec)\",\"IEP STATUS\n" +
            "(As of 3rd Dec)\",\"SECURITY CATEGORY\n" +
            "(As of 3rd Dec)\",\"Job if known\n" +
            "(As provided by Regions)\",PNC FIREARMS MARKER,FIREARMS DETAILS,Primary NCA Contact,Confiscation order" +
            " balance outstanding (as of April 2018),Confiscation order total amount outstanding (as of April 2018)," +
            "\"NPIPs \n" +
            "(Red = New NPIP References)\",Comment\n" +
            "1,O,,,,,SURNAME,,iddle,,,AE321,,,,,,,,,,,,,,,,,,,,,\n" +
            ",,,,,,,,,,,POI,FRT,,,,,,,,,,,,,,,,,,,,\n" +
            ",,,DSA,,,ASD,,,,,CLO,,,,,,,,,,,,,,,,,,,,,";

    @Test
    public void testCsvLocalFileSystem() throws IOException {

        File tempFile = temporaryFolder.newFile("viperEx.csv");
        FileUtils.writeStringToFile(tempFile, viperContent);
        HashMap<String, DataSource> viperEx = null;

        LocalFileSystem c = new LocalFileSystem(tempFile.getAbsolutePath());
        try {
            viperEx =  c.readFile(ViperEx.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Test equality
        assertEquals(viperEx.get("1").getNomisID(), "1");
        ViperEx v = (ViperEx) viperEx.get("1");
        assertEquals(v.getViperScore(), 2, 0);
        assertEquals(v.getAnythingelse(), 3, 0);

        v = (ViperEx) viperEx.get("2");
        assertEquals(v.getViperScore(), 3, 0);
        assertEquals(v.getAnythingelse(), 4, 0);

        v = (ViperEx) viperEx.get("5");
        assertEquals(v.getViperScore(), 6, 0);
        assertEquals(v.getAnythingelse(), 7, 0);

        assertEquals(viperEx.size(), 3);

    }

    @Test
    public void testCsvLocalFileSystemOCGM() throws IOException {

        File tempFile = temporaryFolder.newFile("ocgmEx.csv");
        FileUtils.writeStringToFile(tempFile, ocgmContent);
        HashMap<String, DataSource> ocgmEx = null;
        LocalFileSystem c = new LocalFileSystem(tempFile.getAbsolutePath());
        ocgmEx =  c.readFile(OCGM.class);
        assertEquals(ocgmEx.size(), 1);

        OCGM obj = (OCGM) ocgmEx.get("AE23");
        assertEquals(obj.getNomisID(), "AE23");
        assertEquals(obj.getPNCNumber(), "PNCNUMBER");
        assertEquals(obj.getCRONumber(), "CRONUMBER");
        assertEquals(obj.getOCGIDNumber(), "uo");
        assertEquals(obj.getOCGMBand(), "1a- 5c");
        assertEquals(obj.getGroup(), "Principal subject, significant member, peripheral subject ");
        assertEquals(obj.getDateSecurityCatReview(), "");
        assertEquals(obj.getGender(), "");
        assertEquals(obj.getReligion(), "religion");
    }

    @Test
    public void testCsvLocalFileSystemOCGMCaseSensitiveAndDifferentOrderColumns() throws IOException {

        File tempFile = temporaryFolder.newFile("ocgmEx.csv");
        FileUtils.writeStringToFile(tempFile, ocgmContentci);
        HashMap<String, DataSource> ocgmEx;
        LocalFileSystem c = new LocalFileSystem(tempFile.getAbsolutePath());
        ocgmEx =  c.readFile(OCGM.class);
        assertEquals(ocgmEx.size(), 1);

        OCGM obj = (OCGM) ocgmEx.get("AE23");
        assertEquals(obj.getNomisID(), "AE23");
        assertEquals(obj.getPNCNumber(), "PNCNUMBER");
        assertEquals(obj.getCRONumber(), "CRONUMBER");
        assertEquals(obj.getOCGIDNumber(), "uo");
        assertEquals(obj.getOCGMBand(), "1a- 5c");
        assertEquals(obj.getGroup(), "Principal subject, significant member, peripheral subject ");
        assertEquals(obj.getDateSecurityCatReview(), "");
        assertEquals(obj.getGender(), "");
        assertEquals(obj.getReligion(), "religion");
    }

    @Test
    public void testCSVLocalFileSystemPras() throws Exception {
        File tempFile = temporaryFolder.newFile("pras.csv");
        FileUtils.writeStringToFile(tempFile, prasContent);
        HashMap<String, DataSource> prasEx = null;

        LocalFileSystem c = new LocalFileSystem(tempFile.getAbsolutePath());
        prasEx =  c.readSparseFile(Pras.class);


        // Test equality
        assertEquals(prasEx.get("AE321").getNomisID(), "AE321");
        assertEquals(prasEx.get("POI").getNomisID(), "POI");
        assertEquals(prasEx.get("CLO").getNomisID(), "CLO");

        assertEquals(prasEx.size(), 3);


    }

}
