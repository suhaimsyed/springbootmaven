1. Navigate to the project folder and perform the below actions

    brew install maven
    mvn install
    mvn spring-boot:run
    
2. Access http://localhost:8080/api/carts

3. Create a new Store Entity

    ```
    @Entity
    public class Store {
    @Id
    @GeneratedValue
    private Long id;
    String code;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    Address address;
    // add getters and setters
    ```
    
 4. Create a new Repository for store
    
    ```
    @Repository
    public interface StoreRepository extends JpaRepository<Store,Long> {
    }

    ```
    
 5. Create a new Service for store
 
    ```
    @Service
    public class AddressService {

    private final StoreRepository storeRepository;

    public StoreRepository(StoreRepository storeRepository) {
       this.storeRepository = storeRepository;
    }

    public List<Store> getStore() {
        return storeRepository.findAll();
    }

    public Optional<Store> getStoreById(Long id) {
        return storeRepository.findById(id);
    }

    public Store saveAddress(Store store) {
        return storeRepository.save(store);
    }
    ```
    
  6. Add store to cart entity
  
    ```
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    Store store;

    ```
    
  7. Update the initializer bean
     ```
    @Bean
    ApplicationRunner init(CartService cartService , AddressService addressService,StoreService storeService) {
     
    ```    
 
 Create new APIs to 
 1. Get stores
 2. Create a store 
 3. Create a new cart 
 4. Create a entry 
 5. View entry 
 6. Get address 
 7. Create address
