**Spring boot tutorial with Maven**

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
 
 _Create new APIs to_ 
 1. Get stores
 2. Create a store 
 3. Create a new cart 
 4. Create a entry 
 5. View entry 
 6. Get address 
 7. Create address


**Spring with Docker**

1.  The below changes are added to build docker image of the spring boot application in pom.xml
  
  ```
    <properties>
          <java.version>1.8</java.version>
          <!-- Build a Docker Image with Maven-->
          <docker.image.prefix><name of your docker id></docker.image.prefix>
      </properties>
   ```
   
    ```
        <!-- Build a Docker Image with Maven-->
            <plugin>
                <groupId>com.spotify</groupId>
                <artifactId>dockerfile-maven-plugin</artifactId>
                <version>1.4.9</version>
                <configuration>
                    <repository>${docker.image.prefix}/${project.artifactId}</repository>
                </configuration>
            </plugin>
            <!-- To ensure the jar is unpacked before the docker image is created we add some configuration for the dependency plugin:-->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-dependency-plugin</artifactId>
                <executions>
                    <execution>
                        <id>unpack</id>
                        <phase>package</phase>
                        <goals>
                            <goal>unpack</goal>
                        </goals>
                        <configuration>
                            <artifactItems>
                                <artifactItem>
                                    <groupId>${project.groupId}</groupId>
                                    <artifactId>${project.artifactId}</artifactId>
                                    <version>${project.version}</version>
                                </artifactItem>
                            </artifactItems>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
     ``` 
     
 
2.  Docker file was created -> DockerFile

3.  Run the below commands to build a docker image
   ``` 
        ./mvnw package && java -jar target/gs-spring-boot-docker-0.1.0.jar
        ./mvnw install dockerfile:build
   ``` 
    
4. Ensure you have logged into docker
   ``` 
        docker login
        docker push <repo name>/<image name>
   ```      