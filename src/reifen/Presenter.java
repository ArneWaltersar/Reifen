package reifen;
 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
 
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
 
public class Presenter {
   
    private static Func1 f;
 
    public static void start(Fenster fenster) {
        System.out.println("sql-3");
        Presenter.init();
       
        fenster.getStart1().setOnAction(e -> {
            Presenter.init();
            String filter = fenster.getRadioSommer().isSelected()?"S":fenster.getRadioWinter().isSelected()?"W":null;
            Presenter.showReifen(fenster.getArea1(), fenster.getTextField(), filter);          
            Presenter.close();
        });
       
        fenster.getStart2().setOnAction(e -> {
            Presenter.init();
            Presenter.showReifenHersteller(fenster.getArea2(), fenster.getCombo().getSelectionModel().getSelectedItem().toString());           
            Presenter.close();
        });
       
    }
 
    private static void showReifen(TextArea area1, TextField textField, String filter) {
        ArrayList<String> al = Presenter.getReifenListe(filter);       
        textField.setText(al.remove(al.size()-1));     
        String res = al.stream().map(Object::toString).collect(Collectors.joining("\n"));  
       
        area1.setText(res);    
    }
   
    private static ArrayList<String> getReifenListe(String filter) {
        ArrayList<String> al = new ArrayList<String>();    
        ResultSet rs = Presenter.getReifen(filter);
 
        if (rs != null) {
            StringBuilder res = new StringBuilder();
            try {
                while(rs.next()) {
                    res.append(rs.getString("artikelnr") +" "+ rs.getString("bezeichnung") +", Reifenart: "+ rs.getString("reifenart") +", Verkaufspreis: "+ rs.getDouble("verkaufspreis") +" Euro");
 
                    al.add(res.toString());
                    res.delete(0, res.length());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       
        if (rs == null) {
            System.out.println("ResultSet Reifen null");
        }
       
        if(!al.isEmpty()) {
            al.add("Anzahl der Artikel: "+al.size());
        }
       
        return al;
    }
   
    private static ResultSet getReifen(String filter) {
        ResultSet res = null;      
        String sql = Presenter.getReifenSelectSQL();
       
       
        try {
            PreparedStatement ps = f.getStat(sql);
            ps.setString(1, filter);
            res = ps.executeQuery();                   
        } catch (Exception e) {
            System.out.println("Error getReifen");
            e.printStackTrace();
        }
       
        return res;    
    }
 
    private static String getReifenSelectSQL() {
        StringBuilder str = new StringBuilder();
        str.append(" SELECT ");    
        str.append(" artikelnr "); 
        str.append(" ,bezeichnung ");  
        str.append(" ,reifenart ");
        str.append(" ,verkaufspreis ");
        str.append(" FROM ");
        str.append(" reifen ");
        str.append(" where ");
        str.append(" reifenart = ? ");
           
        return str.toString();
    }
   
 
 
    private static void showReifenHersteller(TextArea area2, String filter) {
        //System.out.println(filter);
        ArrayList<String> al = Presenter.getHerstellerListe(filter);       
        String res = al.stream().map(Object::toString).collect(Collectors.joining("\n"));  
       
        area2.setText(res);    
    }
   
    private static ArrayList<String> getHerstellerListe(String filter) {
        ArrayList<String> al = new ArrayList<String>();
        ArrayList<String> al2 = new ArrayList<String>();
        ResultSet rs = Presenter.getHersteller(filter);
 
        if (rs != null) {
            StringBuilder res = new StringBuilder();
            try {
                while(rs.next()) {
                    //rs.getString("reifenart").equals("S" != null)?countSommer++:countWinter++;
                    res.append(rs.getString("artikelnr") +" "+ rs.getString("bezeichnung") +", Hersteller: "+ rs.getString("hersteller") +", Verkaufspreis: "+ rs.getDouble("verkaufspreis") +" Euro");
                    al2.add(rs.getString("reifenart"));
                   
                    al.add(res.toString());
                    res.delete(0, res.length());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       
        if (rs == null) {
            System.out.println("ResultSet Hersteller null");
        }
       
        if(!al.isEmpty()) {
            if(!al2.isEmpty()) {
                // zusatz ermitteln            
                al.add("");
                al.add("davon Sommerreifen: "+al2.stream().filter(e -> e.equals("S")).count());
                al.add("davon Winterreifen: "+al2.stream().filter(e -> e.equals("W")).count());
            }
           
        }
       
        return al;
    }
   
    private static ResultSet getHersteller(String filter) {
        ResultSet res = null;      
        String sql = Presenter.getHerstellerSelectSQL();
       
       
        try {
            PreparedStatement ps = f.getStat(sql);
            ps.setString(1, filter);
            res = ps.executeQuery();                   
        } catch (Exception e) {
            System.out.println("Error getHersteller");
            e.printStackTrace();
        }
       
        return res;    
    }
   
    private static String getHerstellerSelectSQL() {
        StringBuilder str = new StringBuilder();
        str.append(" SELECT ");    
        str.append(" artikelnr "); 
        str.append(" ,bezeichnung ");
        str.append(" ,reifenart ");    
        str.append(" ,hersteller ");   
        str.append(" ,verkaufspreis ");
        str.append(" FROM ");
        str.append(" reifen ");
        str.append(" where ");
        str.append(" hersteller = ? ");
           
        return str.toString();
    }
 
    private static void close() {
        f.closeStatement();
        f.closeConnection();
       
    }
 
    public static void init() {
        Presenter.f = new Func1();
    }
 
}