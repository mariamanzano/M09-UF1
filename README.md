# M09-UF1

## Introducció

Aquesta aplicació és l’ampliació de l’aplicació de l’activitat 1, on s’han afegit nous formats per generar i mostrar encàrrecs: **aleatori** i **serialitzable**.

També s’ha afegit la possibilitat de modificar encàrrecs ja generats en format aleatori.

## Estructura del Codi

### Classe `Article`

En aquesta classe s’ha afegit un nou atribut `preu` i la classe s’ha modificat per implementar la serialització, que és el procés amb el qual codifiquem l’objecte `article` per tal de transmetre'l per una xarxa a qualsevol altre dispositiu.

### Classe `Encarrec`

La classe `Encarrec` és nova i s’ha afegit per facilitar la gestió dels encàrrecs dels clients.

### Classe `Botiga`

La classe `Botiga` s’encarrega de gestionar els encàrrecs. Permet generar, mostrar i modificar encàrrecs. 

S’han actualitzat i afegit els següents mètodes:

#### 1. `main()`

En el `switch`, s’ha afegit l’opció de poder modificar encàrrecs ja creats.

#### 2. `generaEncarrec()`

En aquest mètode, s’ha afegit la capacitat de generar encàrrecs en format **aleatori** i **serialitzable**.

#### 3. `generaAleatori()`

Genera un fitxer d’accés aleatori amb el `RandomAccessFile` que conté tots els encàrrecs. Utilitzem l’`StringBuffer` per gestionar i emmagatzemar les dades de cada encàrrec.

#### 4. `generaSerialitzable()`

Fem servir el mètode `ObjectOutputStream` per generar el fitxer serialitzable, recorrem la llista d’encàrrecs i els serialitzem amb el mètode `writeObject()`.

#### 5. `mostraEncarrec()`

En aquest mètode, hem afegit la possibilitat de mostrar fitxers en format **aleatori** i **serialitzable**.

#### 6. `mostraAleatori()`

Utilitza `RandomAccessFile` per llegir el fitxer d’accés aleatori que conté els encàrrecs, recorrent-los un per un i imprimint les dades de cada encàrrec per pantalla.

#### 7. `mostraSerialitzable()`

El mètode `mostraSerialitzable()` llegeix el fitxer amb el `ObjectInputStream`. 

Deserialitzem l'array d'objectes amb el `readObject` i el guardem en un array d’`Encarrec`. El recorrem i imprimim les dades per pantalla.

#### 8. `modificaEncarrec()`

El mètode `modificaEncarrec()` demanarà a l’usuari el nom del fitxer a modificar, l’id de l’encàrrec que es vol modificar i les noves dades de data i telèfon. 

Modificarà les dades amb el `RandomAccessFile` per accedir i actualitzar les dades directament al fitxer.
