# Gestión de zapatillas con ficheros en Java

Proyecto de la Unidad 1 de Acceso a Datos (Ficheros), 2º DAM.
IES Enrique Tierno Galván, Parla.

## De qué va el proyecto

Es un programa en Java para llevar el catálogo de zapatillas de una tienda.
Las zapatillas se guardan en un fichero binario, se pueden modificar y
después se pasan a un fichero XML.

## Por qué he elegido este tema

(Aquí va tu texto.)

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

## Cómo está hecho el fichero

El fichero InformacionZapatillas.dat es de acceso aleatorio. Esto quiere
decir que no hace falta leerlo entero desde el principio, sino que se puede
ir directamente a la zapatilla que quieras.

Para poder hacer esto, todas las zapatillas tienen que ocupar lo mismo,
en mi caso 129 bytes cada una.

### Los textos

Cada letra ocupa 2 bytes y todos los textos tienen que medir 20 letras.
Para conseguirlo uso un StringBuffer con setLength(20). Si el texto es
más corto se rellena hasta 20, y si es más largo se corta.

Al leerlos, las letras que sobran se quitan con trim() para que el
texto salga limpio.

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

Con 5 zapatillas el fichero ocupa 5 * 129 = 645 bytes.

Para saber cuántas zapatillas hay, divido lo que ocupa el fichero entre
129. Así, si mañana se guardan más zapatillas, el programa sigue
funcionando sin tocar nada.

## Modificar una zapatilla

Solo dejo cambiar el precio y el stock, porque son los datos que cambian
de verdad en una tienda: el precio sube o baja con las rebajas, y el
stock cambia cuando se agota una zapatilla o llega un camión con más.
El código, el nombre, la marca y la tienda son fijos.

Así funciona el programa:

1. Pide el código de la zapatilla. Si no es un número, avisa y termina.
2. Comprueba que el código existe. Si no, avisa y termina.
3. Va directamente a esa zapatilla con seek y muestra sus datos.
4. Pide el nuevo precio y si hay stock o no.
5. Salta al byte donde empieza el precio y guarda los datos nuevos.
6. Muestra cómo ha quedado la zapatilla.

Dentro de cada zapatilla, el precio empieza en el byte 84
(4 del código, 40 del nombre y 40 de la marca). Como el stock va justo
detrás, se escriben los dos seguidos sin tener que volver a saltar.

Cosas a tener en cuenta al usarlo:
- El precio se escribe con coma, por ejemplo 89,99.
- El stock se escribe como true (sí hay) o false (no hay).

## Programas

| Programa | Para qué sirve | Estado |
|----------|----------------|--------|
| CrearFichero_GabrielValencia.java | Crea el .dat con 5 zapatillas | Hecho |
| ModificarFichero_GabrielValencia.java | Cambia el precio y el stock de una zapatilla a partir de su código | Hecho |
| CrearFicheroXML_GabrielValencia.java | Crea un XML con los datos del .dat | Pendiente |
| LeerFicheroXML_GabrielValencia.java | Muestra lo que hay en el XML | Pendiente |
| Menú principal | Para elegir qué programa ejecutar | Pendiente |

## Autor

Gabriel Valencia