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

# Controller

Se ha agregado la clase controller, parte fundamental del poyecto ya que es uno de los participantes en el modelo MVC, siendo el controller la C del modelo, valga la redundancia.

# Sate Pattern 

Se ha agregado el state patter como parte del proyecto para poder evaluar los turnos y las fases en las que se encuentran.
Los estados que podemos encontrar son los siguientes:
  - Start (el inicio, donde se le "otorga" el turno al jugador correspondiente)
  - IsKO (si el jugador esta fuera de combate, en otras palabras HP = 0 y es la fase para realizar el recovery)
  - NotKO (sigue en combate y estado donde se deben subir las estrellas)
  - PlayCard (estado que el usuario decide jugar una carta o no)
  - Move (estado en donde el jugador se mueve en el tablero)
  - WantFight (estado de decisión donde el usuario puede elegir si combate o no combate al momento de encontrarse con un panel que tiene un jugador dentro)
  - Path (otro estado de decisión donde el usuario elige podrá elegir el camino a tomar)
  - WantHome (al igual que los dos estados anteriores, el usuario puede decidir si quiere seguir moviendose o detenerse en el home panel que esta curzando, siempre e y cuando sea "dueño" de el.
  - StayPanel (estado terminal del movimiento/batalla/home fase donde la acción del panel es activada)
  - Fihting (estado donde el jugador se enfrenta a otra unidad)
  - DOrD (estado de decisón del usuario dentro de una batalla, DOrD es Defend Or Dodge, lo cual es lo que decide el usuario cuando es atacado de vuelta)
  - End (estado que termina el turno y hace entrega del turno al siguiente jugador)
  
  

# Supuestos Importantes
  - La función decide se realiza de manera aleatoria tanto para los Jugadores, los Wild Unit y los Boss. Para los jugadores se realiza de esta manera para poder hacer los test en para ambos casos (defender o evadir). El fin de esta función es luego poder editarla sin problemas una vez que tenga la interfaz de usuario entregando un valor booleano si quiere uno u el otro.
  - Se crearon acciones vacías para los paneles de tal manera que en un futuro sean editados para la conveniencia de uso.
  - Aún no se realiza un límite de "next panels" ya que no tengo infromación de que exista un límite, puede ser que los paneles "teletransporten" a los jugadores a otros paneles.
  - Así mismo no se realiza límite para personajes en un panel, aún cuando el juego no admite a más de 4 jugadores según enunciado. 
  - En un futuro se plantea editar paneles para que puedan aceptar tanto jugadores como Wild Units y Boss, de esta manera poder tener un lugar estátito para estos "contenders."

# Supuestos y más supuestos

Para esta entrega se usa el metodo del jugador llamado `jugador.roll()` que básicamente lanza un dado entregando un resultado aleatorio entre 1 y 6. Además se asume que habrá forma de testear exceptions en un futuro para poder mejorar los patrones de diseño.
  

  [df1]: <https://www.jetbrains.com/idea/>
