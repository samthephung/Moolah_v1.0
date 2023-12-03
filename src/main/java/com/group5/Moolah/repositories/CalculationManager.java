package com.group5.Moolah.repositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;

import org.bson.Document;

import java.util.List;

/**
 * A class that holds the operations to allow a user to
 * manipulate their own expense data retrieved from the ExpenseData
 * collection. The class provides functionality to retrieve the
 * daily and monthly totals of all user's expenses, depending on
 * the date provided.
 */
public class CalculationManager {

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

    //setup objects for operations
    /**
     * Construct a connection to the db and establish usage of the ExpenseData collection
     */
    public CalculationManager(MongoClient client){
        //instantiate the object
        this.client = client;
        database = client.getDatabase(Constants.DATABASE);
        collection = database.getCollection(Constants.EXPENSE_COL);
    }

    //Total -- get all expenses from a specific date and user id
    //getDayTotal
    /**
     * Total of all expenses of a specific date (day, month, year). If
     * there exists no expenses on that date, return 0.00.
     *
     * @param email String representing the user's email associated with their account
     * @param date List that represents a date in the format 0:day, 1:month and 2:year
     * @return total of the expenses of the day as a double
     */
    public double getDayTotal(String email, String date){

        double total = 0;
        //MongoCursor Object which will iterate through each of the documents!
        MongoCursor<Document> cursor = collection.find(and(eq("email", email), eq("date", date))).iterator();

        try{
            while(cursor.hasNext()){
                Document current = cursor.next();
                total = total + (double) current.get("amount");
            }
        } finally{
            System.out.println(total);
            cursor.close();
        }

        return total;
    }

    /**
     * Total of all expenses of a specific month (month, year). If ********************* fix this
     * there exists no expenses in the month, return 0.00.
     *
     * @param email String representing the user's email associated with their account
     * @param date List that represents a date in the format 0:day, 1:month and 2:year
     * @return total of the expenses of the day as a double
     */
    public double getMonthTotal(String email, String date){

        double total = 0;
        //MongoCursor Object which will iterate through each of the documents!
        //in -- matches any of the values in the array
        MongoCursor<Document> cursor = collection.find(and(eq("email", email), eq("date", date))).iterator();

        try{
            while(cursor.hasNext()){
                Document current = cursor.next();
                total = total + (double) current.get("amount");
            }
        } finally{
            System.out.println(total);
            cursor.close();
        }

        return total;
    }

    /*public void getWeekTotal(){

    }*/

}
