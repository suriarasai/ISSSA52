package sg.edu.iss.reactive.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class Product {
	@Id
	private int id;
	private int productId;
	private String name;
	private int version;
	private int weight;
	public Product(int productId, String name, Integer version, int weight) {
		super();
		this.productId = productId;
		this.name = name;
		this.version = version;
		this.weight = weight;
	}
}
