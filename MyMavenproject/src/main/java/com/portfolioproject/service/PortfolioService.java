package com.portfolioproject.service;

import com.portfolioproject.model.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PortfolioService {

    private final Map<String, User> users = new LinkedHashMap<>();

    public void addUser(User user) {
        if (user == null || user.getUserid() == null
                || user.getUserid().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "User and user ID must not be empty.");
        }
        users.put(user.getUserid(), user);
    }

    public boolean userExists(String userId) {
        return userId != null && users.containsKey(userId);
    }

    public User getUser(String userId) {
        return userId == null ? null : users.get(userId);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public void loadUsers(Collection<User> loadedUsers) {
        if (loadedUsers == null) {
            throw new IllegalArgumentException("Users must not be null.");
        }

        Map<String, User> restoredUsers = new LinkedHashMap<>();
        for (User user : loadedUsers) {
            if (user == null || user.getUserid() == null
                    || user.getUserid().trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Loaded users must have a user ID.");
            }
            restoredUsers.put(user.getUserid(), user);
        }
        users.clear();
        users.putAll(restoredUsers);
    }
}
