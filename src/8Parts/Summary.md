   A course management system based on SpringBoot JPA, React, MySQL, Redis, and RabbitMQ implements course addition, 
   deletion, modification, and query. The system is divided into three types: students, teachers, and administrators.
  
   1. SpringBoot JPA, React, and MySQL: Implement Entity layer, Service layer, ServiceImpl layer, Controller layer, and 
      Dao layer. React sends requests to Controller for acceptance, and Controller sends requests to Service to call 
      ServiceImpl method to process requests. Dao is used to implement interaction with MySQL. Redis is configured to 
      implement cache query.

   2. As the number of requests increases, ThreadPool is added to control the maximum number of requests. React directly 
      sends requests to Controller for acceptance. If the number of requests is large, Controller will send Service's 
      blocking queue to manage thread load.

      ServiceImpl implements synchronous course selection. To enable multiple students to register for a popular course 
      with limited places, ReentrantLock is required. 
         (1) Timed lock acquisition is implemented. If there is no operation after the time expires, the lock is 
         automatically released. If there is an operation, the time is extended. 
         (2) Condition judgment, immediate feedback on the selection result of the previous request is obtained, and the 
         operation of the next request is determined based on the remaining condition variables of the result. 
         (3) ReentrantLock can be obtained repeatedly (4) Fair lock, the one with the longest waiting time will obtain 
      the ReentrantLock first.

      ServiceImpl implements asynchronous course selection, which enables students to add courses to their favorites. 
      Using synchronized locks is simple and easy to read, without considering conditions and timing.

      Synchronous course selection implemented by ReentrantLock and asynchronous course selection implemented by 
      synchronized have different API ports handleSyncSelection and handleAsyncSelection in the React code.

   3. In a distributed system, when multiple instances of an application need to access shared resources across multiple 
      nodes and are easy to expand and modify, add Redis distributed locks and RabbitMQ. Both are components and services 
      of distributed systems. RabbitMQ replaces the BlockingQueue of a single JVM communication, and Redis distributed 
      locks replace the ReentrantLock and Synchronized locks of a single JVM communication.
