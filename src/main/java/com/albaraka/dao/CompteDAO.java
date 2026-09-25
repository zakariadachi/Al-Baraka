package com.albaraka.dao;

import com.albaraka.entity.Compte;
import com.albaraka.entity.CompteCourant;
import com.albaraka.entity.CompteEpargne;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompteDAO {

    public void save(Compte compte) {
        String sql = "INSERT INTO Compte (numero, solde, idClient, typeCompte, decouvertAutorise, tauxInteret) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, compte.getNumero());
            stmt.setDouble(2, compte.getSolde());
            stmt.setInt(3, compte.getIdClient());
            
            if (compte instanceof CompteCourant cc) {
                stmt.setString(4, "COURANT");
                stmt.setDouble(5, cc.getDecouvertAutorise());
                stmt.setNull(6, Types.DOUBLE);
            } else if (compte instanceof CompteEpargne ce) {
                stmt.setString(4, "EPARGNE");
                stmt.setNull(5, Types.DOUBLE);
                stmt.setDouble(6, ce.getTauxInteret());
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Compte> findById(int id) {
        String sql = "SELECT * FROM Compte WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResultSetToCompte(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Compte> findByClientId(int clientId) {
        List<Compte> comptes = new ArrayList<>();
        String sql = "SELECT * FROM Compte WHERE idClient = ?";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                comptes.add(mapResultSetToCompte(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comptes;
    }
    
    public void update(Compte compte) {
        String sql = "UPDATE Compte SET solde = ?, decouvertAutorise = ?, tauxInteret = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, compte.getSolde());
            if (compte instanceof CompteCourant cc) {
                stmt.setDouble(2, cc.getDecouvertAutorise());
                stmt.setNull(3, Types.DOUBLE);
            } else if (compte instanceof CompteEpargne ce) {
                stmt.setNull(2, Types.DOUBLE);
                stmt.setDouble(3, ce.getTauxInteret());
            }
            stmt.setInt(4, compte.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Compte WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Compte mapResultSetToCompte(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String numero = rs.getString("numero");
        double solde = rs.getDouble("solde");
        int idClient = rs.getInt("idClient");
        String type = rs.getString("typeCompte");
        
        if ("COURANT".equalsIgnoreCase(type)) {
            double decouvert = rs.getDouble("decouvertAutorise");
            return new CompteCourant(id, numero, solde, idClient, decouvert);
        } else {
            double taux = rs.getDouble("tauxInteret");
            return new CompteEpargne(id, numero, solde, idClient, taux);
        }
    }
}
