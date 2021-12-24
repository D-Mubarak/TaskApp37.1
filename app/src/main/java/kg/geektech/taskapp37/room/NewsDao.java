package kg.geektech.taskapp37.room;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import kg.geektech.taskapp37.models.News;

@Dao
public interface     NewsDao {

   @Query("SELECT * FROM news order by createdAt DESC")
   List<News> getAll();

   @Query("SELECT * FROM news order by title ASC")
   List<News> getAllSortedTitle();

   @Insert
   void insert(News news);

   @Update
   void update(News news);

   @Delete
   void delete(News news);



}
