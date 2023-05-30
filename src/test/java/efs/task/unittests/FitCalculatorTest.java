package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FitCalculatorTest {

    //is bmi correct
    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test
    void Should_ReturnFalse_When_DietNotRecommended(){
        //given
        double weight = 69.5;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @Test
    void Should_ThrowIllegalArgumentException_When_WeightIsZero(){
        //given
        double weight = 0;
        double height = 1.72;

        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> FitCalculator.isBMICorrect(weight, height));

    }

    @ParameterizedTest
    @ValueSource(doubles = {1.72, 1.73, 1.74})
    void Should_ReturnTrue_When_WeightIsCorrect(double height){
        //given
        double weight = 76.0;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @ParameterizedTest
    @CsvSource({"35.5, 1.71", "35.5, 1.75", "35.5, 1.76"})
    void Should_ReturnFalse_When_WeightIsNotCorrect(double weight, double height){
        //given

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void Should_ReturnFalse_When_ArgsNotCorrect(double weight, double height){
        //given

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    //finduser

    @Test
    void Should_ReturnUserWithWorstBmi_When_UsersListNotEmpty(){
        //given
        var usersList = TestConstants.TEST_USERS_LIST;

        //when
        User resultUser = FitCalculator.findUserWithTheWorstBMI(usersList);

        //then
        assertEquals(1.79, resultUser.getHeight());
        assertEquals(97.3, resultUser.getWeight());
    }

    @Test
    void Should_ReturnNull_When_UsersListEmpty(){
        //given
        List<User> usersList = new ArrayList<>();

        //when
        User resultUser = FitCalculator.findUserWithTheWorstBMI(usersList);

        //then
        assertNull(resultUser);
    }

    //calculate bmi score
    @Test
    void Should_ReturnCorrectScore_When_UserNotNull(){
        //given
        var usersList = TestConstants.TEST_USERS_LIST;

        //when
        double[] score = FitCalculator.calculateBMIScore(usersList);

        //then
        assertArrayEquals(TestConstants.TEST_USERS_BMI_SCORE, score);
    }


}
