package com.getgoods.productservice.Service;

import com.getgoods.productservice.Feign.InventoryFeign;
import com.getgoods.productservice.Model.Inventory;
import com.getgoods.productservice.Repository.CategoryRepository;
import com.getgoods.productservice.Repository.ProductRepository;
import com.getgoods.productservice.Util.CategoryNotFoundException;
import com.getgoods.productservice.Util.ProductNotFoundException;
import com.getgoods.productservice.entity.Category;
import com.getgoods.productservice.entity.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private InventoryFeign inventoryFeign;

    @Autowired
    public ProductService(ProductRepository productRepo,CategoryRepository categoryRepository,InventoryFeign inventoryFeign){
        this.productRepository=productRepo;
        this.categoryRepository=categoryRepository;
        this.inventoryFeign=inventoryFeign;
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product findById(Long id) {
        Optional<Product> obj = productRepository.findById(id);
        if(obj.isPresent()){
//            System.out.println("Product found in service layer");
//            System.out.println(obj.get());
            return obj.get();
        }else{
//            System.out.println("Exception adikuran");
            throw new ProductNotFoundException("Product with id "+id+" not there");
        }
    }

    public Product add(@Valid Product product) {
        Optional<Category> optionalCategory = categoryRepository.findById(product.getCategory().getId());
        if (optionalCategory.isEmpty()) {
            throw new CategoryNotFoundException("Category with id " + product.getCategory().getId() + " not found");
        }

        product.setCategory(optionalCategory.get());
        Product savedProduct = productRepository.save(product);
        Inventory inventory = new Inventory(savedProduct.getId(), 40);
        inventoryFeign.addInventory(inventory);

        return savedProduct;
    }


    public Product update(Long id,Product product) {
        Product pro = findById(id);
        pro.setName(product.getName());
        pro.setPrice(product.getPrice());
        pro.setCategory(product.getCategory());
        pro.setExpDate(product.getExpDate());
        return productRepository.save(pro);
    }

    public String deleteById(Long id) {
        Optional<Product> obj = productRepository.findById(id);
        if(obj.isPresent()){
            return "Deleted Successfully";
        }
        else{
            throw new ProductNotFoundException("Product with id "+id+" not there");
        }
    }

    public Product findByName(String name) {
        Optional<Product> pro = productRepository.findByName(name);
        if(pro.isPresent()){
            return pro.get();
        }
        else{
            throw new ProductNotFoundException("Product with name "+name+" not found");
        }
    }


    public List<Product> findByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public List<Product> sortProducts(String by, boolean asc) {
        if ("price".equalsIgnoreCase(by)) {
            return asc ? productRepository.findAllByOrderByPriceAsc() : productRepository.findAllByOrderByPriceDesc();
        } else if ("date".equalsIgnoreCase(by)) {
            return asc ? productRepository.findAllByOrderByExpDateAsc() : productRepository.findAllByOrderByExpDateDesc();
        } else {
            throw new IllegalArgumentException("Unsupported sort field: " + by);
        }
    }


    public List<Product> findByRange(int min, int max) {
        return productRepository.findByRange(min, max);
    }

    public List<Product> findFull() {
        return productRepository.findAll();
    }
}
