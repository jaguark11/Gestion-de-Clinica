# Script de base de datos - clinica_veterinaria_completo.sql

Este archivo contiene todo el esquema necesario para crear la base de datos que usa el sistema de gestión de la clínica veterinaria.

### ¿Qué contiene este script?

- Crea la base de datos `clinica_veterinaria`
- Crea todas las tablas necesarias con sus relaciones (propietario, paciente, historial clínico, tratamiento, observaciones, etc.)
- Incluye claves foráneas, campos de control como `eliminado` y timestamps automáticos

---

### Cómo usarlo paso a paso

1. Abre **MySQL Workbench** o tu gestor de base de datos favorito.
2. Asegúrate de estar conectado a tu servidor local (localhost).
3. Abre el archivo `clinica_veterinaria_completo.sql`.
4. Ejecuta todo el script (Ctrl + Shift + Enter en Workbench).
5. Verifica que se haya creado la base de datos `clinica_veterinaria` y todas sus tablas.

---

### Consideraciones

- No hay datos cargados por defecto. Solo estructura.
- Si ya existe una base de datos con ese nombre, se eliminará al ejecutar el script (`DROP DATABASE IF EXISTS`).
- El sistema Java conecta a esta base de datos, así que asegúrate de que:
  - El nombre de la base de datos sea exactamente `clinica_veterinaria`
  - El usuario y contraseña en el código Java coincidan con los de tu servidor

---

### ¿Qué pasa después?

Una vez creada la base de datos, puedes ejecutar el proyecto Java sin problemas, y este empezará a leer y escribir directamente sobre estas tablas.
