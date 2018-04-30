import java.sql.*;

class Pracownik extends SQL {

    protected int id;
    protected String imie;
    protected String nazwisko;
    protected int stawka;
    protected int ilosc_godzin;
    protected double wynagrodzenie;

    public Pracownik(int id, String imie, String nazwisko, int stawka, int ilosc_godzin, double wynagrodzenie) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.stawka = stawka;
        this.ilosc_godzin = ilosc_godzin;
        this.wynagrodzenie = wynagrodzenie;
    }

    public Pracownik() {
    }

    void save() {
        String enquire = "INSERT INTO pracownik VALUES (" + this.id + ",'" + this.imie + "','" + this.nazwisko + "'," + this.stawka + "," + this.ilosc_godzin + "," + this.wynagrodzenie + ")";
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test2", "root", "Admin1234!");
            Statement statement = con.createStatement();
            statement.executeUpdate(enquire);
        } catch (SQLException error) {
            System.out.println("Coś poszło nie tak. " + error.getMessage());
        }
    }

    static void showWorker() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test2", "root", "Admin1234!");
            Statement statement = con.createStatement();
            String sql = "SELECT pracownik.Imie, pracownik.Nazwisko, pracownik.Wynagrodzenie, stanowiska.stanowisko " +
                    "FROM pracownik JOIN stanowiska ON pracownik.idstanowiska = stanowiska.idstanowiska WHERE stanowisko = 'Spawacz';";
            ResultSet result = statement.executeQuery(sql);
            ResultSetMetaData resultMD = result.getMetaData();
            int countColumn = resultMD.getColumnCount();
            while (result.next()) {
                for (int k = 1; k <= countColumn; k++) {
                    System.out.print(resultMD.getColumnName(k) + ": " + result.getString(k) + ", ");
                }
                System.out.println();

                //System.out.println(result.getString("ID") + " " + result.getString("Imie") + " " + result.getString("Nazwisko") + " " + result.getString("Stawka") + " " + result.getString("Ilosc_godzin") + " " + result.getString("Wynagrodzenie") + " " + result.getString("idstanowiska"));
                //System.out.println(result.getString("Imie") +", " + result.getString("Nazwisko") + ", " + result.getString("stanowisko"));
            }
        } catch (SQLException error) {
            System.out.println("Coś poszło nie tak. " + error.getMessage());
        }
        return;
    }
}