package driverslicense;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DriversLicense {

    private String name, address, licenseNumber, endorsements, issuingState, eyeColor;

    private int height;
    private double weight;
    private char licenseClassification, sex;
    private boolean organDonor, federallyCompliant;
    private Date dateOfBirth, issueDate, expirationDate;

    //private Restriction[] restrictions;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public char getLicenseClassification() {
        return licenseClassification;
    }

    public void setLicenseClassification(char licenseClassification) {
        this.licenseClassification = licenseClassification;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public boolean isOrganDonor() {
        return organDonor;
    }

    public void setOrganDonor(boolean organDonor) {
        this.organDonor = organDonor;
    }

    public boolean isFederallyCompliant() {
        return federallyCompliant;
    }

    public void setFederallyCompliant(boolean federallyCompliant) {
        this.federallyCompliant = federallyCompliant;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getEndorsements() {
        return endorsements;
    }

    public void setEndorsements(String endorsements) {
        this.endorsements = endorsements;
    }

    public String getIssuingState() {
        return issuingState;
    }

    public void setIssuingState(String issuingState) {
        this.issuingState = issuingState;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public boolean getOrganDonor(){
        return organDonor;
    }
    /**
     * (include description of field order here)
     * @return
     */
    public String serializeToCSV(){

        StringBuilder csvBuilder = new StringBuilder();

        csvBuilder.append(name).append(',')
                .append(address).append(',')
                .append(eyeColor).append(',')
                .append(dateOfBirth).append(',')
                .append(issueDate).append(',')
                .append(expirationDate).append(',')
                .append(licenseNumber).append(',')
                .append(issuingState).append(',')
                .append(endorsements).append(',')
                .append(sex).append(',')
                .append(federallyCompliant).append(',')
                .append(licenseClassification).append(',')
                .append(organDonor).append(',')
                .append(height).append(',')
                .append(weight);

        return csvBuilder.toString();
    }
    //String sampleCSV = "NAME\nMin,EYE COLOR,Brown,Blue,DATE OF BIRTH,
    public static String getCSVHeader(){
        return "NAME,ADDRESS,EYE COLOR,DATE OF BIRTH,ISSUE DATE,EXPIRATION DATE," +
                "LICENSE NUMBER,STATE,ENDORSEMENTS,SEX,FEDERAL COMPLIANCE,CLASSIFICATION";
    }

    public static Date convertStringToDate(String dateString){
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String removeHeader(String csv){
        int csvIndex = csv.indexOf("\n");

        if(csvIndex > -1){
            return csv.substring(csvIndex+1, csv.length());
        }
        return csv;
    }

    public static ArrayList<DriversLicense> deserializeFromCSV(String csv){
        String noHeaderCVS = removeHeader(csv);
        ArrayList<DriversLicense> licensesList = new ArrayList<>();
        String[] csvArray = noHeaderCVS.trim().split("\\s*,\\s*");
        int numberOfLicenses = csvArray.length / 12;
        if(numberOfLicenses > 0){
            for(int i = 0; i  < numberOfLicenses; i++){
                DriversLicense license = new DriversLicense();
                license.setName(csvArray[(0+(i*15))]);
                license.setAddress(csvArray[(1+(i*15))]);
                license.setEyeColor(csvArray[(2+(i*15))]);
                license.setDateOfBirth(convertStringToDate(csvArray[(3+(i*15))]));
                license.setIssueDate(convertStringToDate(csvArray[(4+(i*15))]));
                license.setExpirationDate(convertStringToDate(csvArray[(5+(i*15))]));
                license.setLicenseNumber(csvArray[(6+(i*15))]);
                license.setIssuingState(csvArray[(7+(i*15))]);
                license.setEndorsements(csvArray[(8+(i*15))]);
                license.setSex(csvArray[(9+(i*15))].charAt(0));
                license.setFederallyCompliant(Boolean.parseBoolean(csvArray[(10+(i*15))]));
                license.setLicenseClassification(csvArray[(11+(i*15))].charAt(0));
                license.setOrganDonor(Boolean.parseBoolean(csvArray[(12+(i*15))]));
                license.setHeight(Integer.parseInt(csvArray[(13+(i*15))]));
                license.setWeight(Double.parseDouble(csvArray[(14+(i*15))]));
                licensesList.add(license);
            }
            return licensesList;
        }
        for(int i = 0; i < csvArray.length; i++){
            System.out.println(csvArray[i]);
        }
        return null;
    }

    public boolean checkDuplicateLicense(DriversLicense duplicateLicense){
        if((name.equals(duplicateLicense.getName())) &&
                (address.equals(duplicateLicense.getAddress()))&&
                (eyeColor.equals(duplicateLicense.getEyeColor()))&&
                (dateOfBirth.equals(duplicateLicense.getDateOfBirth()))&&
                (issueDate.equals(duplicateLicense.getIssueDate()))&&
                (expirationDate.equals(duplicateLicense.getExpirationDate()))&&
                (licenseNumber.equals(duplicateLicense.getLicenseNumber()))&&
                (issuingState.equals(duplicateLicense.getIssuingState()))&&
                (endorsements.equals(duplicateLicense.getEndorsements()))&&
                (sex == duplicateLicense.getSex())&&
                (federallyCompliant == duplicateLicense.isFederallyCompliant())&&
                (licenseClassification == duplicateLicense.getLicenseClassification())&&
                (organDonor == duplicateLicense.getOrganDonor())&&
                (height == duplicateLicense.getHeight())&&
                (weight == duplicateLicense.getWeight())) {
            return true;
        }
        return false;
    }

    public String toString(){
        String stringValue = "DriversLicense\n" +
                "name='" + name + '\n' +
                "address='" + address + '\n' +
                "eyeColor='" + eyeColor  + '\n' +
                "licenseNumber='" + licenseNumber + '\n' +
                "issuingState='" + issuingState  + '\n' +
                "endorsements='" + endorsements  + '\n' +
                "sex=" + sex  + '\n' +
                "isOrganDonor=" + organDonor  + '\n' +
                "federallyCompliant=" + federallyCompliant+ '\n' +
                "height=" + height  + '\n' +
                "weight=" + weight  + '\n' +
                "licenseClassification=" + licenseClassification;
        return stringValue;
    }

}
