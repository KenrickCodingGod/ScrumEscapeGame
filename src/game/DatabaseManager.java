package game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/scrum_escape";
    private static final String USER = "root"; // pas aan als nodig
    private static final String PASSWORD = "kenrick"; // pas aan als nodig

    public DatabaseManager() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            String sql = """
                CREATE TABLE IF NOT EXISTS speler (
                    id INT PRIMARY KEY,
                    positie INT,
                    monsters TEXT
                );
                """;
            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println("❌ Fout bij verbinden met database: " + e.getMessage());
        }
    }

    public void slaVoortgangOp(int positie, List<Monster> monsters) {
        // monsterlijst opslaan als string (alleen namen)
        String monstersStr = monsters.stream()
                .map(Monster::getNaam)
                .reduce((a, b) -> a + "," + b)
                .orElse("");

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement stmt = conn.prepareStatement("""
                INSERT INTO speler (id, positie, monsters)
                VALUES (1, ?, ?)
                ON DUPLICATE KEY UPDATE positie = VALUES(positie), monsters = VALUES(monsters);
            """);
            stmt.setInt(1, positie);
            stmt.setString(2, monstersStr);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Opslaan mislukt: " + e.getMessage());
        }
    }

    public Speler laadVoortgang() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM speler WHERE id = 1")) {

            if (rs.next()) {
                int positie = rs.getInt("positie");
                String[] monsterNamen = rs.getString("monsters").split(",");
                Speler speler = new Speler();
                speler.setPositie(positie);

                for (String naam : monsterNamen) {
                    if (!naam.isBlank()) {
                        Monster m = maakMonsterOpNaam(naam);
                        speler.voegMonsterToe(m);
                    }
                }
                return speler;
            }
        } catch (SQLException e) {
            System.out.println("⚠️ Geen opgeslagen voortgang gevonden of fout bij laden: " + e.getMessage());
        }

        return new Speler();
    }

    public void resetVoortgang() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM speler WHERE id = 1");
            System.out.println("🔄 Voortgang is gereset.");
        } catch (SQLException e) {
            System.out.println("❌ Fout bij resetten: " + e.getMessage());
        }
    }

    // herbouw monster op basis van nam
    private Monster maakMonsterOpNaam(String naam) {
        return switch (naam) {
            case "Scopezilla" -> new Monster("Scopezilla", "Te veel werk zonder afstemming toegevoegd.");
            case "TeamStilte Zombie" -> new Monster("TeamStilte Zombie", "Je team communiceert niet goed.");
            case "Chaos Tornado" -> new Monster("Chaos Tornado", "Geen overzicht op de taken.");
            case "FeedbackFobie" -> new Monster("FeedbackFobie", "Stakeholders snappen het resultaat niet.");
            case "LoopSpook" -> new Monster("LoopSpook", "Je leert niet van fouten.");
            case "TIAverslinder" -> new Monster("TIAverslinder", "TIA is vergeten.");
            default -> new Monster(naam, "Onbekend monster uit opgeslagen data.");
        };
    }
}
