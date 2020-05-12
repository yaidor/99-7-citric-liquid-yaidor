<!-- 1.0.3-b1 -->
# 99.7% Citric Liquid

Base code for CC3002's *99.7% Citric Juice* Project.

The project consists in creating a (simplified) clone of the game **100% Orange Juice**
developed by [Orange_Juice](http://daidai.moo.jp) and distributed by 
[Fruitbat Factory](https://fruitbatfactory.com).

# ¿uso?

El programa se trabaja al igual que el repositorio original, los cambios realizados no hacen conflicto al con la operación del programa.

Los cambios que se realizaron fue realizar clases abstractas para panel y para contenders (que son los distintos jugadores, wild units y bosses)

Para poder correr el repositorio es necesario clonarlo en su terminal con git

```sh
$ cd dir\
$ git clone git@github.com:CC3002-Metodologias/99-7-citric-liquid-yaidor.git
```

Una vez clonado el repositorio es necesario abrir [IntelliJ IDEA][df1] y confirmar que se tiene Gradle (Settings/Plugins -> Search: Gradle) habilitado.

Confirmado que se tiene Gradle habilitado, procedemos a abrir el programa a través de IntelliJ IDEA:

File-> New-> Project from Existing Sources 

una vez aquí buscamos la dirección de nuestro directorio con nombre `99-7-citric-liquid-yaidor`, seleccionamos el archivo `99-7-citric-liquid-yaidor/build.gradle.kts` y aceptamos el "Import Gradle Project"
# Nuevas Clases!

  - AbstractPanel
    -BonusPanel
    -BossPanel
    -DrawPanel
    -DropPanel
    -EncounterPanel
    -HomePanel
    -NeutralPanel

  - AbstractContender
    -Boss
    -Jugador
    -Wild

# Supuestos Importantes
  - La función decide se realiza de manera aleatoria tanto para los Jugadores, los Wild Unit y los Boss. Para los jugadores se realiza de esta manera para poder hacer los test en para ambos casos (defender o evadir). El fin de esta función es luego poder editarla sin problemas una vez que tenga la interfaz de usuario entregando un valor booleano si quiere uno u el otro.
  - Se crearon acciones vacías para los paneles de tal manera que en un futuro sean editados para la conveniencia de uso.
  - Aún no se realiza un límite de "next panels" ya que no tengo infromación de que exista un límite, puede ser que los paneles "teletransporten" a los jugadores a otros paneles.
  - Así mismo no se realiza límite para personajes en un panel, aún cuando el juego no admite a más de 4 jugadores según enunciado. 
  - En un futuro se plantea editar paneles para que puedan aceptar tanto jugadores como Wild Units y Boss, de esta manera poder tener un lugar estátito para estos "contenders."
  

  [df1]: <https://www.jetbrains.com/idea/>
