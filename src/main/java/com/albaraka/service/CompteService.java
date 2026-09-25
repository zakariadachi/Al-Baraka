package com.albaraka.service;

import com.albaraka.dao.CompteDAO;
import com.albaraka.entity.Compte;

import java.util.List;
import java.util.Optional;

public class CompteService {
    private final CompteDAO compteDAO;

    public CompteService() {
        this.compteDAO = new CompteDAO();
    }

    public void createCompte(Compte compte) {
        compteDAO.save(compte);
    }

    public Optional<Compte> getCompteById(int id) {
        return compteDAO.findById(id);
    }

    public List<Compte> getComptesByClientId(int clientId) {
        return compteDAO.findByClientId(clientId);
    }

    public void updateCompte(Compte compte) {
        compteDAO.update(compte);
    }

    public void deleteCompte(int id) {
        compteDAO.delete(id);
    }
}
