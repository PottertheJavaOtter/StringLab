package driverslicense;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import static org.junit.Assert.*;


public class DriversLicenseTest {

    DriversLicense testLicense;

    String name = "Mike Jones";
    String address = "123 Atl drive";
    String eyeColor = "Brown";
    Date expectedDOB = new Date(); // 3 times
    Date expectedIssueDate = expectedDOB;
    Date expectedExpirationDate = expectedDOB;
    String licenseNumber = "007";
    String issuingState = "TX";
    String endorsements = "Trump";
    char sex = 'M';
    boolean federallyCompliantStatus = false;
    char licenseClassification = 'C';
    boolean organDonor = false;
    int height = 66;
    double weight = 167;

    @Before
    public void setUp() throws Exception {
        testLicense = new DriversLicense();

        testLicense.setName(name);
        testLicense.setAddress(address);
        testLicense.setEyeColor(eyeColor);
        testLicense.setDateOfBirth(expectedDOB);
        testLicense.setIssueDate(expectedIssueDate);
        testLicense.setExpirationDate(expectedExpirationDate);
        testLicense.setLicenseNumber(licenseNumber);
        testLicense.setIssuingState(issuingState);
        testLicense.setEndorsements(endorsements);
        testLicense.setSex(sex);
        testLicense.setFederallyCompliant(federallyCompliantStatus);
        testLicense.setLicenseClassification(licenseClassification);
        testLicense.setOrganDonor(organDonor);
        testLicense.setHeight(height);
        testLicense.setWeight(weight);
    }

    @Test
    public void testSerializeToCSV() throws Exception {

        String actualCSVResult = testLicense.serializeToCSV();

        String expectedCSVResult = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%c,%b,%c,%b,%d,%.1f",
                name, address, eyeColor, expectedDOB,expectedIssueDate, expectedExpirationDate,
                licenseNumber, issuingState, endorsements, sex, federallyCompliantStatus, licenseClassification, organDonor,
                height,weight);
        assertEquals("Actual CSV result did not match expectations.",expectedCSVResult, actualCSVResult);

    }

    @Test
    public void testGetCSVHeader(){
        /*
        order: name, address, eyeColor, expectedDOB,expectedIssueDate, expectedExpirationDate,
                licenseNum, issuingState, trump, male, federallyCompliantStatus, licenseClassification
         */
        String expectedHeader = "NAME,ADDRESS,EYE COLOR,DATE OF BIRTH,ISSUE DATE,EXPIRATION DATE," +
                "LICENSE NUMBER,STATE,ENDORSEMENTS,SEX,FEDERAL COMPLIANCE,CLASSIFICATION";

        String actualHeader = DriversLicense.getCSVHeader();

        assertEquals(expectedHeader, actualHeader);
    }
    @Test
    public void testConvertStringToDate(){
        Date date = testLicense.convertStringToDate("Sun May 22 16:58:47 EDT 2016");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int expectedYear = 2016;
        int expectedMonth = 4;
        int expectedDay = 22;
        int actualYear = cal.get(Calendar.YEAR);
        int actualMonth = cal.get(Calendar.MONTH);
        int actualDay = cal.get(Calendar.DAY_OF_MONTH);
        assertEquals(expectedYear,actualYear);
        assertEquals(expectedMonth,actualMonth);
        assertEquals(expectedDay,actualDay);

    }
    @Test
    public void testDeserializeFromCSV(){
        String csv = testLicense.getCSVHeader()+"\n"+testLicense.serializeToCSV();
        ArrayList<DriversLicense> licenseList = testLicense.deserializeFromCSV(csv);
        DriversLicense actualLicense = licenseList.get(0);
        System.out.println(testLicense.checkDuplicateLicense(actualLicense));
        String expected = "DriversLicense\n" +
                "name='" + name + '\n' +
                "address='" + address + '\n' +
                "eyeColor='" + eyeColor  + '\n' +
                "licenseNumber='" + licenseNumber + '\n' +
                "issuingState='" + issuingState  + '\n' +
                "endorsements='" + endorsements  + '\n' +
                "sex=" + sex  + '\n' +
                "isOrganDonor=" + organDonor  + '\n' +
                "federallyCompliant=" + federallyCompliantStatus+ '\n' +
                "height=" + height  + '\n' +
                "weight=" + weight  + '\n' +
                "licenseClassification=" + licenseClassification;

        String actualValue = testLicense.deserializeFromCSV(testLicense.serializeToCSV()).get(0).toString();
        assertEquals(expected,actualValue);
    }
    @Test
    public void testremoveHeader(){
        String csv = testLicense.getCSVHeader()+"\n"+testLicense.serializeToCSV();
        String expectedCSV = testLicense.removeHeader(csv);
        String actualCSV = testLicense.serializeToCSV();
        assertEquals(expectedCSV,actualCSV);

    }

}