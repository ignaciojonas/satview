Instrucciones para bajar el código y compilarlo usando _SVN_ y _Maven_


---

# Introducción #

---


Esta página contiene un par de pasos sencillos para bajar el código fuente desde este repositorio y compilarlo para obtener un jar.
Luego, se puede correr el programa como cualquier otro JAR!

Para hacer todo esto vamos a usar _Maven_, ya que parece ser la forma más fácil y además es una herramienta bastante poderosa y con la que varios parecen estar familiarizados!


---

# Herramientas necesarias #

---


Para realizar todos los pasos de este tutorial se necesitan solamente 2 herramientas:

  * SVN: para obtener el código fuente de la aplicación _satview_
  * Maven: para  obtener las dependencias necesarias y crear el JAR con el código fuente.

Pasemos a las intrucciones para obtener ambas herramientas

## Instalando SVN ##

Para realizar la instalación en sistemas Linux, se puede usar el administrador de paquetes e instalar el paquete **subversion**. Una forma sencilla de hacer esto es ejecutar el siguiente comando en la consola (para sistemas con `apt-get`, como Ubuntu o Debian)

> `fede@fede-ILD:$ sudo apt-get install subversion`

En sistemas Windows, lo que se debe hacer es obtener el binario de **svn** y colocarlo en el path de ejecución, para poder usarlo como un comando.
Descargar los binarios desde

> http://subversion.tigris.org/servlets/ProjectDocumentList?folderID=8100

el paquete con la versión más reciente en el momento de hacer este tutorial se llama `svn-win32-1.6.1.zip`.
Luego se descomprime este archivo en una locación cualquiera y se modifica la variable de entorno _PATH_ para que encuentre la carpeta **bin** dentro de la carpeta recién descomprimida.
Por ejemplo, si se descomprime el archivo en `C:\svn-1.1.6` entonces se debe modificar la variable _PATH_ para que contenga también la dirección

> `C:\svn-1.1.6\bin`

Si esto se realizó correctamente, podemos dirigirnos a la consola y ejecutar el comando

> `fede@fede-ILD:~$ svn help`

y nos dará una lista completa con todos los comandos extras que puede recibir _svn_

## Instalando _Maven_ ##

Hay muchos y muy buenos tutoriales para instalar _Maven_. En la página oficial se pueden encontrar las instrucciones para bajar e instalar la última versión.

http://maven.apache.org/download.html

Pero básicamente se resume a:

1) Asegurarse de tener instalado un JDK, ya que con un JRE no es suficiente para usar _Maven_
2) Bajar y descomprimir el archivo zip en el sistema
3) Setear la variable de entorno **M2\_HOME** apuntando al directorio en donde se descomprimieron los archivos del paso anterior
4) Setear la variable de entorno **M2\_REPO** apuntando a la dirección en donde se quiere contener el **repositorio** de _maven_

5) **Opcional:** se puede agregar el ejecutable de _maven_ a la variable de entorno **PATH** para poder usar el comando `mvn` sin tener que especificar la ruta completa. Esto se hace agregando la ruta **M2\_HOME/bin** a la variable **PATH**

Una vez hecho esto se puede corroborar que se instaló correctamente _maven_ usando simplemente el comando

> `fede@fede-ILD:$ mvn -version`

> `Maven version: 2.0.9`
> `Java version: 1.6.0_07`
> `OS name: "linux" version: "2.6.27-11-generic" arch: "amd64" Family: "unix"`


---

# ¿Cómo bajar y compilar el código usando _Maven_? #

---


Una vez instalados _Maven_ y _SVN_ podemos proceder a bajar y compilar el código. Lo primero es obtener el código desde el repositorio.

## Usar _maven_ para descargar el código ##

Lo primero que hay que hacer es crear el archivo **pom.xml** en el directorio en donde se quiere descargar el código y poner el siguiente contenido en él:

> pom.xml
```
 <project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>satview</groupId>
	<artifactId>satview</artifactId>
	<version>1.0</version>
	<packaging>jar</packaging>
	
	<scm>
		<connection>
			scm:svn:http://satview.googlecode.com/svn/trunk/satview</connection>
		<developerConnection>
			scm:svn:http://satview.googlecode.com/svn/trunk/satview</developerConnection>
	</scm>
 </project>
```

Una vez creado y salvado el archivo **pom.xml**, se corre el comando

> `mvn scm:checkout`

para extraer el código desde el repositorio.

## Compilar el código ##

