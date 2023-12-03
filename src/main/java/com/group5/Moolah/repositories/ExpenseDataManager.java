package com.group5.Moolah.repositories;
import com.group5.Moolah.model.*;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import java.util.List;

/**
 * A class that includes all operations that may be performed on the
 * ExpenseData collection within the database. The ExpenseData collection
 * may hold many documents each representing an expense that a user has made.
 * The user will be able to add new expense documents, update or delete
 * existing expense documents within the ExpenseData Collection.
 * //this is where the actual CRUD operations will be performed -- reaching out directly to the MongoDB from this class
 */
public class ExpenseDataManager {

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
     * Construct a connection to the db and establish usage of the ExpenseData collection
     */
    public ExpenseDataManager(MongoClient client){
        //instantiate the object
        this.client = client;
        database = client.getDatabase(Constants.DATABASE);
        collection = database.getCollection(Constants.EXPENSE_COL);
    }

    /**
     * Add a new expense to the ExpenseData collection. Each user is identified by
     * their email, therefore the expense will be linked to them.
     *
     * @param email String representing the user's email associated with their account
     * @param e Expense Object which holds all information about an expense
     * @return true if the expense document was added successfully, else false
     */
    public Boolean addExpense(String email, Expense e){

        //able to add dupes :)
        String name = e.getName();
        List<Integer> date = e.getDate();
        double amount = e.getAmount();
        String category = e.getCategory();
        String method = e.getMethod();
        String recurring = e.getRecurring();

        Document expense = new Document("_id", new ObjectId())
                .append("email", email)
                .append("name", name)
                .append("date", date)
                .append("amount", amount)
                .append("category", category)
                .append("method", method)
                .append("recurring", recurring);

        try {
            collection.insertOne(expense);
            System.out.println("Successfully added a new expense.");

        } catch (MongoException m) {
            System.err.println("Unable to add expense." + m);
            return false;
        }

        return true;
    }

    /**
     * Retrieve the specified document based on the user's email
     * and the Expense data.
     *
     * @param email String representing the user's email associated with their account
     * @param e Expense Object which holds all information about an expense
     * @return Document of the ExpenseData collection that matches the given parameters, else null
     */
    public Document findDocument(String email, Expense e){
        String name = e.getName();
        List<Integer> date = e.getDate();
        double amount = e.getAmount();
        String category = e.getCategory();
        String method = e.getMethod();
        String recurring = e.getRecurring();

        //find the first document
        Document doc = collection
                .find(and(eq("email", email),
                        eq("name", name),
                        eq("date", date),
                        eq("amount", amount),
                        eq("category", category),
                        eq("method", method),
                        eq("recurring", recurring)
                )).first();

        return doc;
    }

    //update existing expense
    /**
     * Update an existing expense in the ExpenseData collection. Each user is identified by
     * their email, therefore the expense will be linked to them. If an attempt is made
     * to update an expense that does not exist, return false.
     *
     * @param email String representing the user's email associated with their account
     * @param e Expense Object which holds all information about an expense
     * @param updatedExpense Expense Object which hold any information to update the expense
     * @return true if the expense document was updated successfully, else false
     */
    public Boolean updateExpense(String email, Expense e, Expense updatedExpense){

        Document doc = findDocument(email, e);

        String name = e.getName();
        List<Integer> date = updatedExpense.getDate();
        double amount = updatedExpense.getAmount();
        String category = updatedExpense.getCategory();
        String method = updatedExpense.getMethod();
        String recurring = updatedExpense.getRecurring();

        Bson updateExpense = Updates.combine(
                Updates.set("date", date),
                        Updates.set("name", name),
                        Updates.set("amount", amount),
                        Updates.set("category", category),
                        Updates.set("method", method),
                        Updates.set("recurring", recurring));
        try {
            if(doc != null){
                collection.updateOne(doc, updateExpense);
                System.out.println("User has updated this expense.");
            }
            else{
                return false;
            }
        } catch (MongoException m) {
            System.err.println("Unable to update data because expense does not exist." + m);
        }
        return true;
    }

    //delete expense
    /**
     * Delete an existing expense in the ExpenseData collection. Each user is identified by
     * their email, therefore the expense will be linked to them. If an attempt is made
     * to delete an expense that does not exist, return false.
     *
     * @param email String representing the user's email associated with their account
     * @param e Expense Object which holds all information about an expense
     * @return true if the expense document was deleted successfully, else false
     */
    public Boolean deleteExpense(String email, Expense e){
        //delete the most recent expense if there are multiple ones (first one)

        Document doc = findDocument(email, e);
        if(doc != null){
            Bson query = eq("_id", doc.get("_id"));

            try {
                collection.deleteOne(query);
                System.out.println("The specified expense has been successfully deleted.");

            } catch (MongoException m) {
                System.err.println("Unable to delete expense." + m);
            }
        }
        else{
            System.out.println("Expense does not exist, unable to perform deletion.");
            return false;
        }

        return true;
    }

    //delete later -- just for testing purposes
    public void removeAllExpenses(){
        collection.deleteMany(new Document());
    }

}
