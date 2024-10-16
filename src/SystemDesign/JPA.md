1. Warehouse commodity management system based on SpringBoot JPA.
   The system has commodity management, commodity classification, order management, and order delivery. 
   The commodities are stored in different warehouses across the country. The country is divided into the north, south, 
   west, and east regions. There will be several warehouses in each region. Not every warehouse has all the commodities. 
   Warehouses classify commodities according to type.

2. Design a program to decide which warehouses will ship to customers, ensuring that the number of shipments is small 
   and the orders can be delivered to customers in a short time.
(1) If the commodities are in different warehouses and different regions, customers want all commodities to be 
    delivered at one time instead of several times. Consider retrieving the goods from the nearest warehouse.
(2) Choose a method to store the relationship between regions, warehouses, and commodities
(3) Write a program to decide which warehouse to ship from based on the customer's order. The fewer the number of 
    shipments, the better
(4) Consider fast delivery of orders

Repository
```java
@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    // look for warehouse by region
    List<Warehouse> findByRegion(Region region);
    
    // look for if out of stock by id of warehouse and product
    @Query("SELECT w FROM Warehouse w JOIN w.products p WHERE p.id = :productId")
    List<Warehouse> findWarehousesWithProduct(@Param("productId") Long productId);
}

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
```
Service
```java
public interface ShippingService {
    Warehouse determineWarehouseForOrder(Order order);
}
```
ServiceImpl
```java
@Service
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public Warehouse determineWarehouseForOrder(Order order) {
        // Simple algorithm: according to customer's location, select closest warehouse and enough products 
        // as possible as I can
        Map<Warehouse, Integer> warehouseShippingCount = new HashMap<>();

        for (OrderItem item : order.getItems()) {
            List<Warehouse> availableWarehouses = warehouseRepository.findWarehousesWithProduct(item.getProduct().getId());
            
            Warehouse closestWarehouse = null;
            double minDistance = Double.MAX_VALUE;
            
            for (Warehouse warehouse : availableWarehouses) {
                double distance = calculateDistance(order.getCustomerLat(), order.getCustomerLng(),
                                                    warehouse.getLatitude(), warehouse.getLongitude());
                if (distance < minDistance) {
                    minDistance = distance;
                    closestWarehouse = warehouse;
                }
            }
            
            warehouseShippingCount.put(closestWarehouse, warehouseShippingCount.getOrDefault(closestWarehouse, 0) + 1);
        }
        
        // select warehouse including most products
        return warehouseShippingCount.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElseThrow(() -> new RuntimeException("No suitable warehouse found for the order"));
    }

    private double calculateDistance(double lat1, double lng1, double lat2, double lng2) {
        // calculate length from warehouse to customer
    }
}

```

Controller
```java
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private ShippingService shippingService;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        Warehouse warehouse = shippingService.determineWarehouseForOrder(order);
        return ResponseEntity.ok("Order will be shipped from warehouse: " + warehouse.getName());
    }
}

```
Entity:
```java
@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    // specific warehouse in region
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;
    private String address;
    private double latitude;
    private double longitude;
}
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String sku;
    // specific product in warehouse
    @ManyToMany
    @JoinTable(
        name = "warehouse_product",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "warehouse_id")
    )
    private List<Warehouse> warehouses;
}
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderDate;
    private String customerAddress; 
    private double customerLat;
    private double customerLng;
    @OneToMany
    private List<OrderItem> items;
}

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;
}
```
DBTable Creation
```sql
CREATE TABLE region (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);
CREATE TABLE warehouse (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    region_id BIGINT,
    address VARCHAR(255),
    latitude DOUBLE,
    longitude DOUBLE,
    CONSTRAINT fk_region FOREIGN KEY (region_id) REFERENCES region(id)
);
CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    sku VARCHAR(100) NOT NULL
);
CREATE TABLE warehouse_product (
    warehouse_id BIGINT,
    product_id BIGINT,
    CONSTRAINT fk_warehouse FOREIGN KEY (warehouse_id) REFERENCES warehouse(id),
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product(id)
);
CREATE TABLE `order` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_date TIMESTAMP,
    customer_address VARCHAR(255),
    customer_lat DOUBLE,
    customer_lng DOUBLE
);
CREATE TABLE order_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT,
    quantity INT,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product(id)
);
```
