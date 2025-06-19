# Proyecto de Gestión de Pacientes - Clínica Veterinaria

Este proyecto lo hice como parte de mis prácticas con Java y JavaFX. La idea es poder gestionar pacientes (mascotas) en una clínica veterinaria, guardando datos como nombre, especie, color, peso, edad, etc. También tiene un campo de signos particulares por si el animal llega con alguna condición especial.

Todo se conecta con una base de datos MySQL. Hay funciones para agregar, eliminar pacientes y reiniciar el ID si se vacía la tabla. También se puede ver la información más detallada al hacer doble clic sobre los signos.

## Tecnologías

- Java 17 o superior
- JavaFX 24
- MySQL
- NetBeans (usé esta IDE para el desarrollo)

## Cómo correrlo

1. Importar el proyecto en NetBeans.
2. Asegurarse de tener creada la base de datos `clinica_veterinaria` en MySQL.
3. Correr el archivo principal.
4. Si es necesario, cambiar los datos de conexión en la clase `Conexion.java`.

## Notas

- El ID solo se reinicia si no hay registros en la tabla.
- Todos los datos se manejan en tiempo real con la base de datos.
