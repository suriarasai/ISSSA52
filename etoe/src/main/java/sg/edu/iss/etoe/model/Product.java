package sg.edu.iss.etoe.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String productName;
	private String productDescription;
	@OneToOne(cascade = {CascadeType.ALL})  
    @JoinColumn(name="SUPP_ID")
	private Supplier supplier;
	private long qoh;
	private double price;
	private long rol;
	private Date dolr;
	//Custom Constructor 1
	public Product(String productName, String productDescription, Supplier supplier, long qoh, double price, long rol,
			Date dolr) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.supplier = supplier;
		this.qoh = qoh;
		this.price = price;
		this.rol = rol;
		this.dolr = dolr;
	}
	//Custom Constructor 2
	public Product(String productName, String productDescription, long qoh, double price, long rol, Date dolr) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.qoh = qoh;
		this.price = price;
		this.rol = rol;
		this.dolr = dolr;
	}
	
	
	

}
