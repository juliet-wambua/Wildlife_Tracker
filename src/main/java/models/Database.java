package models;

import org.sql2o.Sql2o;

public class Database {
    public static Sql2o sql2otest = new Sql2o("jdbc:postgresql://ec2-107-20-243-220.compute-1.amazonaws.com:5432/d8vo75qvp1d84l", "foisumzmptunls", "59602abbc96bdb9a673321ec69bbbd2c14c3ea0f992c568fb7500db4097b7856");
}
