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

## Programas

| Programa | Para qué sirve | Estado |
|----------|----------------|--------|
| CrearFichero_GabrielValencia.java | Crea el .dat con 5 zapatillas | Hecho |
| ModificarFichero_GabrielValencia.java | Cambia los datos de una zapatilla a partir de su código | En proceso |
| CrearFicheroXML_GabrielValencia.java | Crea un XML con los datos del .dat | Pendiente |
| LeerFicheroXML_GabrielValencia.java | Muestra lo que hay en el XML | Pendiente |
| Menú principal | Para elegir qué programa ejecutar | Pendiente |

## Autor

Gabriel Valencia