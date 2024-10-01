1. A course management system based on SpringBoot JPA, React, MySQL, Redis and RabbitMQ, which implements the addition, 
   deletion, modification and query of courses. The system is divided into three types: students, teachers and 
   administrators, and meets the following functions:

   (1) Display the code of the entity layer, Dao layer, Controller layer, Service layer and ServiceImpl layer, and 
       implement the createItem(), deleteItem(), updateItem(), getAllItem(), getItemById() methods of the physical 
       examination items.

   1. Entity Layer (Course Entity)
```java
    @Entity
    @Table(name = "courses")
    public class Course {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String courseName;
        private String description;
        private String teacher;
    
        // Constructors, Getters, Setters
        public Course() {}
        public Course(String courseName, String description, String teacher) {
            this.courseName = courseName;
            this.description = description;
            this.teacher = teacher;
        }
        // Getters and Setters
    }
```
   2. DAO Layer (Repository Interface)
```java
    @Repository
    public interface CourseRepository extends JpaRepository<Course, Long> {
    }
```
   3. Service Layer (Service Interface)
```java
    public interface CourseService {
        Course createItem(Course course);
        void deleteItem(Long id);
        Course updateItem(Long id, Course course);
        List<Course> getAllItems();
        Course getItemById(Long id);
    }
```
   4. ServiceImpl Layer (Service Implementation)
```java
    @Service
    public class CourseServiceImpl implements CourseService {
    
        @Autowired
        private CourseRepository courseRepository;
    
        @Override
        public Course createItem(Course course) {
            return courseRepository.save(course);
        }
    
        @Override
        public void deleteItem(Long id) {
            courseRepository.deleteById(id);
        }
    
        @Override
        public Course updateItem(Long id, Course course) {
            Course existingCourse = courseRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            existingCourse.setCourseName(course.getCourseName());
            existingCourse.setDescription(course.getDescription());
            existingCourse.setTeacher(course.getTeacher());
            return courseRepository.save(existingCourse);
        }
    
        @Override
        public List<Course> getAllItems() {
            return courseRepository.findAll();
        }
    
        @Override
        @Cacheable(value = "courses", key = "#id")
        public Course getItemById(Long id) {
            return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        }
    }
```
   5. Controller Layer
```java
    @RestController
    @RequestMapping("/api/courses")
    public class CourseController {
    
        @Autowired
        private CourseService courseService;
    
        @PostMapping
        public ResponseEntity<Course> createCourse(@RequestBody Course course) {
            return new ResponseEntity<>(courseService.createItem(course), HttpStatus.CREATED);
        }
    
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
            courseService.deleteItem(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    
        @PutMapping("/{id}")
        public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
            return new ResponseEntity<>(courseService.updateItem(id, course), HttpStatus.OK);
        }
    
        @GetMapping
        public ResponseEntity<List<Course>> getAllCourses() {
            return new ResponseEntity<>(courseService.getAllItems(), HttpStatus.OK);
        }
    
        @GetMapping("/{id}")
        public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
            return new ResponseEntity<>(courseService.getItemById(id), HttpStatus.OK);
        }
    }
```
   (2) Configure the RedisConfig class and add it to the Service layer with annotations to 
       implement cache query of the project.

   1. Redis Configuration (RedisConfig.java)
```java
    @Configuration
    @EnableCaching
    public class RedisConfig extends CachingConfigurerSupport {
    
        @Bean
        public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
            RedisTemplate<String, Object> template = new RedisTemplate<>();
            template.setConnectionFactory(connectionFactory);
            return template;
        }
    
        @Bean
        public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
            RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory).build();
            return redisCacheManager;
        }
    }
```
   2. ServiceImpl Layer, annotate the caching behavior for query methods.
```java
    @Service
    public class CourseServiceImpl implements CourseService {
    
        @Autowired
        private CourseRepository courseRepository;
    
        @Cacheable(value = "courses", key = "#id")
        @Override
        public Course getItemById(Long id) {
            return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        }
    
        @CacheEvict(value = "courses", key = "#id")
        @Override
        public void deleteItem(Long id) {
            courseRepository.deleteById(id);
        }
    }
```

   (4) Display the data transmission to SpringBoot JPA through React.
