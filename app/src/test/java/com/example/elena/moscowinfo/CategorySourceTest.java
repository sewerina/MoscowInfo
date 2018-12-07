package com.example.elena.moscowinfo;

import com.example.elena.moscowinfo.model.Category;
import com.example.elena.moscowinfo.model.fake.FakeCategorySource;
import com.example.elena.moscowinfo.web.WebCategory;
import com.example.elena.moscowinfo.web.WebCategoryDetails;
import com.example.elena.moscowinfo.web.WebCategorySource;
import com.example.elena.moscowinfo.web.Dataset;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CategorySourceTest {
    @Test
    public void webCategorySourceTest() {
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

    @Test
    public void webCategoryTest() {
        Dataset dataset = new Dataset();
        dataset.Caption = "TestCaption";
        dataset.Id = 456;

        WebCategory category = new WebCategory(dataset);

        assertEquals(category.text(), "TestCaption");
        assertEquals(category.image(), "https://apidata.mos.ru/v1/datasets/456/image?api_key=57e3d14c8a573455a02dae758bb975dc");
    }

    @Test
    public void webCategoryDetails() {
        WebCategoryDetails webCategoryDetails = new WebCategoryDetails(517);

        assertNotNull(webCategoryDetails.department());
        assertNotNull(webCategoryDetails.description());
        assertNotNull(webCategoryDetails.fullDescription());

        assertEquals(webCategoryDetails.department(), "Департамент здравоохранения города Москвы");
        assertEquals(webCategoryDetails.description(), "Больницы взрослые, распо…х границ города Москвы.");
    }
}