package controller;

import model.Database;
import model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ScoreBoardController {
    public ArrayList<User> getSortedUsers() {
        Comparator<User> scoreComparator = (user1, user2) -> user1.getScore().compareTo(user2.getScore());
        Comparator<User> timeComparator = (user1, user2) -> user1.getTime().compareTo(user2.getTime());
        return (ArrayList<User>) Database.getUsers().stream().sorted(scoreComparator.thenComparing(timeComparator).reversed()).collect(Collectors.toList());
    }

    public String getHighRanks() {
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
