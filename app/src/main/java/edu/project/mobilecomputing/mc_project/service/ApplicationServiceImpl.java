package edu.project.mobilecomputing.mc_project.service;

import java.util.List;

import edu.project.mobilecomputing.mc_project.model.User;

/**
 * Created by Rukmani on 4/6/17.
 */
public class ApplicationServiceImpl implements ApplicationService{
    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public List<User> getFriends(String userId) {
        return null;
    }

    @Override
    public void addFriend(User user1, User user2) {

    }

    @Override
    public void removeFriend(User user1, User user2) {

    }

    @Override
    public String getGroceryList(String userId) {
        return null;
    }

    @Override
    public void deleteUserAccount(String userId) {

    }

    @Override
    public String generateMessages(int useCase) {
        return null;
    }

    @Override
    public void sendMessage(String userId, int useCase) {

    }

    @Override
    public void respondToNotification(String userId, int useCase) {

    }

    @Override
    public boolean areFriendsOnSplitwise(User currentUser, User friend) {
        return false;
    }

    @Override
    public void makeFriendsOnSplitwise(User currentUser, User friend) {

    }

    @Override
    public void createTransactionOnSplitwise(User buyer, User receiver, String caption, Float amount) {

    }
}
