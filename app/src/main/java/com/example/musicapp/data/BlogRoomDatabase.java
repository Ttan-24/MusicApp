package com.example.musicapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.musicapp.MainActivity3;

@Database(entities = {Country.class}, version = 1)
public abstract class BlogRoomDatabase extends RoomDatabase {

    public abstract BlogDao blogDao(); // provides an abstract method that returns an instance of that interface. It's abstract because this method
    // will never be called directly. Instead, there will be some generated code that's created by the Room database in the background and that's the
    // version of the method that you'll be calling

    private static volatile BlogRoomDatabase INSTANCE;  // it can only be referenced from main memory

    public static BlogRoomDatabase getDatabase(final Context context) {    // to create the database object - need context
        if (INSTANCE == null) {                                            // check whether the instance is null
            synchronized (BlogRoomDatabase.class) {                        // access is synchronised so that we will have no race conditions between different
                                                                           // different areas of the application
                if (INSTANCE == null) {                                    // check again if the instance is still nul and then create the database reference
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BlogRoomDatabase.class, "blog_database")
                            .allowMainThreadQueries()
                            .build();
                            ////ADDED
                            //INSTANCE.populate();
                }
            }
        }
        return INSTANCE;
    }

   ////ADDED
   //private void populate() {
   //    if (blogDao().count() == 0) {
   //        runInTransaction(new Runnable() {
   //            @Override
   //            public void run() {
   //                BlogDao blogDao = blogDao();
   //                blogDao.insert(new Country("AE","US", "http", 2, 2,false));
   //            }
   //        });
   //    }
   //}

}
