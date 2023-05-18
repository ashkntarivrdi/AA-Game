package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static ArrayList<User> users = new ArrayList<>();
    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Database.currentUser = currentUser;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        Database.users = users;
    }

    public static void addUsers(User user) {
        users.add(user);
        saveUsers();
    }

    public static void loadUsers() {
        try {
            String json = new String(Files.readAllBytes(Paths.get("./src/main/resources/Database/UserDatabase.json")));
            ArrayList<User> savedUsers;
            savedUsers = new Gson().fromJson(json, new TypeToken<List<User>>() {
            }.getType());
            if(savedUsers != null) setUsers(savedUsers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveUsers() {
        try {
            FileWriter fileWriter = new FileWriter("./src/main/resources/Database/UserDatabase.json");
            fileWriter.write(new Gson().toJson(getUsers()));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if(user.getName().equals(username))
                return user;
        }
        return null;
    }


}
