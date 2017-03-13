<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<!-- specifies a template depending on type of data and formatting desired
"match" designates where the stylesheet will pull content from. -->
<xsl:template match="/rdf:RDF">
<!-- what are the most important tags to pull from? -->

<!-- not relevant for our xml file 
<html>
<body>
-->

<Description>

<xsl: for-each select="dcterms:title">
	<xsl:value-of select="dcterms:title"/><br /> <!--adds a break after title -->
<!-- comment out? is this covered in the for-each statement?
<dcterms:title>
</dcterms:title>
-->
</xsl: for-each>


<xsl: for-each select="dcterms:description">
	<xsl:value-of select="dcterms:description"/><br />
<!--
<dcterms:description>
</dcterms:description>
-->
</xsl: for-each>



<xsl: for-each select="dcterms:content">
	<xsl:value-of select="dcterms:content"/><br />
<!--
<sioc:content>
</sioc:content>
-->
</xsl: for-each>


</Description>



<!-- not relevant for our xml file 
</html>
</body>
-->








</xsl:template>


<!-- all opening tags must have closing tags -->
</xsl:stylesheet>



<!--
<!-- start different example -->
<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">

<html>
  <head>
    <!-- Get the head values here... -->
    <xsl:apply-templates select="/html/head/*" />
  </head>
  <body>
    <table width="100%" border="1" bgcolor="LemonChiffon">
      <tr>
        <td colspan="2" width="100%">
        <h1>Header</h1>
        (common to every page of the site)</td>
      </tr>
      <tr height="300" valign="top">
        <td width="75%" bgcolor="Aqua">
			<!-- Get the body here -->
     		<xsl:apply-templates select="/html/body/*"/>
       </td>
        <td width="25%">
        <h1>Headlines</h1>
        (common to every page of the site)</td>
      </tr>
    </table>
  </body>
</html>
</xsl:template>

<!-- Identity transformation -->
<xsl:template match="@*|*">
	<xsl:copy>
		<xsl:apply-templates select="@*|node()"/>
	</xsl:copy>
</xsl:template>

</xsl:stylesheet>

-->