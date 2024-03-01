package com.shopnow.productmicroservice.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shopnow.productmicroservice.entities.Category;
import com.shopnow.productmicroservice.entities.Product;
import com.shopnow.productmicroservice.exceptions.CustomException;
import com.shopnow.productmicroservice.repositories.CategoryRepository;
import com.shopnow.productmicroservice.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Value("${shopnow.images.productimages}")
	private String productimagepath;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	FileService fileService;

	@Override
	public Product addProduct(Product product, Integer categoryid, MultipartFile file) {

		Category category = categoryRepository.findById(categoryid).orElseThrow(
				() -> new CustomException("Category not found with id:" + categoryid, HttpStatus.NOT_FOUND));
		product.setCategory(category);

		String filenamewithtimestamp = null;
		try {
			filenamewithtimestamp = fileService.uploadImage(productimagepath, file);
		} catch (IOException e) {
			throw new CustomException("Error Occurred while uploading Image", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		product.setProductimage(filenamewithtimestamp);
		Product addedProduct = productRepository.save(product);
		return addedProduct;
	}

	@Override
	public Product updateProduct(Product updatedProduct) {
		Integer productId = updatedProduct.getPid();

		// Check if the product exists in the database
		Product existingProduct = productRepository.findById(productId)
				.orElseThrow(() -> new CustomException("Product not found with id:" + productId, HttpStatus.NOT_FOUND));

		// Update the existing product with the new values
		existingProduct.setName(updatedProduct.getName());
		existingProduct.setDescription(updatedProduct.getDescription());
		existingProduct.setPrice(updatedProduct.getPrice());
	
		// If the category ID is provided, update the category of the product
		if (updatedProduct.getCategory() != null) {
			Category category = categoryRepository.findById(updatedProduct.getCategory().getCategoryid())
					.orElseThrow(() -> new CustomException("Category not found with id:" + updatedProduct.getCategory().getCategoryid(), HttpStatus.NOT_FOUND));
			existingProduct.setCategory(category);
		}

		// Save the updated product to the database
		Product updated = productRepository.save(existingProduct);
		
		return updated;
	}


	@Override
	public Product getProductById(Integer id) {
		Product foundProduct = productRepository.findById(id)
				.orElseThrow(() -> new CustomException("Product not found with id:" + id, HttpStatus.NOT_FOUND));
		return foundProduct;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> allProducts = productRepository.findAll();
		return allProducts;
	}

	@Override
	public Product deleteProductById(Integer id) {
		Product foundProduct = productRepository.findById(id)
				.orElseThrow(() -> new CustomException("Product not found with id:" + id, HttpStatus.NOT_FOUND));
		boolean imageDeleted = fileService.deleteImage(productimagepath, foundProduct.getProductimage());
		if(imageDeleted) {
			productRepository.delete(foundProduct);
		}else {
			throw new CustomException("Error While Deleting Image,[Migth be open/in use else where]",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return foundProduct;
	}

	@Override
	public List<Product> getAllProductsByCategoryId(Integer categoryid) {
		if (categoryid == 0)
			return productRepository.findAll();

		Category category = categoryRepository.findById(categoryid).orElseThrow(
				() -> new CustomException("Category not found with id:" + categoryid, HttpStatus.NOT_FOUND));
		List<Product> products = productRepository.findProductByCategory(category);
		return products;
	}

}
