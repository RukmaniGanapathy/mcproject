package edu.project.mobilecomputing.mc_project.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import edu.project.mobilecomputing.mc_project.model.Grocery;
import edu.project.mobilecomputing.mc_project.model.User;

/**
 * Created by Rukmani on 4/6/17.
 */
public class ApplicationServiceImpl implements ApplicationService{
    public static User myUser = null;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();


    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getMyUser() {
        return myUser;
    }

    @Override
    public void setMyUser(User myUser) {
        ApplicationServiceImpl.myUser = myUser;
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
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        final Grocery[] gList = new Grocery[1];
        //TODO:
        userId = getCurrentUser().getUserId();


        databaseReference.child("grocery").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children){
                    System.out.println("hello from listener");
                    gList[0] = child.getValue(Grocery.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

        return "ayse";
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

    @Override
    public User getCurrentUser() {

        FirebaseUser fbUser = firebaseAuth.getCurrentUser();
        User myUser = new User();
        myUser.setEmail(fbUser.getEmail());
        myUser.setUserId(fbUser.getUid());

        return myUser;
    }
}
