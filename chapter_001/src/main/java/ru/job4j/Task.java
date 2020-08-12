package ru.job4j;

import java.util.*;

public class Task {

    static class User {
        String name;
        Set<String> emails;

        public User(String name, Set<String> emails) {
            this.name = name;
            this.emails = emails;
        }

        public Set<String> getEmails() {
            return emails;
        }
    }

    public static Map<User, Set<String>> convert(List<User> users) {
        Map<String, Set<User>> emails;
        Map<User, Set<String>> realUsers;

        emails = new HashMap<>();
        realUsers = new HashMap<>();

        for (User user: users) {
            for (String email: user.getEmails()) {
                Set<User> emailUsers = emails.get(email);
                if (emailUsers == null) {
                    emailUsers = new HashSet<>();
                }
                emailUsers.add(user);
                emails.put(email, emailUsers);
            }
        }

        List<User> temp = new ArrayList<>(users);
        Set<User> userGroup;
        while (temp.size() > 0) {
            userGroup = goDeeper(temp.get(0), emails, new HashSet<>(), new HashSet<>());
            Set<String> userEmails = new HashSet<>();
            for (User user: userGroup) {
                userEmails.addAll(user.getEmails());
            }
            realUsers.put(temp.get(0), userEmails);
            temp.removeAll(userGroup);
        }
        return realUsers;
    }

    private static Set<User> goDeeper(User user, Map<String, Set<User>> emails, Set<User> gatheredUsers, Set<String> gatheredEmails) {
        gatheredUsers.add(user);
        for (String email: user.getEmails()) {
            if (!gatheredEmails.contains(email)) {
                for (User subUser: emails.get(email)) {
                    if (!gatheredUsers.contains(subUser)) {
                        gatheredEmails.add(email);
                        return goDeeper(subUser, emails, gatheredUsers, gatheredEmails);
                    }
                }
            }
        }
        return gatheredUsers;
    }
}