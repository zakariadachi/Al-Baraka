package com.albaraka.dao;

import com.albaraka.entity.Transaction;
import com.albaraka.entity.TypeTransaction;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    public void save(Transaction transaction) {
        String sql = "INSERT INTO Transaction (date, montant, type, lieu, idCompte) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(transaction.date()));
            stmt.setDouble(2, transaction.montant());
            stmt.setString(3, transaction.type().name());
            stmt.setString(4, transaction.lieu());
            stmt.setInt(5, transaction.idCompte());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Transaction> findByCompteId(int compteId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM Transaction WHERE idCompte = ? ORDER BY date DESC";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, compteId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                transactions.add(mapResultSetToTransaction(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public List<Transaction> findAll() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM Transaction ORDER BY date DESC";
        try (Connection conn = DatabaseConnection.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                transactions.add(mapResultSetToTransaction(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    private Transaction mapResultSetToTransaction(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();
        double montant = rs.getDouble("montant");
        TypeTransaction type = TypeTransaction.valueOf(rs.getString("type"));
        String lieu = rs.getString("lieu");
        int idCompte = rs.getInt("idCompte");
        return new Transaction(id, date, montant, type, lieu, idCompte);
    }
}
