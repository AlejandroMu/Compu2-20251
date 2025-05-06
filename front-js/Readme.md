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

### Webpack

Webpack es un empaquetador de módulos para aplicaciones JavaScript modernas. Permite combinar múltiples archivos y dependencias en un único archivo o en varios archivos optimizados para producción.

#### Instalación
Para usar Webpack, primero instálalo como dependencia en tu proyecto:

```bash
npm install --save-dev webpack webpack-cli
```

#### Configuración Básica
Crea un archivo `webpack.config.js` en la raíz de tu proyecto para configurar Webpack:

```javascript
const path = require('path');

module.exports = {
    entry: './src/index.js', // Archivo de entrada
    output: {
        filename: 'bundle.js', // Archivo de salida
        path: path.resolve(__dirname, 'dist'), // Carpeta de salida
    },
    mode: 'development', // Modo: 'development' o 'production'
};
```

#### Scripts en `package.json`
Agrega un script para ejecutar Webpack:

```json
"scripts": {
    "build": "webpack"
}
```

Ejecuta el comando para generar el archivo empaquetado:

```bash
npm run build
```

#### Beneficios de Webpack
- **Modularidad**: Permite dividir el código en módulos reutilizables.
- **Optimización**: Minimiza y optimiza el código para producción.
- **Compatibilidad**: Transpila código moderno para navegadores antiguos.
- **Carga de Recursos**: Soporta la carga de imágenes, estilos, y otros activos.

#### Uso con Babel
Para usar Webpack con Babel y transpilar código moderno, instala las dependencias necesarias:

```bash
npm install --save-dev babel-loader @babel/core @babel/preset-env
```

Actualiza el archivo `webpack.config.js` para incluir el cargador de Babel:

```javascript
module.exports = {
    module: {
        rules: [
            {
                test: /\.js$/, // Archivos .js
                exclude: /node_modules/, // Excluir node_modules
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env'], // Configuración de Babel
                    },
                },
            },
        ],
    },
};
```

#### Consideraciones
- Webpack es altamente configurable y soporta plugins para extender su funcionalidad.
- Es ideal para proyectos grandes y complejos que requieren optimización y modularidad.
- Para proyectos pequeños, considera alternativas más simples como Vite o Parcel.
- Usa el modo `production` para generar un código optimizado para despliegue.

#### Recursos Adicionales
- [Documentación oficial de Webpack](https://webpack.js.org/)
- [Guía de inicio rápido](https://webpack.js.org/guides/getting-started/)
- [Configuración avanzada](https://webpack.js.org/configuration/)
- [Plugins de Webpack](https://webpack.js.org/plugins/)
- [Cargadores de Webpack](https://webpack.js.org/loaders/)

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



