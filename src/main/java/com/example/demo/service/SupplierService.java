package com.example.demo.service;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Supplier;

public interface SupplierService extends CrudRepository<Supplier, Long> {

}
