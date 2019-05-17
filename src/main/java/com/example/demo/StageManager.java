package com.example.demo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.example.demo.controller.SupplierController;
import com.example.demo.controller.SupplierFormController;
import com.example.demo.model.Supplier;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

@Component
public class StageManager {

	private Stage primaryStage;

	@Value("${classpath:/com/example/demo/view/SuppliersView.fxml}") 
	private Resource mainFxmlResource;

	@Value("${classpath:/com/example/demo/view/SupplierFormView.fxml}")
	private Resource supplierFormViewResource;
	
	@Autowired SupplierController supplierController;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = new Stage();
	}

	public void showMain() {
		try {
			FXMLLoader loader = new FXMLLoader(mainFxmlResource.getURL());
			loader.setController(supplierController);
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Supplier showSupplierForm(Supplier supplier) {
		try {
			Stage dialogStage = new Stage();
			SupplierFormController controller = new SupplierFormController();
			
			FXMLLoader loader = new FXMLLoader(supplierFormViewResource.getURL());
			loader.setController(controller);
			
			controller.setSupplier(supplier);

			AnchorPane pane = loader.load();
			Scene scene = new Scene(pane);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.setScene(scene);
			dialogStage.showAndWait();
			
			if(controller.isSaveClicked()) {
				return controller.getSupplier();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
