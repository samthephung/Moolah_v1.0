package com.group5.Moolah.services;

import com.group5.Moolah.repositories.Constants;
import com.group5.Moolah.repositories.UserDataManager;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import static com.mongodb.client.model.Filters.eq;

public class Main {
    public static void main(String[] args){

        //connection string to the mongodb database >>
        try(MongoClient client = MongoClients.create(Constants.URI)) {
            //String email = "abc";

            //object to handle the userdata functionality
            System.out.println("Create new account?");
            UserDataManager user = new UserDataManager(client);
            user.createUser("Sam", "sp@gmail.com", "c");

            //System.out.println("login to existing account?");
            //if (user.matchUser("Sam", "sp@gmail.com", "12345")) {
            //    email = "sp@gmail.com";
            //}
            /*user.updateUser("sp@gmail.com", "Sarah", "123456");*/

            /*user.deleteUser("sam@gmail.com");
            user.deleteUser("sp@gmail.com");**/

            //date is a list of integers -- in the format -- DAY, MONTH, YEAR
            //List<Integer> date = new ArrayList<>();
            //date.add(14);
            //date.add(11);
            //date.add(2023);

            //ExpenseCalculation ec = new ExpenseCalculation(client);
            //ec.getDayTotal(email, date);

            //ec.getMonthTotal(email, date);

            //ExpenseData exp = new ExpenseData(client);
            //Expense e = new Expense(date, 25.00, "test", "cash", false);
            //Expense f = new Expense(date, 25.00, "test", "cash", false);
            //Expense updateE = new Expense("10-15-2023", 20.50, "test", "card", false);

            //exp.addExpense("sp@gmail.com", e);
            //exp.addExpense("sp@gmail.com", f);
            //exp.updateExpense("sp@gmail.com", e, updateE);
            //exp.deleteExpense("sp@gmail.com", e);


            //create db object -- pass in db name
            //MongoDatabase db = client.getDatabase("sampleDB");

            //create collection object to access collection -- pass in collection name
            //MongoCollection<Document> col = db.getCollection("sampleCollection");

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*public static final String URI = "mongodb+srv://admin:adminTest123@moolah.ye79sts.mongodb.net/?retryWrites=true&w=majority";

    public static void main(String[] args){
        // Codec provider for serializing classes into BSONs
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), CodecRegistries.fromProviders(pojoCodecProvider));

        //connection string to the mongodb database >>
        try(MongoClient client = MongoClients.create(URI)) {
            //create db object -- pass in db name
            MongoDatabase db = client.getDatabase("sampleDB").withCodecRegistry(pojoCodecRegistry);

            //create collection object to access collection -- pass in collection name
            MongoCollection<Document> col = db.getCollection("sampleCollection");
            //create collection for users to test
            MongoCollection<User> userCol = db.getCollection("users", User.class);

            //create example user and insert into db
            User user = new User(1, "John", "Doe", "000-000-0000", "johndoe@example.com");
            InsertOneResult result = userCol.insertOne(user);
            if (result.wasAcknowledged()) System.out.println("Insertion successful!");

            List<User> users = new ArrayList<>();
            userCol.find().into(users);
            System.out.println(users.get(0).getFirstName());

            //delete inserted user
            userCol.deleteOne(eq("_id", 1));*/

        }
    }
}
