package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Supplier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Service
@Transactional
public class SupplierServiceImpl {
	
	@Autowired SupplierService supplierService;
	
	public ObservableList<Supplier> getAllSuppliers() {
		Iterable<Supplier> suppliersIt = this.supplierService.findAll();
		ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
		suppliersIt.forEach(supplier -> suppliers.add(supplier));
		
		return suppliers;
	}

	public Supplier updateSupplier(Supplier supplier) {
		return supplierService.save(supplier);
	}

	public void deleteSupplier(Supplier supplier) {
		supplierService.delete(supplier);
	}
}
