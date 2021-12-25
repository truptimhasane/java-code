import java.beans.Statement;
import java.io.IOException;
import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;

import javax.management.Query;
import javax.print.PrintException;
import com.mysql.jdbc.PreparedStatement;

public class Student_details{
              
    public static void insert(java.sql.Connection con,java.sql.Statement stmt) throws SQLException, ParseException{
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Student id");
        int id=sc.nextInt();
        System.out.println("Enter Student Name");
        String nm=sc.next();
        System.out.println("Enter Student DOB");
        String sdt=sc.next();
        java.sql.Date dt=Date.valueOf(sdt);
        System.out.println("Enter Student DOJ");
        String sdtj=sc.next();
        java.sql.Date dtj=Date.valueOf(sdtj);
        String query = "INSERT INTO STUDENT(STUDENT_NO, STUDENT_NAME, STUDENT_DOB,STUDENT_DOJ) VALUES (?,?,?,?)";
        java.sql.PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1,id);
        pstmt.setString(2, nm);
        pstmt.setDate(3,dt);
        pstmt.setDate(4,dtj);
        int i=pstmt.executeUpdate(); 
        System.out.println(i+" record Inserted");  
    }


    public static void update(java.sql.Statement stmt,Connection con) throws SQLException{
        Scanner sc=new Scanner(System.in);
        
        boolean f=true;
        while(f){
         System.out.println("What You Want To Update:-"+ '\n' +"1.Name"+ '\n' +"2.Date Of Birth"+ '\n' +"3.Date Of Joining"+ '\n' +"4.Back"); 
        int ch=sc.nextInt();
        switch(ch){
            case 1:
            System.out.println("Enter Student Id To Update:-");   
             int id=sc.nextInt();
            System.out.println("Enter new Name:-"); 
            String nm=sc.next();
            String s = "UPDATE STUDENT SET STUDENT_NAME =? WHERE STUDENT_NO=?";
            java.sql.PreparedStatement pstmt = con.prepareStatement(s);
            pstmt.setString(1,nm);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

            break;

            case 2: 
            System.out.println("Enter Student Id To Update:-");   
            int i=sc.nextInt();
            System.out.println("Enter new Birth Date:-"); 
            String sdt=sc.next();
            java.sql.Date dt=Date.valueOf(sdt);
            String sq = "UPDATE STUDENT "+"SET STUDENT_DOB = ? WHERE STUDENT_NO=?";
            java.sql.PreparedStatement p = con.prepareStatement(sq);
            p.setDate(1, dt);
            p.setInt(2, i);
            p.executeUpdate();
            break;

            case 3: 
            System.out.println("Enter Student Id To Update:-");   
            int d=sc.nextInt();
            System.out.println("Enter new Joining Date:-"); 
            String sdtj=sc.next();
            java.sql.Date dtj=Date.valueOf(sdtj);
            String sql = "UPDATE STUDENT "+"SET STUDENT_DOJ =? WHERE STUDENT_NO=?";
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            pst.setDate(1, dtj);
            pst.setInt(2, d);
            pst.executeUpdate();
            break;
            case 4: f=false;
                break;
            default:
                    System.out.println("Enter Correct CHOICE-");   
        }
    }
    }
    public static void delete(java.sql.Statement stmt) throws SQLException{
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Student Id To Delete:-");   
        int id=sc.nextInt();
        String query = "DELETE FROM STUDENT WHERE STUDENT_NO ="+id;
        int i=stmt.executeUpdate(query); 
        System.out.println(i+" record deleted");
       
    }
    public static void search(java.sql.Statement stmt) throws SQLException{

        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Student Id:-");   
        int id=sc.nextInt();
        String query = "SELECT * FROM STUDENT WHERE STUDENT_NO ="+id;
        ResultSet rs=stmt.executeQuery(query);
        while(rs.next()){
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getDate(3)+"  "+rs.getDate(4)+"  ");
        }
        
        rs.close();

    }


    public static void display(java.sql.Statement stmt) throws SQLException{
       String query="SELECT * FROM `student` WHERE 1";
        ResultSet rs=stmt.executeQuery(query);
        System.out.println("STUDENT_NO"+ '\t' +"STUDENT_NAME"+ '\t' +"STUDENT_DOB"+ '\t' +"STUDENT_DOJ"+ '\t');

        while(rs.next()){
            System.out.println("    "+rs.getInt(1)+"         "+rs.getString(2)+"           "+rs.getDate(3)+"      "+rs.getDate(4)+"  ");
        }
        rs.close();

    }
 






    public static void main(String[] args)throws IOException,SQLException {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/STUDENT","root", "");
            java.sql.Statement stmt=con.createStatement();
            Scanner sc=new Scanner(System.in);
            System.out.println("STUDENT INFORMATION");
            boolean f=true;

            while(f){
                System.out.println("Enter Choice:-"+ '\n' +"1.Insert"+ '\n' +"2.Update"+ '\n' +"3.Delete"+ '\n' +"4.List Of All Students"+ '\n' +"5.Search"+ '\n' +"6.Exit");
                int c=sc.nextInt();
                
            switch(c){
            case 1:insert(con,stmt);break;
            case 2:update(stmt,con);break;
            case 3:delete(stmt);break;
            case 4:display(stmt);break;
            case 5:search(stmt);break;
            case 6: f=false;break;
            default :System.out.println("Enter Correct CHOICE-");
            }
            }
            
        }
        catch(Exception e){
        System.out.println(e);
        }
        
    }
    
}