Ahora estamos en posición de compilar el código, para ello tenemos que encontrar sus dependencias y agregarlas de forma tal que se puedan resolver y usar los JARs externos correspondientes.

### Resolviendo las dependencias ###
Para descargar las dependencias basta con indicar en el archivo **pom.xml** cuáles son estas dependencias y dejar que _maven_ las descargue y las agregue a su repositorio local. Esto se hace agregando lo siguiente al archivo pom:

```
 <project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">

 ...

         <dependencies>
		<dependency>
			<groupId>castor</groupId>
			<artifactId>castor</artifactId>
			<version>1.0M2</version>
		</dependency>
		
		<dependency>
			<groupId>commons-logging</groupId>
			<artifactId>commons-logging</artifactId>
			<version>1.1</version>
		</dependency>
		<dependency>
			<groupId>com.jgoodies</groupId>
			<artifactId>forms</artifactId>
			<version>1.1.0</version>
		</dependency>
		<dependency>
			<groupId>com.sun.media</groupId>
			<artifactId>jai_codec</artifactId>
			<version>1.1.2</version>
		</dependency>
		<dependency>
			<groupId>javax.media</groupId>
			<artifactId>jai_core</artifactId>
			<version>1.1.2</version>
		</dependency>
		<dependency>
			<groupId>jfree</groupId>
			<artifactId>jcommon</artifactId>
			<version>1.0.0</version>
		</dependency>
		<dependency>
			<groupId>jfree</groupId>
			<artifactId>jfreechart</artifactId>
			<version>1.0.1</version>
		</dependency>
		<dependency>
			<groupId>xerces</groupId>
			<artifactId>xercesImpl</artifactId>
			<version>2.9.1</version>
		</dependency>
		
		<dependency>
			<groupId>l2fprod.common</groupId>
			<artifactId>directorychooser</artifactId>
			<version>7.3</version>
		</dependency>
		
	</dependencies>
 </project>
```

Con esto simplemente indicamos los jars de los cuales depende este proyecto, pero todavía no vamos a correr ningún comando ya que a nosotros nos interesan para la fase en la cual se cree el JAR de salida de nuestro proyecto.

### Configurando el JAR de salida ###

Para decirle a _maven_ el formato en que queremos nuestro JAR de salida y configurar algunos parámetros como el `classpath` de la aplicación, los directorios del código y de los archivos de salida y los niveles de _compliance_ para ambos, vamos a agregar la sección `<build>` en el archivo **pom.xml** y 3 plugins dentro de ella (más precisamente, en la sección `<plugins>` dentro de `<build>`).

```
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">

          <build>
		<sourceDirectory>target/checkout/src</sourceDirectory>
		<outputDirectory>target/checkout/bin</outputDirectory>
                <plugins>
                        <!-- plugin para el nivel de compliance del codigo y del target -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<configuration>
					<source>1.5</source>
					<target>1.5</target>
				</configuration>
			</plugin>
			
			<!-- plugin para elegir la clase principal-->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-jar-plugin</artifactId>
				
				<configuration>
					<archive>
						<manifest>
							<mainClass>edu.pdi2.Main</mainClass>
							<packageName>edu.pdi2</packageName>
							<addClasspath>true</addClasspath>
							
							<classpathPrefix>${m2.repo}/</classpathPrefix>
							<classpathMavenRepositoryLayout>true</classpathMavenRepositoryLayout>
							<!--<customClasspathLayout>
								WEB-INF/lib/${artifact.groupIdPath}/${artifact.artifactId}-${artifact.version}${dashClassifier?}.${artifact.extension}</customClasspathLayout>
							-->
						</manifest>
						<manifestEntries>
							<mode>development</mode>
							<url>${pom.url}</url>
						</manifestEntries>
					</archive>
				</configuration>
			</plugin>
			
			<plugin>
				<!-- NOTE: We don't need a groupId specification because the group is
				org.apache.maven.plugins ...which is assumed by default.
				-->
				<artifactId>maven-assembly-plugin</artifactId>
				<configuration>
					<descriptorRefs>
						<descriptorRef>jar-with-dependencies</descriptorRef>
					</descriptorRefs>
				</configuration>
			</plugin>

               </plugins>
         </build>
     
      ...
 </project>
```

Ahora ya estamos en condiciones de correr la generación del JAR para nuestro proyecto. Posicionados desde el directorio en donde se encuentra el archivo _pom_, usamos el comando

> `mvn package`

