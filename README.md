# TP Integrador Final - BAZAR


#### Objetivo

El objetivo de este proyecto integrador final es el de validar los conocimientos prácticos y
técnicos referidos al desarrollo de APIs en el lenguaje de programación Java mediante Spring
Boot para el curso “Desarrollo de APIs en Java con Spring Boot” de la TodoCode Academy.

#### Escenario

Un bazar ha incrementado en gran medida sus ventas. Dado esto y que le está siendo casi
imposible registrar las mismas y manejar el stock de sus productos de forma manual, necesita
del desarrollo de una aplicación que le permita realizar esta tarea.
La dueña del bazar manifiesta que todas las operaciones que tenga la aplicación se deben
poder realizar mediante dos tipos de clientes http distintos:

• Una aplicación web, cuyo frontend desarrollará un programador amigo (no será parte
de nuestra tarea como desarrolladores backend).\
• Una aplicación Mobile que será implementada a futuro.

Cada una de estas app representa a los dispositivos que ella y sus empleados manejan
actualmente. En síntesis: una computadora y varios celulares.
Dada esta situación particular y de que necesita utilizar el mismo backend para ambas
opciones, solicita el desarrollo de una API.

#### Modelado
A partir del relevamiento que ha llevado a cabo un analista funcional, se detectaron que serán
necesarias las siguientes clases:\
• Producto\
• Venta\
• Cliente

#### Requerimientos/Funciones/Endpoints

A partir del relevamiento realizado respecto al modelado, la dueña del bazar especificó que
tiene los siguientes requerimientos:

#### *Se incluiran imagenes solo para Producto por motivos demostrativos con Postman y no en las demas entidades para no saturar de imagenes, se incluiran mas imagenes en endpoints que tengan una funcion diferente a un CRUD simple.

