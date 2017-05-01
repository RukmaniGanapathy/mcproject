package edu.project.mobilecomputing.mc_project.service;

import java.util.List;

import edu.project.mobilecomputing.mc_project.model.User;

/**
 * Created by Rukmani on 3/30/17.
 */
public abstract interface ApplicationService {


    public abstract List<User> getUsers();

    public abstract List<User> getFriends(String userId);

    public abstract void addFriend(User user1, User user2);

    public abstract void removeFriend(User user1, User user2);

    public abstract String getGroceryList(String userId);

    public abstract void deleteUserAccount(String userId);

    public abstract String generateMessages(int useCase, String user);

    //write the logic to send a particular message to a user
    public abstract void sendMessage(String userId, int useCase);

    public abstract void respondToNotification(String userId, int useCase);

    public abstract boolean areFriendsOnSplitwise(User currentUser, User friend);

    public abstract void makeFriendsOnSplitwise(User currentUser, User friend);

    public abstract void createTransactionOnSplitwise(User buyer, User receiver, String caption, Float amount);

    public abstract User getCurrentUser();

    public abstract User getMyUser();

    public abstract void setMyUser(User myUser);

    //TODO add method signatures for accelerometer and Google maps related functionalities





}
