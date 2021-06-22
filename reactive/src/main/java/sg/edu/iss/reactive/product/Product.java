package sg.edu.iss.reactive.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@NoArgsConstructor
@Data
@ToString
public class Product {
	@Id
	private String id;
	private int productId;
	private String name;
	@Version
	private Integer version;
	private int weight;
	public Product(int productId, String name, Integer version, int weight) {
		super();
		this.productId = productId;
		this.name = name;
		this.version = version;
		this.weight = weight;
	}
	
	

}
