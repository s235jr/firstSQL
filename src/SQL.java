import java.sql.*;

//"INSERT INTO pracownik VALUES  (7,'Zosia','Samosia',70,120,0)"
//"UPDATE pracownik SET stawka = 25 WHERE ID =3"
//"DELETE FROM pracownik WHERE Nazwisko = 'Samosia'"

public class SQL {

    String host = "jdbc:mysql://localhost:3306/test2";
    String userName = "root";
    String userPassword = "Admin1234!";

    public static void main(String[] args) {

        Pracownik marcin = new Pracownik(8, "Marcin", "Takise", 26, 80, 0);
        //marcin.save();
        Pracownik pracownik = new Pracownik();
        pracownik.showWorker();
        //countWynagrodzenie();
        //showPosition();





        /*String enquire = "UPDATE pracownik SET idstanowiska = 2 WHERE ID = 1";
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test2", "root", "Admin1234!");
            Statement statement = con.createStatement();
            statement.executeUpdate(enquire);
        } catch (SQLException error) {
            System.out.println("Coś poszło nie tak. " + error.getMessage());
        }*/





    }

    static void showPosition() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test2", "root", "Admin1234!");
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM stanowiska");
            while (result.next()) {
                System.out.println(result.getString("idstanowiska") + " " + result.getString("stanowisko"));
            }


        } catch (SQLException error) {
            System.out.println("Coś poszło nie tak." + error.getMessage());
        }
    }




    static void countWynagrodzenie() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test2", "root", "Admin1234!");
            for (int i = 1; i < 10; i++) {
                String sql = "SELECT * FROM pracownik WHERE ID = " + i;
                Statement statement = con.createStatement();
                ResultSet result = statement.executeQuery(sql);
                while (result.next()) {
                    double wynagrodzenie = result.getInt("Stawka") * result.getInt("Ilosc_godzin");
                    //sql = "UPDATE pracownik SET wynagrodzenie = " + wynagrodzenie + " WHERE ID = " + i;
                    sql = "UPDATE pracownik SET wynagrodzenie = " + wynagrodzenie + " WHERE ID = " + i;
                    statement = con.createStatement();
                    statement.executeUpdate(sql);
                }
            }
        } catch (SQLException error) {
            System.out.println("Coś poszło nie tak. " + error.getMessage());
        }
    }
}