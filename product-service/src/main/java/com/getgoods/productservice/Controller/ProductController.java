package com.getgoods.productservice.Controller;

import com.getgoods.productservice.Service.ProductService;
import com.getgoods.productservice.entity.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }


    @GetMapping("/paginated")
    public ResponseEntity<Page<Product>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(productService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Product>> findAll(){
        return new ResponseEntity<>(productService.findFull(),HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id){
//        System.out.println("Controller called");
        return new ResponseEntity<>(productService.findById(id),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> add(@Valid @RequestBody Product product){
        return new ResponseEntity<>(productService.add(product),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> update(@RequestBody Product product,@PathVariable Long id){
        return new ResponseEntity<>(productService.update(id,product),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>(productService.deleteById(id),HttpStatus.OK);
    }

    @GetMapping("/find/name/{name}")
    public ResponseEntity<Product> findByName(@PathVariable String name){
        return new ResponseEntity<>(productService.findByName(name),HttpStatus.OK);
    }

    @GetMapping("/find/category/{category_id}")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable Long category_id){
        return  new ResponseEntity<>(productService.findByCategoryId(category_id),HttpStatus.OK);
    }

    @GetMapping("/find/sort")
    public List<Product> sortProducts(
            @RequestParam String by,
            @RequestParam(defaultValue = "true") boolean asc) {
        return productService.sortProducts(by, asc);
    }

    @GetMapping("/find/range")
    public ResponseEntity<List<Product>> rangeProducts(
            @RequestParam(defaultValue = "0") String min,
            @RequestParam(defaultValue = "999999")String max
    ){
        return new ResponseEntity<>(productService.findByRange(Integer.parseInt(min),Integer.parseInt(max)),HttpStatus.OK);
    }



}
