import org.springframework.stereotype.Controller;

@Controller
@RequestMapping(path = "/api/Product")
public class ProductsController {

    @Autowired
    private ProductRepository ProductRepository;

    @GetMapping(path = "")
    public @ResponseBody Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Product getProduct(@PathVariable(value = "id") Integer id) {
        Optional<Product> Product = ProductRepository.findById(id);
        return Product.get();
    }

    @PostMapping(path = "/")
    public @ResponseBody String createProduct(@RequestBody Product Product) {
        ProductRepository.save(Product);
        return "Saved";
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody String updateProduct(@PathVariable(value = "id") Integer id, @RequestBody Product ProductDetails) {

        Optional<Product> optionalProduct = ProductRepository.findById(id);
         Product Product = optionalProduct.get();

        Product.setFirstName(ProductDetails.getFirstName());
        Product.setLastName(ProductDetails.getLastName());
        Product.setDateOfBirth(ProductDetails.getDateOfBirth());

        ProductRepository.save(ProductDetails);
        return "Updated";
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody String deleteProduct(@PathVariable(value = "id") Integer id) {
        ProductRepository.deleteById(id);
        return "Deleted";
    }

}
    
}
