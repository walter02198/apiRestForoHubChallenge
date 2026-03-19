Voll Clinic API
API REST para la gestión de consultas médicas de la clínica Voll. Permite registrar médicos, pacientes, agendar citas y gestionar la autenticación mediante JWT.

🛠️ Tecnologías
Java 17
Spring Boot 3.3.0
Spring Data JPA (Hibernate)
Spring Security con JWT
MySQL (producción) / H2 (pruebas)
Flyway (migraciones de base de datos)
SpringDoc OpenAPI (documentación Swagger)
Lombok
Maven
📋 Requisitos
Java 17 o superior
Maven 3.x
MySQL 8.x (para entorno de producción)
Git
🚀 Cómo ejecutar
# Clonar el repositorio
git clone https://github.com/usuario/api---curso-documentar-api.git
cd api---curso-documentar-api

# Compilar el proyecto
./mvnw clean compile

# Ejecutar la aplicación
./mvnw spring-boot:run
La API estará disponible en http://localhost:8080

📌 Endpoints
Autenticación
Método	Ruta	Descripción
POST	/login	Autentica usuario y retorna token JWT
Médicos
Método	Ruta	Descripción
GET	/medicos	Lista médicos activos
GET	/medicos/{id}	Obtiene datos de un médico
POST	/medicos	Registra un nuevo médico
PUT	/medicos	Actualiza datos de un médico
DELETE	/medicos/{id}	Desactiva un médico
Pacientes
Método	Ruta	Descripción
GET	/pacientes	Lista pacientes activos
GET	/pacientes/{id}	Obtiene datos de un paciente
POST	/pacientes	Registra un nuevo paciente
PUT	/pacientes	Actualiza datos de un paciente
DELETE	/pacientes/{id}	Desactiva un paciente
Consultas
Método	Ruta	Descripción
GET	/consultas	Lista todas las consultas
POST	/consultas	Agenda una nueva consulta
DELETE	/consultas	Cancela una consulta existente
📖 Documentación API
La documentación interactiva de Swagger está disponible en:

Swagger UI: http://localhost:8080/swagger-ui.html
OpenAPI JSON: http://localhost:8080/v3/api-docs
🔐 Autenticación
La API utiliza autenticación JWT. Para acceder a los endpoints protegidos:

Realizar una petición POST a /login con credenciales:
{
  "login": "usuario@ejemplo.com",
  "clave": "contraseña"
}
Incluir el token JWT en las siguientes peticiones:
Authorization: Bearer <token_jwt>
📁 Estructura del proyecto
src/main/java/med/voll/api/
├── controller/           # Controladores REST
├── domain/              # Entidades y lógica de negocio
│   ├── medico/          # Médico
│   ├── paciente/        # Paciente
│   ├── consulta/        # Consulta médica
│   ├── direccion/       # Dirección (embeddable)
│   └── usuarios/        # Usuarios del sistema
├── infra/
│   ├── errores/         # Manejo global de excepciones
│   ├── security/        # Configuración JWT y seguridad
│   └── springdoc/       # Configuración OpenAPI
└── ApiApplication.java  # Punto de entrada
🗄️ Base de datos
El proyecto utiliza Flyway para migraciones. Las migraciones se encuentran en:

src/main/resources/db/migration/

Autor
Walter Valverde