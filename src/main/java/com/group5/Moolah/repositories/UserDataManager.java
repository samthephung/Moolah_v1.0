package com.group5.Moolah.repositories;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

/**
 * A class that includes all operations that may be performed on the
 * UserData collection within the database. The UserData collection
 * may hold many documents each representing a user. Each user will have
 * a unique email that will act as their identifier (primary key).
 * The user will be able to create a new account, update their information
 * or delete their existing account within the UserData Collection.
 * //this is where the actual CRUD operations will be performed -- reaching out directly to the MongoDB from this class
 */
public class UserDataManager {

    /**
     * MongoClient that initiates connection to remote MongoDB atlas.
     */
    private final MongoClient client;

    /**
     * Database that will be operated on.
     */
    private final MongoDatabase database;

    /**
     * Specific collection within the database that will be operated on.
     */
    private final MongoCollection<Document> collection;

    /**
     * Construct a connection to the db and establish usage of the UserData collection
     */
    //setup objects for operations
    public UserDataManager(MongoClient client){
        //instantiate the object
        this.client = client;
        database = client.getDatabase(Constants.DATABASE);
        collection = database.getCollection(Constants.USER_COL);
    }

    /**
     * Verify that no other users within the UserData collection do not have the
     * passed in email.
     *
     * @param email String representing the user's email to be associated with their account
     * @return True if there does not exist an account with the passed in email, else false
     */
    private Boolean checkForEmail(String email){
        //query for the bson that has the specified email
        Document doc = collection.find(eq("email", email)).first();

        //true if the there does not exist an acc with that email
        //false if there already exists an acc with the email
        return doc == null;
    }

    //create new user

    /**
     * Create a new user with a full name, valid email and their own
     * personal password.
     *
     * @param name User's full name to be associated with the account
     * @param email User's email to be associated with account
     * @param password User password
     * @return True if a new user was created successfully, else false
     */
    public Boolean createUser(String name, String email, String password){

        //check no duplicates -- if there is a user that has the same email!
        if(checkForEmail(email)){
            Document user = new Document("_id", new ObjectId())
                    .append("name", name)
                    .append("email", email)
                    .append("pass", LoginManager.passwordHash(password));

            try {
                collection.insertOne(user);
                System.out.println("Successfully added a new user.");

            } catch (MongoException m) {
                System.err.println("Unable to insert data." + m);
            }

        }else{
            System.out.println("Account exists already for the given email. User not added.");
            return false;
        }

        return true;
    }

    public Boolean matchUser(String name, String email, String password) {
        Document doc = collection.find(eq("email", email)).first();
        if (doc != null) {
            if (!(LoginManager.passwordHash(password).equals(doc.getString("pass")))) { //check if password matches
                System.out.println("password did not match");
                return false;
            }
            //if (!(name.equals(doc.getString("name")))) { //check if name matches
            //    System.out.println("name did not match");
            //    return false;
            //}
            System.out.println("Account matches");
            return true;
        }
        return false;
    }

    //update existing user -- find the user based on their email
    /**
     * Update an existing user. The user may update their full name, password,
     * but not their email.
     *
     * @param name User's full name to be associated wtih the acount
     * @param email User's email to be associated with account
     * @param password User password
     * @return True if a new user was updated successfully, else false
     */
    public Boolean updateUser(String email, String name, String password){

        //find the document based on the unique email
        Document doc = collection.find(eq("email", email)).first();

        if(!name.isEmpty()){
            Bson updateName = Updates.combine(
                    Updates.set("name", name));

            try {
                if(doc != null){
                    collection.updateOne(doc, updateName);
                    System.out.println("User has updated their name.");
                }else{
                    return false;
                }
            } catch (MongoException m) {
                System.err.println("Unable to update data because user does not exist." + m);
            }
        }

        if(!password.isEmpty()){
            Bson updatePassword = Updates.combine(
                    Updates.set("pass", password));

            try {
                if(doc != null){
                    collection.updateOne(doc, updatePassword);
                    System.out.println("User has updated their password.");
                }else{
                    return false;
                }
            } catch (MongoException m) {
                System.err.println("Unable to update data because user does not exist." + m);
            }
        }

        return true;
    }

    //delete existing user
    /**
     * Delete the user account with the given email. Once a user is deleted,
     * their document will no longer exist in the UserData collection.
     *
     * @param email User's email to be associated with account
     * @return True if a new user was deleted successfully, else false
     */
    public Boolean deleteUser(String email){

        if(checkForEmail(email)){
            System.out.println("User does not exist, unable to perform deletion.");
            return false;
        }
        else{
            //how to delete a document based on a single parameter -- deleting an account
            Bson query = eq("email", email);

            try {
                collection.deleteOne(query);
                System.out.println("User (" + email + ") has been successfully deleted.");

            } catch (MongoException m) {
                System.err.println("Unable to delete user." + m);
            }
        }

        return true;
    }

    //delete later -- for convenience for testing
    public void removeAllUsers(){
        collection.deleteMany(new Document());
    }
}
