package net.javaguides.todoapp.dao;


import java.sql.SQLException;
import java.util.List;
import net.javaguides.todoapp.model.Todo;

public interface TodoDao {
    void insertTodo(Todo var1) throws SQLException;

    Todo selectTodo(long var1);

    List<Todo> selectAllTodos();

    boolean deleteTodo(int var1) throws SQLException;

    boolean updateTodo(Todo var1) throws SQLException;
}

