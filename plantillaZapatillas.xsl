<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html><xsl:apply-templates /></html>
    </xsl:template>
    <xsl:template match="zapatillas">
        <head><title>TIPOS DE ZAPATILLA</title></head>
        <body>
            <h1>LISTA DE ZAPATILLAS</h1>
            <table border="1">
               <tr><th>Código</th><th>Nombre</th><th>Marca</th><th>Precio</th><th>Stock</th><th>Tienda</th></tr>
                <xsl:apply-templates select="zapatilla" />
            </table>
        </body>
    </xsl:template>
    <xsl:template match="zapatilla">
        <tr><xsl:apply-templates /></tr>
    </xsl:template>
    <xsl:template match="codigo|nombre|marca|precio|stock|tienda">
        <td><xsl:apply-templates /></td>
    </xsl:template>
</xsl:stylesheet>