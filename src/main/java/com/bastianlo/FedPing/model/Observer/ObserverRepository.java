package com.bastianlo.FedPing.model.Observer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ObserverRepository extends JpaRepository<Observer, UUID> {
}
