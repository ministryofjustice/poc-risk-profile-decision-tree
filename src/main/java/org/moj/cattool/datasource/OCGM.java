package org.moj.cattool.datasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"NOMIS No.", "Date of Birth", "PNC Number", "CRO Number", "Ocg Id Number",
        "New Establishment", "Prison Group", "Police Region", "Release Date", "Release Type",
        "Sentence Length In Days", "OCGM: Band", "Standing Within Ocg", "Role: Corrupter", "Priority Status",
        "Priority Status", "Nationality", "Gender", "Security Cat", "Date Security Cat Review",
        "Date Secirity Cat Changed", "Main Offonce", "Religion" })

public class OCGM extends DataSource{

    private String ocgmBand;
    private String standingWithinOcg;
    private String dateOfBirth;
    private String PNCNumber;
    private String CRONumber;
    private String OCGIDNumber;
    private String newEstablishment;
    private String prisonGroup;
    private String policeRegion;
    private String releaseDate;
    private String releaseType;
    private String sentenceLengthInDays;
    private String roleCorrupter;
    private String priorityStatus;
    private String nationality;
    private String gender;
    private String securityCat;
    private String dateSecurityCatReview;
    private String dateSecurityDateChanged;
    private String mainOffence;
    private String religion;

    @Override
    @JsonProperty("NOMIS No.")
    public String getNomisID() {
        return super.getNomisID();
    }

    @JsonProperty("OCGM: Band")
    public String getOCGMBand() {
        return ocgmBand;
    }

    public void setOCGMBand(String ocgmBand) {
        this.ocgmBand = ocgmBand;
    }

    @JsonProperty("Standing Within Ocg")
    public String getGroup() {
        return standingWithinOcg;
    }

    public void setGroup(String standingWithinOcg) {
        this.standingWithinOcg = standingWithinOcg;
    }

    @JsonProperty("Date of Birth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @JsonProperty("PNC Number")
    public String getPNCNumber() {
        return PNCNumber;
    }

    public void setPNCNumber(String PNCNumber) {
        this.PNCNumber = PNCNumber;
    }

    @JsonProperty("CRO Number")
    public String getCRONumber() {
        return CRONumber;
    }

    public void setCRONumber(String CRONumber) {
        this.CRONumber = CRONumber;
    }

    @JsonProperty("Ocg Id Number")
    public String getOCGIDNumber() {
        return OCGIDNumber;
    }

    public void setOCGIDNumber(String OCGIDNumber) {
        this.OCGIDNumber = OCGIDNumber;
    }

    @JsonProperty("New Establishment")
    public String getNewEstablishment() {
        return newEstablishment;
    }

    public void setNewEstablishment(String newEstablishment) {
        this.newEstablishment = newEstablishment;
    }

    @JsonProperty("Prison Group")
    public String getPrisonGroup() {
        return prisonGroup;
    }

    public void setPrisonGroup(String prisonGroup) {
        this.prisonGroup = prisonGroup;
    }

    @JsonProperty("Police Region")
    public String getPoliceRegion() {
        return policeRegion;
    }

    public void setPoliceRegion(String policeRegion) {
        this.policeRegion = policeRegion;
    }

    @JsonProperty("Release Date")
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @JsonProperty("Release Type")
    public String getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(String releaseType) {
        this.releaseType = releaseType;
    }

    @JsonProperty("Sentence Length In Days")
    public String getSentenceLengthInDays() {
        return sentenceLengthInDays;
    }

    public void setSentenceLengthInDays(String sentenceLengthInDays) {
        this.sentenceLengthInDays = sentenceLengthInDays;
    }

    @JsonProperty("Role: Corrupter")
    public String getRoleCorrupter() {
        return roleCorrupter;
    }

    public void setRoleCorrupter(String roleCorrupter) {
        this.roleCorrupter = roleCorrupter;
    }

    @JsonProperty("Priority Status")
    public String getPriorityStatus() {
        return priorityStatus;
    }

    public void setPriorityStatus(String priorityStatus) {
        this.priorityStatus = priorityStatus;
    }

    @JsonProperty("Nationality")
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @JsonProperty("Gender")
    public String getGender() {
        return gender;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("Security Cat")
    public String getSecurityCat() {
        return securityCat;
    }

    public void setSecurityCat(String securityCat) {
        this.securityCat = securityCat;
    }

    @JsonProperty("Date Security Cat Review")
    public String getDateSecurityCatReview() {
        return dateSecurityCatReview;
    }

    public void setDateSecurityCatReview(String dateSecurityCatReview) {
        this.dateSecurityCatReview = dateSecurityCatReview;
    }

    @JsonProperty("Date Secirity Cat Changed")
    public String getDateSecurityDateChanged() {
        return dateSecurityDateChanged;
    }

    public void setDateSecurityDateChanged(String dateSecurityDateChanged) {
        this.dateSecurityDateChanged = dateSecurityDateChanged;
    }

    @JsonProperty("Main Offonce")
    public String getMainOffence() {
        return mainOffence;
    }

    public void setMainOffence(String mainOffence) {
        this.mainOffence = mainOffence;
    }
    @JsonProperty("Religion")
    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }
}
