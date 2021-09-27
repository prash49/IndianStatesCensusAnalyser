package com.bridgelabz.indianstatescensusanalyser;


import com.bridgelabz.indianstatescensusanalyser.model.CensusAnalyser;
import com.bridgelabz.indianstatescensusanalyser.service.CensusAnalyserException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static com.bridgelabz.indianstatescensusanalyser.service.CensusAnalyserException.ExceptionType.CENSUS_INCORRECT_FILE_FORMAT;

public class CensusAnalyserTest {
    private String INIDAN_CENSUS_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private String INIDAN_CENSUS_WRONGCSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private String INDIAN_CENSUS_INCORRECT_FILE_FORMAT = "./src/main/resources/IndiaStateCensusData.txt";

    //TC1.1
    @Test
    public void givenIndianCensusCSVFile_WhenLoad_ShouldReturnCorrectRecords() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        int count = censusAnalyser.loadIndiaCensusData(INIDAN_CENSUS_CSV_FILE_PATH);
        Assert.assertEquals(29, count);
    }

    // TC1.2
    @Test
    public void givenIndianCensusWrongCSVFile_WhenLoad_ShouldReturnException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INIDAN_CENSUS_WRONGCSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(e.type, CensusAnalyserException.ExceptionType.CENSUS_FILE_INCORRECT);
            e.printStackTrace();
        }
    }

    //TC1.3
    @Test
    public void givenIndianCensusCSVFile_WhenCorrectPathButWrongFileFormat_ShouldThrowException() {

        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INDIAN_CENSUS_INCORRECT_FILE_FORMAT);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CENSUS_INCORRECT_FILE_FORMAT, e.type);
        }
    }

    // TC1.4

}

