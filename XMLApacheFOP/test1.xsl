<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
      xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
      xmlns:fo="http://www.w3.org/1999/XSL/Format"
      xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" 
      xmlns:scalar="http://scalar.usc.edu/2012/01/scalar-ns#" 
      xmlns:art="http://simile.mit.edu/2003/10/ontologies/artstor#" 
      xmlns:prov="http://www.w3.org/ns/prov#" 
      xmlns:dcterms="http://purl.org/dc/terms/" 
      xmlns:ov="http://open.vocab.org/terms/" 
      xmlns:sioc="http://rdfs.org/sioc/ns#" 
      xmlns:oac="http://www.openannotation.org/ns/">
  <xsl:output method="xml" indent="yes"/>
  <xsl:template match="/rdf:RDF">
    <fo:root>
      <fo:layout-master-set>
        <fo:simple-page-master master-name="A4-portrait"
              page-height="29.7cm" page-width="21.0cm" margin="2cm">
          <fo:region-body/>
        </fo:simple-page-master>
      </fo:layout-master-set>
      <fo:page-sequence master-reference="A4-portrait">
        <fo:flow flow-name="xsl-region-body">
          <xsl:for-each select="rdf:Description">
            <fo:block>
              <xsl:value-of select="sioc:content"/>
            </fo:block>
          </xsl:for-each>
        </fo:flow>
      </fo:page-sequence>
    </fo:root>
  </xsl:template>
</xsl:stylesheet>