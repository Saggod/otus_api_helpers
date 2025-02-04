package tests;

import dto.CoursesDto;
import dto.GradeDto;
import dto.UsesrDto;
import helpers.RestApiHelpers;
import org.junit.jupiter.api.Test;
import servicesApi.ApiResp;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestApiTests extends RestApiHelpers {

    private ApiResp apiResp = new ApiResp();

    @Test
    public void getGradeTest() {
        GradeDto gradeDto = apiResp.getGrade(1)
                .statusCode(200)
                .extract()
                .as(GradeDto.class);

        assertEquals("Test user", gradeDto.getName());
        assertEquals(78, gradeDto.getScore());
    }

    @Test
    public void getCoursesTest() {
       List<CoursesDto> coursesDto = apiResp.getCourses()
               .statusCode(200)
               .extract()
               .body()
               .jsonPath()
               .getList("", CoursesDto.class);


        CoursesDto expectedFirstCourse = new CoursesDto();
        expectedFirstCourse.setName("QA java");
        expectedFirstCourse.setPrice(15000);

        CoursesDto expectedSecondCourse = new CoursesDto();
        expectedSecondCourse.setName("Java");
        expectedSecondCourse.setPrice(12000);

        assertThat(coursesDto, contains(
                samePropertyValuesAs(expectedFirstCourse),
                samePropertyValuesAs(expectedSecondCourse)
        ));
    }

    @Test
    public void  getUsersTest() {
        List<UsesrDto> usesrDtoList = apiResp.getUsers()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList("", UsesrDto.class);

        assertThat(usesrDtoList, is(notNullValue()));
        assertThat(usesrDtoList, hasSize(1));

        UsesrDto expected = new UsesrDto();
        expected.setName("Test user");
        expected.setEmail("test@test.test");
        expected.setCourse("QA");
        expected.setAge(23);

        assertThat(usesrDtoList, contains(
                samePropertyValuesAs(expected)));
    }
}
