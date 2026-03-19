# AGENTS.md - ForoHub API

This document provides guidelines for agentic coding assistants working in this repository.

## Project Overview

- **Type**: Java REST API (Spring Boot 3.3.1)
- **Build Tool**: Maven
- **Java Version**: 17
- **Database**: MySQL with Flyway migrations
- **Authentication**: JWT (Auth0 java-jwt)

## Build, Test, and Lint Commands

### Build
```bash
./mvnw clean install
./mvnw clean package
```

### Run
```bash
./mvnw spring-boot:run
```

### Test Commands
```bash
# Run all tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=ApiRestApplicationTests

# Run a specific test method
./mvnw test -Dtest=ApiRestApplicationTests#contextLoads

# Run tests with Maven surefire (alternative)
./mvnw surefire:test -Dtest=ClassName
```

### Code Quality
```bash
# Format code (if spotless plugin is added)
./mvnw spotless:apply

# Compile only
./mvnw compile
```

## Code Style Guidelines

### Project Structure
```
src/
├── main/
│   ├── java/foro/hub/apiRest/
│   │   ├── controller/      # REST controllers
│   │   ├── domain/          # Entities, repositories, DTOs
│   │   │   ├── topicos/     # Topics-related code
│   │   │   ├── usuarios/    # Users-related code
│   │   │   └── respuesta/   # Responses-related code
│   │   ├── infra/
│   │   │   ├── errores/     # Exception handlers
│   │   │   ├── security/    # JWT, security configs
│   │   │   └── springdoc/   # OpenAPI docs
│   │   └── ApiRestApplication.java
│   └── resources/
│       ├── application.properties
│       └── db.migration/     # Flyway migrations
└── test/
```

### Package Naming
- Use Spanish terminology (e.g., `topicos`, `usuarios`, `respuesta`)
- Package path: `foro.hub.apiRest.<module>.<feature>`

### Class Naming Conventions

| Type | Convention | Example |
|------|------------|---------|
| Controllers | `XxxController` | `TopicoController` |
| Entities | `Xxx` (entity name in JPA) | `Topico`, `Usuario` |
| Repositories | `IXxxRepository` | `ITopicoRepository` |
| DTOs (records) | `DatosXxx` | `DatosRegistroTopicos` |
| Enums | `Xxx` | `Status`, `Curso` |
| Services | `XxxService` | `TokenService` |
| Config | `XxxConfigurations` | `SecurityConfigurations` |

### DTOs (Data Transfer Objects)
- Use Java `record` type for immutable DTOs
- Name pattern: `Datos[Accion][Entity]`
  - `DatosRegistroTopicos` - input for creating
  - `DatosRespuestaTopico` - single response
  - `DatosListadoTopicos` - list response
  - `DatosActualizarTopico` - update input
- Validation: Use Jakarta Bean Validation (`@NotBlank`, `@NotNull`)
- Place inside domain module: `domain.topicos.DatosRegistroTopicos`

### Annotations
- **Lombok**: `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@EqualsAndHashCode`
- **JPA**: `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`, `@ManyToOne`, `@OneToMany`
- **Spring**: `@RestController`, `@RequestMapping`, `@Service`, `@Configuration`, `@Autowired`
- **Validation**: `@Valid`, `@NotBlank`, `@NotNull`
- **Transactional**: Use `@Transactional` on write operations (POST, PUT, DELETE)

### Import Organization
1. Java standard library
2. Third-party libraries (Spring, Jakarta, etc.)
3. Internal domain classes
4. Group by package

### Naming Conventions
- Variables/Methods: camelCase
- Classes: PascalCase
- Constants: UPPER_SNAKE_CASE
- Database columns: snake_case in SQL, camelCase in Java entities
- Tables: snake_case in database

### Entity Conventions
```java
@Table(name="table_name")
@Entity(name="EntityName")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class EntityName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private EnumType enumField;
}
```

### Repository Pattern
- Extend `JpaRepository<Entity, Long>`
- Use Spring Data JPA query methods: `findByActivoTrue()`, `existsByTituloAndMensaje()`
- Use `@Query` for complex queries

### Controller Conventions
```java
@RestController
@RequestMapping("/resource")
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
@SecurityRequirement(name = "bearer-key")
public class XxxController {
    @Autowired
    private IRepository repository;
    
    @PostMapping
    public ResponseEntity<Xxx> crear(@Valid @RequestBody DatosRegistroXxx datos) { }
    
    @GetMapping
    public ResponseEntity<Page<DatosListadoXxx>> listado(@PageableDefault(size = 20) Pageable paginacion) { }
    
    @PutMapping
    @Transactional
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizarXxx datos) { }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id) { }
}
```

### Exception Handling
- Use `@RestControllerAdvice` for global exception handling
- Handle common exceptions:
  - `EntityNotFoundException` → 404
  - `MethodArgumentNotValidException` → 400 with validation errors
  - `ValidationException` → 400 with message
  - Custom `ValidationDeIntegridad` → 400 with message

### Security
- JWT authentication via `SecurityFilter`
- Stateless session policy
- Public endpoints: `/login`, `/swagger-ui/**`, `/v3/api-docs/**`
- All other endpoints require authentication

### Database Migrations
- Use Flyway with SQL migrations
- Location: `src/main/resources/db.migration/`
- Naming: `V1__description.sql`, `V2__description.sql`

### Testing
- Use `@SpringBootTest` for integration tests
- Use JUnit 5 (`org.junit.jupiter.api.Test`)
- Test file location: `src/test/java/foro/hub/apiRest/`

### Configuration
- Database credentials in `application.properties` (or use environment variables)
- JWT secret via `${JWT_secret}` environment variable
- Server runs on port 8080 (default)
