# CourseManageSystem
This is the OOP task, which implement some function on managing courses and users, based on java.  

# Functions
simple user login and permission management.  
course management: add, remove, update.  
user managament: add, update.  
select course with maintainance of user information.  
  
# Directory
.  
├── controller  
│   └── CourseManageSystem.java  
├── dao  
│   ├── CourseDatabaseOperateWrapper.java  
│   ├── Database.java  
│   ├── SerializationFileDatabase.java  
│   └── UserDatabaseOperateWrapper.java  
├── Driver.java  
├── entity  
│   ├── AdminInformation.java  
│   ├── Admin.java  
│   ├── Course.java  
│   ├── Information.java  
│   ├── StudentInformation.java  
│   ├── Student.java  
│   └── User.java  
├── exception  
│   ├── CountException.java  
│   └── CreateFileException.java  
└── service  
    ├── CourseService.java  
    └── UserService.java  
  
5 directories, 17 files  
  
# Architecture
- /controller 控制层  
- /dao 可持续层  
- /entity 实例层  
- /exception 异常类  
- /service 服务类  
Driver.java 驱动类  
  
# Design methods
- Singleton method:  
Database  
SerializationFileDatabase  
  
- Decorator method:  
CourseDatabaseOperateWrapper  
UserDatabaseOperateWrapper  
