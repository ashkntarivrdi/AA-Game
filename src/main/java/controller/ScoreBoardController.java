package controller;

import model.Database;
import model.User;

import java.util.ArrayList;

public class ScoreBoardController {
    public ArrayList<User> getSortedUsersEasy() {
        Database.getUsers().sort((user1, user2) -> {
            if (user1.getEasyScore() != user2.getEasyScore())
                return user1.getEasyScore() - user2.getEasyScore();
            return (int) (user1.getTime() - user2.getTime());
        });
        return Database.getUsers();
    }

    public ArrayList<User> getSortedUsersMedium() {
        Database.getUsers().sort((user1, user2) -> {
            if (user1.getMediumScore() != user2.getMediumScore())
                return user1.getMediumScore() - user2.getMediumScore();
            return (int) (user1.getTime() - user2.getTime());
        });
        return Database.getUsers();
    }

    public ArrayList<User> getSortedUsersHard() {
        Database.getUsers().sort((user1, user2) -> {
            if (user1.getHardScore() != user2.getHardScore())
                return user1.getHardScore() - user2.getHardScore();
            return (int) (user1.getTime() - user2.getTime());
        });
        return Database.getUsers();
    }



    public String getHighRanks(String level) {
        ArrayList<User> sortedUsers;
        if (level.equals("easy")) sortedUsers = getSortedUsersEasy();
        else if (level.equals("hard")) sortedUsers = getSortedUsersHard();
        else sortedUsers = getSortedUsersMedium();

        int index = sortedUsers.size() - 1;
        int rank = 1;
        String result = "";
        while (rank <= 10 && index >= 0) {
            result += rank + "- Username: " + sortedUsers.get(index).getName() +
                    " | Score: " + sortedUsers.get(index).getEasyScore() +
                    " | Time: " + sortedUsers.get(index).getTime() + ",";
            index--;
            rank++;
        }
        return result;
    }
}
