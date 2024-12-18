package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Flower;

public interface FlowerRepository extends JpaRepository<Flower, Long> {
}