#### 1. Poder realizar un CRUD completo de productos
a. Métodos HTTP: GET, POST, DELETE, PUT\
b. Endpoints:\
Creación: localhost:8080/productos/crear\
![Captura de pantalla 2024-06-11 130846](https://github.com/Davidr1594/Consultorio_Odontologico/assets/169404551/654b4b1f-ef8b-404d-835d-1609be44a7e3)

Lista completa de productos: localhost:8080/productos\
![Captura de pantalla 2024-06-11 130948](https://github.com/Davidr1594/Consultorio_Odontologico/assets/169404551/9b5c0160-efd8-4c4c-b86b-dd772c11e570)


Traer un producto en particular: localhost:8080/productos/{codigo_producto}\
![Captura de pantalla 2024-06-11 131252](https://github.com/Davidr1594/Consultorio_Odontologico/assets/169404551/f7b7cf2e-c512-45cf-a636-0635dfb1cf40)


Eliminación: localhost:8080/productos/eliminar/{codigo_producto}\
![Captura de pantalla 2024-06-11 131350](https://github.com/Davidr1594/Consultorio_Odontologico/assets/169404551/59751714-447f-469d-b836-af42ff9ed727)


Edición: localhost:8080/productos/editar/{codigo_producto}
![Captura de pantalla 2024-06-11 131600](https://github.com/Davidr1594/Consultorio_Odontologico/assets/169404551/99016942-130e-46de-9ab2-4354e7d14b8d)\
Despues de ser editado:\
![Captura de pantalla 2024-06-11 131639](https://github.com/Davidr1594/Consultorio_Odontologico/assets/169404551/26c9d74e-4234-4a04-9149-e8dfcce05c94)



#### 2. Poder realizar un CRUD completo de clientes
a. Métodos HTTP: GET, POST, DELETE, PUT\
b. Endpoints:\
Creación: localhost:8080/clientes/crear\
Lista completa de clientes: localhost:8080/clientes\
Traer un cliente en particular: localhost:8080/clientes/{id_cliente}\
Eliminación: localhost:8080/clientes/eliminar/{id_cliente}\
Edición: localhost:8080/clientes/editar/{id_cliente}


##### 3. Poder realizar un CRUD completo de ventas
a. Métodos HTTP: GET, POST, DELETE, PUT\
b. Endpoints:\
Creación: localhost:8080/ventas/crear\
Lista completa de ventas realizadas: localhost:8080/ventas\
Traer una venta en particular: localhost:8080/ventas/{codigo_venta}\
Eliminación: localhost:8080/clientes/eliminar/{codigo_venta}\
Edición: localhost:8080/clientes/editar/{codigo_venta}\

Nota: No es necesario para este requerimiento actualizar el stock de un producto (descontar)
al realizar una venta, ni tampoco controlar si cuenta con la cantidad disponible para vender;
sin embargo, se considerará como “plus” o extra si se desea implementar la funcionalidad.

**Esta opcion fue implementada en cada venta que se realiza, demostración:**

Productos antes de una venta donde id producto #102 tiene 5 cantidades disponible y id producto #54 tiene 20 disponibles:\
![Captura de pantalla 2024-06-11 132416](https://github.com/Davidr1594/Consultorio_Odontologico/assets/169404551/a1f4694e-416c-428f-89f5-e48523de9f0d)

Se realiza una venta con los 2 productos:\
![Captura de pantalla 2024-06-11 132839](https://github.com/Davidr1594/Consultorio_Odontologico/assets/169404551/3b9e24fd-e68b-44f6-8edb-9b73c40a474d)

Al volver traer lista productos podemos oberservar que se rebajo la cantidad disponible, asi mismo el codigo primero verifica si hay cantidades disponibles antes de realizar la venta.\
![Captura de pantalla 2024-06-11 133024](https://github.com/Davidr1594/Consultorio_Odontologico/assets/169404551/04c0c1bd-d101-4504-9c7e-3f2e89a596e9)


##### 4. Obtener todos los productos cuya cantidad_disponible sea menor a 5
a. Métodos HTTP: GET\
b. Endpoint:\
localhost:8080/productos/falta_stock\
![Captura de pantalla 2024-06-11 133120](https://github.com/Davidr1594/Consultorio_Odontologico/assets/169404551/8aeac588-45fa-41b7-9728-175ebb023445)


##### 5. Obtener la lista de productos de una determinada venta
a. Métodos HTTP: GET\
b. Endpoint:\
localhost:8080/ventas/productos/{codigo_venta}
![Captura de pantalla 2024-06-11 133240](https://github.com/Davidr1594/Consultorio_Odontologico/assets/169404551/a0d3c0a8-77fb-4a61-84a0-31771bfe5cd1)

##### 6. Obtener la sumatoria del monto y también cantidad total de ventas de un determinado día
a. Métodos HTTP: GET\
b. Endpoint:\
localhost:8080/ventas/{fecha_venta}\
![Captura de pantalla 2024-06-11 133345](https://github.com/Davidr1594/Consultorio_Odontologico/assets/169404551/0c93908b-e4e2-44a1-a3db-7c1c74169483)

##### 7. Obtener el codigo_venta, el total, la cantidad de productos, el nombre del cliente y el apellido del cliente de la venta con el monto más alto de todas.
a. Métodos HTTP: GET\
b. Endpoint:\
localhost:8080/ventas/mayor_venta\
Tener en cuenta patrón DTO para este escenario
![Captura de pantalla 2024-06-11 133449](https://github.com/Davidr1594/Consultorio_Odontologico/assets/169404551/984af3af-6a8e-4f9e-99f9-eec97b6c7964)


##### [Implementacion propia] Obtener detalle de una venta simulando la informacion que el cliente quiere ver tipo factura.
a. Métodos HTTP: GET\
b. Endpoint:\
localhost:8080/ventas/factura/{id_venta}\
![Captura de pantalla 2024-06-11 133548](https://github.com/Davidr1594/Consultorio_Odontologico/assets/169404551/3bfe0e1a-5c01-43cd-b93d-abfd1a4b78d1)
