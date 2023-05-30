package efs.task.unittests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlannerTest {
    Planner planner;

    @BeforeEach
    void setup(){
        planner = new Planner();
    }

    @ParameterizedTest
    @EnumSource(ActivityLevel.class)
    void Should_ReturnCorrectCaloriesDemand_When_UserIsCorrect(ActivityLevel activityLevel){
        //given
        int calories = TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(activityLevel);
        User user  = TestConstants.TEST_USER;

        //when
        var res = planner.calculateDailyCaloriesDemand(user,activityLevel);


        //then
        assertEquals(res, calories);
    }

    @Test
    void Should_ReturnCorrectDailyIntake_When_UserIsCorrect(){
        //given
        User user = TestConstants.TEST_USER;
        DailyIntake dailyIntake = TestConstants.TEST_USER_DAILY_INTAKE;

        //when
        var res = planner.calculateDailyIntake(user);

        //then
        assertEquals(res.getCalories(), dailyIntake.getCalories());
        assertEquals(res.getCarbohydrate(), dailyIntake.getCarbohydrate());
        assertEquals(res.getFat(), dailyIntake.getFat());
        assertEquals(res.getProtein(), dailyIntake.getProtein());
    }
}
