package Models;

import org.sql2o.Sql2o;

public class DB {
    //public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "postgres", "Bus-242-001/2014");
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-174-129-29-101.compute-1.amazonaws.com:5432/demlj5phrse0s7", "nthvfprikqqmgr", "0adf07b46a043e20e3f3ac970eba598f3642c3d27a35e7c89c9ea8c8113ab96b");
}