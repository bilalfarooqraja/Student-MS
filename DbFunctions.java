import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbFunctions {
    public Connection connectToDb(String dbname,String user,String pass){
    Connection conn=null;
    try{
       Class.forName("org.postgresql.Driver");
       conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,user,pass);
       if(conn!=null){
           System.out.println("Connection Established");
       }else{
           System.out.println("Connection Failed");
       }
    }catch (Exception e){
        System.out.println(e);
        return null;
    }
        return conn;
    }
    //Create Table Function
    public void CreateTable(Connection conn,String tableName){
        Statement statement;
        try{
            String query="create table "+tableName+"(empid SERIAL,name varchar(200),ITCP varchar(200),Calculus varchar(200),OOP varchar(200), DataBase varchar(200),Maths varchar(200),primary key(empid));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Query Executed");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void InsertRows(Connection conn,String tableName,String name,String itcp,String cal,String oop,String db,String maths){
        Statement statement;
        try {
            String query=String.format("Insert into %s(name,itcp,calculus,oop,database,maths) values('%s','%s','%s','%s','%s','%s');",tableName,name,itcp,cal,oop,db,maths);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Inserted");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void readData(Connection conn,String tableName){
        Statement statement;
        ResultSet rs=null;
        try {

            String query="select * from "+tableName+";";
            statement = conn.createStatement();
            rs=statement.executeQuery(query);

            while (rs.next()){
                System.out.print(rs.getString("empid")+" ");
                System.out.print(rs.getString("name")+" ");
                System.out.println("ITCP "+rs.getString("itcp")+" ");
                System.out.println("Callculus "+rs.getString("calculus")+" ");
                System.out.println("OOP "+rs.getString("OOP")+" ");
                System.out.println("DataBase "+rs.getString("database")+" ");
                System.out.println("Maths "+rs.getString("maths")+" ");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void updateData(Connection conn,String tableName,String oldnName,String name){

        Statement statement;
        try {
            String query="UPDATE "+tableName+" SET name = "+"'"+name+"'"+" WHERE name= "+"'"+oldnName+"'"+";";
            statement = conn.createStatement();
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void searchByName(Connection conn,String tableName,String name){

        Statement statement;
        ResultSet rs=null;
        try {
            String query="select * From "+tableName+ " Where name ='"+name+"';";
            statement = conn.createStatement();
            rs=statement.executeQuery(query);
            while (rs.next()){
                System.out.print(rs.getString("empid")+" ");
                System.out.print(rs.getString("name")+" ");
                System.out.println("ITCP "+rs.getString("itcp")+" ");
                System.out.println("Callculus "+rs.getString("calculus")+" ");
                System.out.println("OOP "+rs.getString("OOP")+" ");
                System.out.println("DataBase "+rs.getString("database")+" ");
                System.out.println("Maths "+rs.getString("maths")+" ");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void deleteData(Connection conn,String tableName,String name){

        Statement statement;
        try {
            String query="delete from " +tableName+" where name = '" +name+"'";

            statement = conn.createStatement();
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void deleteTable(Connection conn,String tableName){

        Statement statement;
        try {
            String query="drop table '"+tableName+"'";
            statement = conn.createStatement();
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