y (entre otras cosas) debería indicarnos que hubo un error! :(

El error se debe a que no se pudieron descargar algunas dependencias para el proyecto. Ellas son:

  * jai\_core
  * jai\_codec
  * directorychooser

Esto se debe a que no todos los jars son siempre accesibles desde repositorios públicos para _maven_. Esto se soluciona fácilmente: lo que hay que hacer es obtener los jars en forma manual e **instalarlos** en el repositorio local de _maven_ para que la próxima vez no intente descargarlos, sino que use los que nosotros descargamos.

### Instalando los jars faltantes en forma manual ###

Lo primero que se debe hacer es descargar los jars y descomprimirlos en algún directorio. Los jars para **jai\_codec** y **jai\_core** se pueden obtener desde la página de Sun. En este tutotial se descargó la versión **Linux CLASSPATH install** de la JAI disponible en la siguiente página

> https://cds.sun.com/is-bin/INTERSHOP.enfinity/WFS/CDS-CDS_Developer-Site/en_US/-/USD/ViewProductDetail-Start?ProductRef=7341-JAI-1.1.2-oth-JPR@CDS-CDS_Developer

Luego de descomprimir el archivo, hay que dirigirse hacia adentro de la carpeta donde se encuentran los jars de la JAI e instalarlos

Nos dirigimos al directorio en donde se encuentran los jars

> `fede@fede-ILD:$ cd ~/jai-1_1_2/lib/`

Y ahora los instalamos.
Primero el jar de **jai\_core**

> `mvn install:install-file -DgroupId=javax.media -DartifactId=jai_core -Dversion=1.1.2 -Dpackaging=jar -Dfile=jai_core.jar`

y luego el de **jai\_codec**

> ` mvn install:install-file -DgroupId=com.sun.media -DartifactId=jai_codec -Dversion=1.1.2 -Dpackaging=jar -Dfile=jai_codec.jar`

Para instalar el jar correspondiente a **directorychooser** se hace lo mismo que antes:

Bajar y descomprimir los archivos desde

> http://l2fprod.com/common/download.php

dirigirse a la carpeta en donde se encuentra el jar llamado _lf2prod-common-directorychooser.jar_ y ejecutar el comand

> `mvn install:install-file -DgroupId=l2fprod.common -DartifactId=directorychooser -Dversion=7.3 -Dpackaging=jar -Dfile=l2fprod-common-directorychooser.jar`

**Nota importante:** En este ejemplo se han usado las versiones **1.1.2** de la _JAI_ y la versión **7.3** de _l2fprod-common_. Si el usuario desea usar versiones distintas debería indicarlo en el archivo _pom_ (cambiando el tag `<version>` dentro de las dependencias) y cambiar también la versión cuando se están instalando los jars bajados de internet.
Por ejemplo, si se desea usar la versión **1.1.3** de la _JAI_, cuando se instalen los jars se deben usar los siguientes comandos:

> `mvn install:install-file -DgroupId=javax.media -DartifactId=jai_core -Dversion=1.1.3 -Dpackaging=jar -Dfile=jai_core.jar`

> ` mvn install:install-file -DgroupId=com.sun.media -DartifactId=jai_codec -Dversion=1.1.3 -Dpackaging=jar -Dfile=jai_codec.jar`

Y en el archivo _pom_ se deben cambiar las dependencias para que luzcan de esta forma

```
 <project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">

 ...

         <dependencies>

                ...

                <dependency>
			<groupId>com.sun.media</groupId>
			<artifactId>jai_codec</artifactId>
			<version>1.1.3</version>
		</dependency>
		<dependency>
			<groupId>javax.media</groupId>
			<artifactId>jai_core</artifactId>
			<version>1.1.3</version>
		</dependency>

                ...
          </dependencies>
 </project>
```

### Compilando el proyecto nuevamente ###

Ahora que ya se instalaron las dependencias ya se puede intentar la compilación de nuestro proyecto!. Para hacerlo corremos el comando

> ` mvn package -Dm2.repo=$M2_REPO`

Esto le dirá a _maven_ que corra la compilación y que use una variable de entorno para ubicar el repositorio y apuntar a los jars necesarios (la variable de entorno **M2\_REPO** ya se encontraba configurada desde que se realizó la instalación de _maven_ al principio de este tutorial).


---

# Voilà! #

---


Ahora ya deberíamos tener el jar listo para correr dentro del directorio `target`. para correrlo simplemente hay que ejecutar el comando

> `java -jar satview-1.0.jar`