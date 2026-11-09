# Gestión de zapatillas con ficheros en Java

Proyecto de la Unidad 1 de Acceso a Datos (Ficheros), 2º DAM.
IES Enrique Tierno Galván

## De qué va el proyecto

Es un programa en Java para llevar el catálogo de zapatillas de una tienda.
Las zapatillas se guardan en un fichero binario, se pueden modificar, se
pasan a un fichero XML, se vuelven a leer desde ese XML y, por último, se
convierten en una página HTML con una tabla.

## Por qué he elegido este tema

Siempre me han gustado la moda y, sobre todo, las zapatillas. Con 18 años
empecé a trabajar en Foot Locker, donde trabajaba con marcas como Nike,
New Balance y Asics, que son de mis favoritas. Eso me gustó todavía más,
porque podía ver los modelos nuevos antes de que salieran e incluso
ayudar a promocionarlos en la tienda.

Después pasé a trabajar en Skechers. No es una marca que yo usara, pero
es muy conocida y, con el tiempo y trabajando con ella, también me ha
acabado gustando.

Además, trabajo en el almacén, y allí somos nosotros los que nos
encargamos del stock y de los precios. Por eso ya sabía cómo funciona
todo esto en una tienda de verdad, y tenía claro que era un buen tema
para el proyecto.

Al conocer el tema y trabajar cada día con marcas que me gustan tanto,
hacer la práctica se me ha hecho mucho más ameno. Por eso los datos que
se pueden modificar son el precio y el stock: son los que cambian en el
día a día de una tienda.

## Cómo está organizado

Es un proyecto Maven. El código está en `src/main/java` y los ficheros que
crean los programas aparecen en la carpeta principal.

Uso dos librerías que Maven descarga solo gracias al `pom.xml`:
- XStream, para pasar las zapatillas a XML y leerlas de vuelta.
- Gson, por si se quiere pasar a JSON más adelante.

## Datos de cada zapatilla

| Campo    | Tipo    | Lo que ocupa en el fichero |
|----------|---------|----------------------------|
| Código   | int     | 4 bytes                    |
| Nombre   | String  | 20 letras, 40 bytes        |
| Marca    | String  | 20 letras, 40 bytes        |
| Precio   | float   | 4 bytes                    |
| En stock | boolean | 1 byte                     |
| Tienda   | String  | 20 letras, 40 bytes        |
| Total    |         | 129 bytes                  |

Los datos se guardan siempre en este orden, y hay que leerlos en el
mismo orden porque si no salen mal.

## Cómo está hecho el fichero binario

El fichero InformacionZapatillas.dat es de acceso aleatorio. Esto quiere
decir que no hace falta leerlo entero desde el principio, sino que se puede
ir directamente a la zapatilla que quieras.

Para poder hacer esto, todas las zapatillas tienen que ocupar lo mismo,
en mi caso 129 bytes cada una.

### Los textos

Cada letra ocupa 2 bytes y todos los textos tienen que medir 20 letras.
Para conseguirlo uso un StringBuffer con setLength(20). Si el texto es
más corto se rellena hasta 20, y si es más largo se corta. Al leerlos,
lo que sobra se quita con trim().

### Cómo encontrar una zapatilla

Como los códigos van seguidos (1, 2, 3...), para saber dónde empieza
cada zapatilla se usa esta cuenta:

    posición = (código - 1) * 129

| Código | Byte donde empieza |
|--------|--------------------|
| 1      | 0                  |
| 2      | 129                |
| 3      | 258                |
| 4      | 387                |
| 5      | 516                |

Con 5 zapatillas el fichero ocupa 5 * 129 = 645 bytes. Para saber cuántas
zapatillas hay, divido lo que ocupa el fichero entre 129.

## Los programas

### Menú principal (Menu_GabrielValencia)
Es el programa que hay que ejecutar para usar todo el proyecto. Muestra
estas opciones:

    1. Crear fichero de zapatillas
    2. Modificar una zapatilla
    3. Crear XML
    4. Leer XML
    5. Convertir XML a HTML (XSLT)
    0. Salir

Lee la opción con un Scanner y, con un switch, llama al programa elegido.
Todo va dentro de un do-while, así que el menú vuelve a salir después de
cada opción hasta que eliges 0. Si escribes un número que no está en la
lista, avisa de que la opción no es válida.

Lo normal es usarlas en orden la primera vez: 1 para crear el fichero,
3 para pasarlo a XML, 4 para leerlo y 5 para crear la tabla HTML. La 2
se puede usar en cualquier momento después de la 1.

### 1. Crear el fichero (CrearFichero_GabrielValencia)
Guarda 5 zapatillas en InformacionZapatillas.dat. Los datos salen de unos
arrays, uno por cada campo.

### 2. Modificar una zapatilla (ModificarFichero_GabrielValencia)
Solo dejo cambiar el precio y el stock, porque son los datos que cambian
de verdad en una tienda: el precio sube o baja con las rebajas, y el
stock cambia cuando se agota una zapatilla o llega un camión con más.
El código, el nombre, la marca y la tienda son fijos.

1. Pide el código. Si no es un número o no existe, avisa y termina.
2. Va directamente a esa zapatilla con seek y muestra sus datos.
3. Pide el nuevo precio y si hay stock o no.
4. Salta al byte 84 de la zapatilla, donde empieza el precio, y guarda
   el precio y el stock nuevos, que van seguidos.
5. Muestra cómo ha quedado.

El precio se escribe con coma (por ejemplo 89,99) y el stock como true o false.

### 3. Crear el XML (CrearFicheroXML_GabrielValencia)
Lee todas las zapatillas del .dat, crea un objeto Zapatilla con cada una
y las guarda en una lista. Después XStream convierte la lista entera en
InformacionZapatillas.xml, que se llama igual que el .dat. Con los alias,
la raíz se llama `zapatillas` y cada una `zapatilla`.

Para leer el .dat de principio a fin uso un bucle que termina cuando salta
EOFException, que es el aviso de que se ha acabado el fichero.

### 4. Leer el XML (LeerFicheroXML_GabrielValencia)
Lee InformacionZapatillas.xml con XStream y lo convierte otra vez en una
lista de zapatillas, que muestra por pantalla. Hay que poner los mismos
alias que al crearlo y dar permiso a la clase Zapatilla, porque si no
XStream no deja crear los objetos.

### 5. Convertir a HTML (ConvertirXSLT_GabrielValencia)
Junta InformacionZapatillas.xml (los datos) con plantillaZapatillas.xsl
(el diseño) y crea tablaZapatillas.html, una página con una tabla de las
zapatillas.

### La clase Zapatilla
Representa una zapatilla con sus 6 datos. XStream usa los nombres de sus
atributos como nombres de las etiquetas del XML.

## Ficheros que se generan

| Fichero | Lo crea | Qué es |
|---------|---------|--------|
| InformacionZapatillas.dat | CrearFichero | Fichero binario de acceso aleatorio |
| InformacionZapatillas.xml | CrearFicheroXML | Las zapatillas en XML |
| tablaZapatillas.html | ConvertirXSLT | Tabla para ver en el navegador |

## Autor

Gabriel Valencia Bañares