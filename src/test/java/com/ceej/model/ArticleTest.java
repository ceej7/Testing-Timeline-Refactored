package com.ceej.model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


// refactoring: data testing not hardcoded
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArticleTest {
    Article article;

    @BeforeEach
    public void init(){
        article = new Article();
        Locale.setDefault(Locale.US);
    }
    @AfterEach
    public void teardown(){
        article = new Article();
        Locale.setDefault(Locale.getDefault());
    }

    @Order(0)
    @ParameterizedTest
    @MethodSource("case_provider")
    @DisplayName("综合所有时间的scenario")
    public void set_article_to_multi_scenario(int type, int bias, String pattern, String expected_raw){
        LocalDateTime bf;
        switch (type){
            case 0:
                bf =LocalDateTime.now().minusMinutes(bias);
                break;
            case 1:
                bf =LocalDateTime.now().minusHours(bias);
                break;
            case 2:
                bf =LocalDateTime.now().minusDays(bias);
                break;
            case 3:
                bf =LocalDateTime.now().minusMonths(bias);
                break;
            case 4:
                bf =LocalDateTime.now().minusYears(bias);
                break;
            default:
                bf=LocalDateTime.now();
                break;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");
        article.setTimeStamp(bf.format(df).toString());
        if(!expected_raw.equals("")){
            assertEquals(expected_raw, article.getTimeStamp());
        }
        else {
            assertEquals(bf.format(DateTimeFormatter.ofPattern(pattern)).toString(), article.getTimeStamp());
        }
    }

    static Stream<Arguments> case_provider(){
        return Stream.of(
                Arguments.of(0,0,"","刚刚"),
                Arguments.of(0,5,"","5分钟前"),
                Arguments.of(1,5,"","5小时前"),
                Arguments.of(2,1,"昨天 HH:mm",""),
                Arguments.of(3,1,"MM月dd日HH时",""),
                Arguments.of(4,1,"yyyy年MM月dd日","")
        );
    }



    @Order(1)
    @Disabled
    @Test
    @DisplayName("设置时间为now")
    public void set_article_to_now(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");
        article.setTimeStamp(now.format(df).toString());
        assertEquals("刚刚", article.getTimeStamp());
    }

    @Order(2)
    @Disabled
    @Test
    @DisplayName("设置时间为5分钟前")
    public void set_article_to_five_min_before(){
        LocalDateTime bf = LocalDateTime.now().minusMinutes(5);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");
        article.setTimeStamp(bf.format(df).toString());
        assertEquals("5分钟前", article.getTimeStamp());
    }

    @Order(3)
    @Disabled
    @Test
    @DisplayName("设置时间为5小时前")
    public void set_article_to_five_hours_before(){
        LocalDateTime bf = LocalDateTime.now().minusHours(5);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");
        article.setTimeStamp(bf.format(df).toString());
        assertEquals("5小时前", article.getTimeStamp());
    }

    @Order(4)
    @Disabled
    @Test
    @DisplayName("设置时间为一天前")
    public void set_article_to_one_day_before(){
        LocalDateTime bf = LocalDateTime.now().minusDays(1);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");
        article.setTimeStamp(bf.format(df).toString());
        assertEquals("昨天 "+bf.format(DateTimeFormatter.ofPattern("HH:mm")).toString(), article.getTimeStamp());
    }

    @Order(5)
    @Disabled
    @Test
    @DisplayName("设置时间为一个月前")
    public void set_article_to_one_month_before(){
        LocalDateTime bf = LocalDateTime.now().minusMonths(1);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");
        article.setTimeStamp(bf.format(df).toString());
        assertEquals(bf.format(DateTimeFormatter.ofPattern("MM月dd日HH时")).toString(), article.getTimeStamp());
    }

    @Order(6)
    @Disabled
    @Test
    @DisplayName("设置时间为一年前")
    public void set_article_to_one_year_before(){
        LocalDateTime bf = LocalDateTime.now().minusYears(1);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");
        article.setTimeStamp(bf.format(df).toString());
        assertEquals(bf.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")).toString(), article.getTimeStamp());
    }

}