# API de Inventarios

## Descripción
Esta es una API RESTful construida con Spring Boot 3 y Java 21 para la gestión de inventario de productos. Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los productos y cuenta con documentación interactiva integrada mediante Swagger (OpenAPI).

## Estructura del Proyecto
El proyecto sigue una arquitectura clásica por capas para mantener el código organizado y escalable:

*   **`com.inventarios.Controller`**: Contiene los controladores REST (`ProductoController`) que manejan las peticiones HTTP, definen las rutas de la API y exponen la documentación de Swagger. También incluye el manejador global de excepciones.
*   **`com.inventarios.Service` / `ServiceImpl`**: Contiene la lógica de negocio. Se compone de interfaces (`IProductoService`) y sus implementaciones (`ProductoServiceImpl`).
*   **`com.inventarios.Repository`**: Contiene las interfaces de Spring Data JPA para la interacción directa con la base de datos MySQL.
*   **`com.inventarios.modelo`**: Contiene las entidades JPA que mapean las tablas de la base de datos (Ej. la entidad `Producto`).
*   **`com.inventarios.config`**: Contiene configuraciones globales del proyecto, como la política de CORS (`CorsConfig.java`) y la configuración de Spring Security (`SecurityConfig.java`).

## Requisitos Previos
*   Java 21 o superior.
*   Maven 3.9+
*   MySQL 8.0 (si se corre nativamente) o Docker Desktop (si se usa contenedores).

---

## 🚀 Cómo ejecutar en Desarrollo Local (IDE)

Si deseas trabajar en el código o probar la API directamente en tu computadora usando tu IDE favorito (IntelliJ, VS Code, NetBeans, Eclipse), sigue estos pasos:

1.  **Clonar el repositorio**:
    ```bash
    git clone <url-del-repositorio>
    cd inventarios
    ```

2.  **Configurar Variables de Entorno**:
    El proyecto utiliza variables de entorno para mantener seguras las credenciales.
    *   Copia el archivo `.env.example` y cámbiale el nombre a `.env` (este archivo no se subirá a Git).
    *   Reemplaza los valores con las credenciales de tu MySQL local:
        ```env
        DB_URL=jdbc:mysql://localhost:3306/inventario_db?createDatabaseIfNotExist=true
        DB_USERNAME=tu_usuario
        DB_PASSWORD=tu_contraseña
        ```

3.  **Ejecutar la API**:
    *   Asegúrate de que tu servidor MySQL local esté corriendo en el puerto 3306.
    *   Ejecuta la clase principal `InventariosApplication.java` desde tu IDE. *(Asegúrate de que tu IDE cargue las variables del archivo `.env`)*.
    *   La API arrancará en el puerto `8080`.

---

## 🐳 Cómo ejecutar con Docker

Si quieres levantar el proyecto rápidamente sin instalar MySQL o configurar tu entorno Java, puedes utilizar Docker. El proyecto ya incluye un `Dockerfile` multi-etapa y un `docker-compose.yml`.

1.  Asegúrate de tener tu archivo `.env` configurado en la raíz del proyecto (Docker leerá las contraseñas de ahí).
2.  Abre una terminal en la raíz del proyecto y ejecuta:
    ```bash
    docker-compose up --build -d
    ```
3.  Esto descargará MySQL, compilará el código Java, generará la imagen y levantará ambos servicios conectados entre sí. 
    *   La base de datos MySQL se expondrá en el puerto `3307` de tu host para no chocar con instalaciones locales.
    *   La API estará disponible en el puerto `8080`.

Para detener todo, simplemente ejecuta:
```bash
docker-compose down
```

---

## 📖 Documentación de la API (Swagger)

Con la aplicación en ejecución (por cualquier método), puedes explorar, leer la descripción de los endpoints y probarlos gráficamente a través de la interfaz de Swagger UI:

👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**
