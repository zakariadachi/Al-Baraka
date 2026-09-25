package com.albaraka.entity;

import java.time.LocalDateTime;

public record Transaction(
    int id,
    LocalDateTime date,
    double montant,
    TypeTransaction type,
    String lieu,
    int idCompte
) {}
