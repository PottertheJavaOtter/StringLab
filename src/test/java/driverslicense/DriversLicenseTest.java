package driverslicense;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;


public class DriversLicenseTest {

    DriversLicense testLicense;

    String name = "Mike Jones";
    String address = "123 Atl drive";
    String eyeColor = "Brown";
    Date expectedDOB = new Date(); // 3 times
    Date expectedIssueDate = expectedDOB;
    Date expectedExpirationDate = expectedDOB;
    String licenseNum = "007";
    String issuingState = "TX";
    String trump = "Trump";
    char male = 'M';
    boolean federallyCompliantStatus = false;
    char licenseClassification = 'C';

    @Before
    public void setUp() throws Exception {
        testLicense = new DriversLicense();

        testLicense.setName(name);
        testLicense.setAddress(address);
        testLicense.setEyeColor(eyeColor);
        testLicense.setDateOfBirth(expectedDOB);
        testLicense.setIssueDate(expectedIssueDate);
        testLicense.setExpirationDate(expectedExpirationDate);
        testLicense.setLicenseNumber(licenseNum);
        testLicense.setIssuingState(issuingState);
        testLicense.setEndorsements(trump);
        testLicense.setSex(male);
        testLicense.setFederallyCompliant(federallyCompliantStatus);
        testLicense.setLicenseClassification(licenseClassification);
    }

    @Test
    public void testSerializeToCSV() throws Exception {

        String actualCSVResult = testLicense.serializeToCSV();

        String expectedCSVResult = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%c,%b,%c",
                name, address, eyeColor, expectedDOB,expectedIssueDate, expectedExpirationDate,
                licenseNum, issuingState, trump, male, federallyCompliantStatus, licenseClassification);

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
        String csv = testLicense.serializeToCSV();
        ArrayList<DriversLicense> licenseList = testLicense.deserializeFromCSV(csv);
        DriversLicense actualLicense = licenseList.get(0);
        assertEquals(name, actualLicense.getName());
        assertEquals(address, actualLicense.getAddress());
        assertEquals(eyeColor, actualLicense.getEyeColor());
        assertEquals(licenseNum, actualLicense.getLicenseNumber());
        assertEquals(issuingState, actualLicense.getIssuingState());
        assertEquals(trump, actualLicense.getEndorsements());
        assertEquals(male, actualLicense.getSex());
        assertEquals(federallyCompliantStatus, actualLicense.isFederallyCompliant());
        assertEquals(licenseClassification, actualLicense.getLicenseClassification());

    }
}