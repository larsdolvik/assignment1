package no.larsdolvik.assignment1;

//got some code and help from: http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/

public class Name {
    //private variables
    int _id;
    String _name;

    // Empty constructor
    public Name() {

    }

    // Constructor
    public Name(int id, String name) {
        this._id = id;
        this._name = name;
    }

    // Constructor
    public Name(String name) {
        this._name = name;
    }

    //getting ID
    public int getID() {
        return this._id;
    }

    //getting name
    public String getName() {
        return this._name;
    }

}