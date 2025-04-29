# Repaso de Javascript

## Conceptos Básicos de JavaScript

### ¿Qué es JavaScript?
JavaScript es un lenguaje de programación interpretado que se utiliza principalmente para crear contenido dinámico en páginas web. Es uno de los pilares del desarrollo web junto con HTML y CSS.

### Variables
Las variables se utilizan para almacenar datos. En JavaScript, se pueden declarar usando `var`, `let` o `const`.

```javascript
let nombre = "Juan";
const PI = 3.1416;
var edad = 25;
```

### Tipos de Datos
JavaScript tiene varios tipos de datos, como:
- **String**: Cadenas de texto (`"Hola"`)
- **Number**: Números (`42`, `3.14`)
- **Boolean**: Valores lógicos (`true`, `false`)
- **Array**: Listas (`[1, 2, 3]`)
- **Object**: Objetos (`{ clave: "valor" }`)

### Funciones
Las funciones son bloques de código reutilizables.

```javascript
function saludar(nombre) {
    return `Hola, ${nombre}`;
}
console.log(saludar("Ana"));

const saludar = (nombre) =>{
    return `Hola, ${nombre}`;
}

console.log(saludar("Ana"));
```

### Condicionales
Se utilizan para tomar decisiones en el código.

```javascript
if (edad >= 18) {
    console.log("Eres mayor de edad");
} else {
    console.log("Eres menor de edad");
}
```

### Bucles
Los bucles permiten repetir un bloque de código.

```javascript
for (let i = 0; i < 5; i++) {
    console.log(i);
}
```

### Manipulación del DOM
JavaScript permite interactuar con el DOM para modificar el contenido de una página web.

```javascript
document.getElementById("miElemento").innerText = "Nuevo texto";
```

### Eventos
Los eventos permiten ejecutar código en respuesta a acciones del usuario.

```javascript
document.getElementById("miBoton").addEventListener("click", function() {
    alert("¡Botón clickeado!");
});
```

### Promesas
Las promesas se utilizan para manejar operaciones asíncronas.

```javascript
const miPromesa = new Promise((resolve, reject) => {
    let exito = true; // Cambiar a false para simular un error
    if (exito) {
        resolve("La operación fue exitosa");
    } else {
        reject("Hubo un error en la operación");
    }
});

miPromesa
    .then((mensaje) => {
        console.log(mensaje);
    })
    .catch((error) => {
        console.error(error);
    });

```

### Type module

En JavaScript, el atributo `type: "module"` se utiliza para indicar que un archivo JavaScript debe ser tratado como un módulo. Esto permite el uso de la sintaxis de importación y exportación para organizar el código en diferentes archivos.

#### Ejemplo de uso:

Archivo `main.js`:
```javascript
import { saludar } from './modulo.js';

console.log(saludar('Juan'));
```

Archivo `modulo.js`:
```javascript
export const saludar = (nombre) => `Hola, ${nombre}`;
```

En el archivo HTML, se debe especificar el atributo `type="module"` al incluir el script:

```html
<script type="module" src="main.js"></script>
```

#### Beneficios de los módulos:
- Permiten dividir el código en partes reutilizables.
- Evitan la contaminación del espacio global.
- Facilitan la gestión de dependencias.
- Mejoran la mantenibilidad del código.
- Soportan carga diferida (lazy loading) de módulos.

#### Consideraciones:
- Los módulos se ejecutan en modo estricto por defecto.
- Las rutas de importación deben ser relativas o absolutas y deben incluir la extensión del archivo.
- No se puede usar `require` en módulos, ya que es específico de CommonJS.

### CommonJS

CommonJS es un sistema de módulos utilizado principalmente en entornos de servidor, como Node.js. A diferencia de los módulos de JavaScript estándar (ES Modules), CommonJS utiliza la función `require` para importar módulos y `module.exports` para exportarlos.

#### Ejemplo de uso:

Archivo `modulo.js`:
```javascript
module.exports = {
    saludar: (nombre) => `Hola, ${nombre}`
};
```

Archivo `main.js`:
```javascript
const { saludar } = require('./modulo');

console.log(saludar('Juan'));
```

#### Consideraciones:
- CommonJS es ampliamente utilizado en Node.js, pero no es compatible de forma nativa con navegadores.
- Los módulos CommonJS se cargan de forma síncrona, lo que puede ser menos eficiente en ciertos casos.

---

### Modo Estricto

El modo estricto (`"use strict"`) es una característica de JavaScript que permite escribir código más seguro y evitar errores comunes. Al habilitarlo, se aplican reglas más estrictas en la sintaxis y el comportamiento del lenguaje.

#### Cómo habilitarlo:
Se puede activar al inicio de un archivo o función:

```javascript
"use strict";

x = 10; // Error: 'x' no está declarado
```

#### Beneficios:
- Evita el uso de variables no declaradas.
- Prohíbe ciertas palabras reservadas para futuras versiones de JavaScript.
- Ayuda a prevenir errores silenciosos.
- Mejora el rendimiento en algunos motores de JavaScript.

#### Consideraciones:
- Los módulos de JavaScript (`type: "module"`) utilizan el modo estricto de forma predeterminada.
- No se puede desactivar una vez habilitado.
- Es recomendable usarlo para escribir código más robusto y mantenible.


# Ejercicio

Realizar una página que permita gestionar listas de tareas. Cada lista hace referencia a un estado de la tarea y los estados van es secuencia de izquierda a derecha.

Use la siguiente estructura para manipular los datos

```js
export default tasks = [
    {
        id:1,
        name:"",
        description:"",
        state:""
    }    
]
```



