package com.example.elena.moscowinfo;

import android.content.Context;

import com.example.elena.moscowinfo.model.Category;
import com.example.elena.moscowinfo.database.DatabaseCategorySource;
import com.example.elena.moscowinfo.model.fake.FakeCategorySource;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();


        DatabaseCategorySource source = new DatabaseCategorySource(appContext);

        FakeCategorySource fakeCategorySource = new FakeCategorySource();
        Category cat1 = fakeCategorySource.onPosition(0);
        Category cat2 = fakeCategorySource.onPosition(3);
        Category cat3 = fakeCategorySource.onPosition(5);
        List<Category> categories = new ArrayList<>();
        categories.add(cat1);
        categories.add(cat2);
        categories.add(cat3);


        source.addCategories(categories);
        assertNotNull(source);
        assertTrue(source.size() > 0);
        assertEquals(source.onPosition(1).text(), "Fake Category");

        assertEquals("com.example.elena.moscowinfo", appContext.getPackageName());
    }
}
