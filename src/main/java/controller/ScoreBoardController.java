package controller;

import model.Database;
import model.User;

import java.util.ArrayList;

public class ScoreBoardController {
    public ArrayList<User> getSortedUsers() {
        Database.getUsers().sort((user1, user2) -> {
            if (user1.getScore() != user2.getScore())
                return user1.getScore() - user2.getScore();
            return (int) (user1.getTime() - user2.getTime());
        });
        return Database.getUsers();
    }

    public String getHighRanks(String level) {
        ArrayList<User> sortedUsers = getSortedUsers();

        int index = sortedUsers.size() - 1;
        int rank = 1;
        String result = "";
        while (rank <= 10 && index >= 0) {
            result += rank + "- Username: " + sortedUsers.get(index).getName() +
                    " | Score: " + sortedUsers.get(index).getScore() +
                    " | Time: " + sortedUsers.get(index).getTime() + ",";
            index--;
            rank++;
        }
        return result;
    }
}
