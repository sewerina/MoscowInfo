package com.example.elena.moscowinfo;

import com.example.elena.moscowinfo.model.Category;
import com.example.elena.moscowinfo.model.FakeCategorySource;
import com.example.elena.moscowinfo.model.WebCategorySource;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CategorySourceTest {
    @Test
    public void webCategoryTest() {
        WebCategorySource source = new WebCategorySource();
        Category category = source.onPosition(0);

        assertTrue(source.size() > 0);

        assertEquals(category.text(), "Дома культуры и клубы");
        assertNotNull(category.text());
        assertTrue(!category.text().isEmpty());


    }

    @Test
    public void fakeCategoryTest() {
        FakeCategorySource source = new FakeCategorySource();
        Category category = source.onPosition(5);

        assertTrue(source.size() > 0);

        assertEquals(category.text(), "Fake Category");
        assertNotNull(category.text());
        assertFalse(category.text().isEmpty());
    }
}