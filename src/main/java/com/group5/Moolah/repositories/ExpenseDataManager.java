package com.group5.Moolah.repositories;
import com.group5.Moolah.model.*;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.*;

import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
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
        String date = e.getDate();
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
        String date = e.getDate();
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

    /**
     * Retrieve the specified document based on the user's email
     * and the Expense data.
     *
     * @param email String representing the user's email associated with their account
     * @param d Expense Object which holds all information about an expense
     * @return Document of the ExpenseData collection that matches the given parameters, else null
     */
    public Document findDocumentToDelete(String email, DeleteExpense d){
        String name = d.getName();
        double amount = d.getAmount();

        //find the first document
        Document doc = collection
                .find(and(eq("email", email),
                        eq("name", name),
                        eq("amount", amount)
                )).first();

        return doc;
    }

    //find most recently added documents -- arraylist
    public List<Expense> findRecentDocuments(String email){
        //fill with expense objects
        List <Expense> expenseList = new ArrayList<>();
        //find the 3 most recent expenses
        MongoCursor<Document> cursor = collection.find(and(eq("email", email))).sort(new Document("_id", -1)).limit(3).iterator();

        try{
            while(cursor.hasNext()){
                Document current = cursor.next();
                System.out.println(current.getString("name"));
                String name = "";
                double amount = 0;
                String date = "";
                String method = "";
                String recurring = "";
                String category = "";

                Expense e = new Expense(name, amount, date, method, recurring, category);

                //initialize the expense object
                //set name
                e.setName(current.getString("name"));
                //set amount
                e.setAmount(current.getDouble("amount"));
                //set date
                e.setDate(current.getString("date"));

                expenseList.add(e);
            }
        } finally{
            cursor.close();
        }

        //3 most recent documents are returned
        //store values -- only name, amount and date
        return expenseList;
    }

    public List<Expense> retrieveDailyExpenses(String email, String chosenDate){
        //fill with expense objects
        List <Expense> expenseList = new ArrayList<>();
        //find the 3 most recent expenses
        MongoCursor<Document> cursor = collection.find(and(eq("email", email), eq("date", chosenDate))).sort(new Document("_id", -1)).limit(3).iterator();

        try{
            while(cursor.hasNext()){
                Document current = cursor.next();
                System.out.println(current.getString("name"));
                String name = "";
                double amount = 0;
                String date = "";
                String method = "";
                String recurring = "";
                String category = "";

                Expense e = new Expense(name, amount, date, method, recurring, category);

                //initialize the expense object
                //set name
                e.setName(current.getString("name"));
                //set amount
                e.setAmount(current.getDouble("amount"));
                //set date
                e.setDate(current.getString("date"));

                expenseList.add(e);
            }
        } finally{
            cursor.close();
        }

        //3 most recent documents are returned
        //store values -- only name, amount and date
        return expenseList;
    }

    //update existing expense
    /**
     * Update an existing expense in the ExpenseData collection. Each user is identified by
     * their email, therefore the expense will be linked to them. If an attempt is made
     * to update an expense that does not exist, return false.
     *
     * @param email String representing the user's email associated with their account
     * @param u Expense Object which holds both the old expense and updated expense to update
     * @return true if the expense document was updated successfully, else false
     */
    public Boolean updateExpense(String email, UpdateExpense u){
        //name, amount, date, method, recurring category
        //build existing expense and check if it exists
        Expense e = new Expense(
                u.getName(),
                u.getAmount(),
                u.getDate(),
                u.getMethod(),
                u.getRecurring(),
                u.getCategory()
        );

        Expense updatedExpense = new Expense(
                u.getName_update(),
                u.getAmount_update(),
                u.getDate_update(),
                u.getMethod_update(),
                u.getRecurring_update(),
                u.getCategory_update()
        );

        //check if the document exists
        Document doc = findDocument(email, e);

        String name = e.getName();
        String date = updatedExpense.getDate();
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
                System.out.println("Unable to update expense.");
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
     * @param d Expense Object which holds all information about an expense
     * @return true if the expense document was deleted successfully, else false
     */
    public Boolean deleteExpense(String email, DeleteExpense d){
        //delete the most recent expense if there are multiple ones (first one)

        Document doc = findDocumentToDelete(email, d);
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
