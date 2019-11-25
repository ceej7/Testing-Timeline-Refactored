package com.ceej.model;

import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


// refactoring: data testing not hardcoded
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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

    @Test
    @DisplayName("设置时间为now")
    public void set_article_to_now(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");
        article.setTimeStamp(now.format(df).toString());
        assertEquals("刚刚", article.getTimeStamp());
    }

    @Test
    @DisplayName("设置时间为5分钟前")
    public void set_article_to_five_min_before(){
        LocalDateTime bf = LocalDateTime.now().minusMinutes(5);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");
        article.setTimeStamp(bf.format(df).toString());
        assertEquals("5分钟前", article.getTimeStamp());
    }

    @Test
    @DisplayName("设置时间为5小时前")
    public void set_article_to_five_hours_before(){
        LocalDateTime bf = LocalDateTime.now().minusHours(5);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");
        article.setTimeStamp(bf.format(df).toString());
        assertEquals("5小时前", article.getTimeStamp());
    }

    @Test
    @DisplayName("设置时间为一天前")
    public void set_article_to_one_day_before(){
        LocalDateTime bf = LocalDateTime.now().minusDays(1);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");
        article.setTimeStamp(bf.format(df).toString());
        assertEquals("昨天 "+bf.format(DateTimeFormatter.ofPattern("HH:mm")).toString(), article.getTimeStamp());
    }

    @Test
    @DisplayName("设置时间为一个月前")
    public void set_article_to_one_month_before(){
        LocalDateTime bf = LocalDateTime.now().minusMonths(1);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");
        article.setTimeStamp(bf.format(df).toString());
        assertEquals(bf.format(DateTimeFormatter.ofPattern("MM月dd日HH时")).toString(), article.getTimeStamp());
    }

    @Test
    @DisplayName("设置时间为一年前")
    public void set_article_to_one_year_before(){
        LocalDateTime bf = LocalDateTime.now().minusYears(1);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");
        article.setTimeStamp(bf.format(df).toString());
        assertEquals(bf.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")).toString(), article.getTimeStamp());
    }
}