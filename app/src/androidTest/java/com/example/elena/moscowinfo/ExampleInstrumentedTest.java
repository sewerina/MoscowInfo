package com.example.elena.moscowinfo;

import android.content.Context;

import com.example.elena.moscowinfo.database.AppDatabase;
import com.example.elena.moscowinfo.database.CategoryDao;
import com.example.elena.moscowinfo.database.CategoryEntity;
import com.example.elena.moscowinfo.database.FavouriteCategorySource;
import com.example.elena.moscowinfo.database.FavouriteDao;
import com.example.elena.moscowinfo.database.FavouriteEntity;
import com.example.elena.moscowinfo.model.Category;
import com.example.elena.moscowinfo.database.DatabaseCategorySource;
import com.example.elena.moscowinfo.model.fake.FakeCategorySource;

import androidx.room.Room;
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
    Context appContext = InstrumentationRegistry.getTargetContext();

    @Test
    public void useAppContext() {
        // Context of the app under test.


        DatabaseCategorySource source = new DatabaseCategorySource(appContext);

        FakeCategorySource fakeCategorySource = new FakeCategorySource();
//        Category cat1 = fakeCategorySource.onPosition(0);
//        Category cat2 = fakeCategorySource.onPosition(3);
//        Category cat3 = fakeCategorySource.onPosition(5);
//        List<Category> categories = new ArrayList<>();
//        categories.add(cat1);
//        categories.add(cat2);
//        categories.add(cat3);


        source.addCategories(fakeCategorySource);
        assertNotNull(source);
        assertTrue(source.size() > 0);
        assertEquals(source.onPosition(1).text(), "Fake Category");
        assertTrue(source.onPosition(6).id() > 0);

        assertEquals("com.example.elena.moscowinfo", appContext.getPackageName());
    }

    @Test
    public void receiveFavourites() {
        AppDatabase appDatabase = Room.inMemoryDatabaseBuilder(appContext,
                AppDatabase.class)
                .enableMultiInstanceInvalidation()
                .allowMainThreadQueries()
                .build();
        FavouriteDao favouriteDao = appDatabase.favouriteDao();

        CategoryDao categoryDao = appDatabase.categoryDao();

        CategoryEntity e1 = new CategoryEntity();
        e1.mId = 1;
        e1.mCaption = "Fav Category";

        CategoryEntity e2 = new CategoryEntity();
        e2.mId = 2;
        e2.mCaption = "Category";

        CategoryEntity e3 = new CategoryEntity();
        e3.mId = 3;
        e3.mCaption = "Fav Category";

        List<CategoryEntity> categoryEntities = new ArrayList<>();
        categoryEntities.add(e1);
        categoryEntities.add(e2);
        categoryEntities.add(e3);

        categoryDao.insertAllCategories(categoryEntities);

        for (CategoryEntity categoryEntity : categoryDao.getAllCategories()) {
            FavouriteEntity favouriteEntity = new FavouriteEntity();
            favouriteEntity.mCategoryId = categoryEntity.mId;
            favouriteDao.insertFavourite(favouriteEntity);
        }

        assertEquals(3, favouriteDao.getAllFavourites().size());
        assertEquals(3, favouriteDao.getFavouriteCategories().size());

    }
}
