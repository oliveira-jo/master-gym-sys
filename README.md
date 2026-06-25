
# Mastergymsys

A system to help manage a gym, providing complete registration of students, enrollments, and plans.

- CRUD Users
- CRUD Students
- CRUD Modalities
- CRUD Graduations
- CRUD Subscriptions
- CRID Enrollments (Student, Modality, Subscrition)
- Invoice Generation, Invoice Search and payments
- Report Generation

## Start Project With Flyway

- Postgres and flyway config properties
- Flyway for generate and manage scripts sql
- Scrips in: resources ->  db -> migration
- Patther: VX__Name


## Create Entities

- Pack Domain 

## Data persistem

- Repository - Persistence with postgresql

## CRUD Students  

- Create, Read, Update, Delete
- Repository, Service, DTO, Controller
- Generate endpoints

## Testing with Postman

- Testing endpoints in Postman
- JSON

## Validation In Project and Global Exception Handler

- Pack Validation
- Add validation in requests dtos
  - @NotBlank, @Email, ...
- @Valid in controller
- Global Exception Handler and Custom Exceptions

## Filters With Specifications

Instead of creating multiple methods within the repository, a specification is created.

- Create StudentFilterRequest
- Create pack specification
- Create StudentSpecification

````JAVA
public class StudentSpecification {
  public static Specification<Student> withFilter(StudentFilterRequest filter) {
    return Specification.<Student>unrestricted()
        .and(nameContem(filter.name()))
        .and(emailContem(filter.email()))
        .and(phoneContem(filter.phone()))
        .and(cityContem(filter.city()))
        .and(stateContem(filter.state()));
  }

  private static Specification<Student> nameContem(String name) {
    return (root, query, cb) -> {
      if (name == null || name.isBlank()) {
        return null;
      }
      return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    };
  }
}
// others methodos with data ...
````

- In StudentRepository add JpaSpecificationExecutor

````JAVA
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {}
````

- In StudentService

Update method findAll

````JAVA
 @Transactional(readOnly = true)
  public Page<StudentResponseDTO> findAll(StudentFilterRequest filter, @NonNull Pageable pageable) {
    return studentRepository.findAll(StudentSpecification.withFilter(filter), pageable)
        .map(StudentResponseDTO::new);
  }
````

- In StudentController

Update method findAll

````JAVA
  @GetMapping
  public ResponseEntity<Page<StudentResponseDTO>> findAll(StudentFilterRequest filter, Pageable pageable){
    return ResponseEntity.ok(studentService.findAll(filter, pageable));
  }
````

### Testing in Postman
````
http://localhost:8080/students?name=Metz&page=0&size=10&sorte=name,asc
````

- The JPA specification offers a huge improvement in coding.
- It sets it apart from other professionals who use it directly within the repository.

## Projetions and Native Query

- Many developers are using four requests to build a dashboard as an example.
- We can do something much more sophisticated and lighter using projections.

- Create pack projection
- Create MonthlyBillingProjection

````JAVA
public interface MonthlyBillingProjection {
  String getMonth();
  BigDecimal getTotal();
}
````

- Create ReportRepository

````JAVA
public interface ReportRepository extends Repository<Payment, Long> {

  @Query(value = """
        SELECT
          TO_CHAR(due_date, 'YYYY-MM') AS month,
          SUM(amount) AS total
        FROM payments
        WHERE status = 'PAID'
        GROUP BY TO_CHAR(due_date, 'YYYY-MM')
        ORDER BY month
      """, nativeQuery = true)
  List<MonthlyBillingProjection> monthlyBilling();
  // others
}
````

- Create ReportController

````JAVA
@RestController
@RequestMapping("/reports")
public class ReportController {
  @Autowired
  private ReportRepository reportRepository;

  @GetMapping("/monthlyBilling")
  public ResponseEntity<List<MonthlyBillingProjection>> monthlyBilling() {
    return ResponseEntity.ok().body(reportRepository.monthlyBilling());
  }
}
````

## Creating a New Migration

  Creating a v3 migration for test the financial:
  - Enrollments
  - enrollments_modalities
  - Payments
  - Report 

## Opan Api and Documentation
  - Configuration Open Api
  - Generate documentation as a interface 
  - And implementation this in Controller

## Monorepo Structure
````
├── backend
│   └── spring-boot
│
├── frontend
│   └── angular
│
├── docs
│
└── docker
````

## Frontend Structure
````
frontend/
│
├── src/
│
│   ├── assets/
│   │
│   ├── environments/
│   │
│   ├── styles.css
│   │
│   └── app/
│
│       ├── core/
│       │   ├── guards/
│       │   ├── interceptors/
│       │   ├── models/
│       │   ├── services/
│       │   └── constants/
│       │
│       ├── shared/
│       │   ├── components/
│       │   ├── pipes/
│       │   ├── directives/
│       │   └── interfaces/
│       │
│       ├── layout/
│       │   ├── home/
│       │   ├── navbar/
│       │   ├── footer/
│       │   └── sidebar/
│       │
│       ├── features/
│       │
│       │   ├── auth/
│       │   │   └── login/
│       │   │
│       │   ├── usuarios-user/
│       │   │
│       │   ├── alunos-student/
│       │   │
│       │   ├── modalidades-modality/
│       │   │
│       │   ├── planos-subscription/
│       │   │
│       │   ├── matriculas-enrollment/
│       │   │
│       │   ├── faturamento-/
│       │   │
│       │   ├── pagamentos-payment/
│       │   │
│       │   └── relatorios-report/
│       │
│       ├── app.component.ts
│       ├── app.routes.ts
│       └── app.config.ts
│
├── angular.json
├── package.json
└── tsconfig.json
````

