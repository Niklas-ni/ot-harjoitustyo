
# SakkoKassa

Sakkokassan tarkoitus on pitää kirjaa sakoista eri henilöille eri joukkueissa esim. 
Se on tehty helpottamaan kirjanpitoa. Tarkoitus on pystyä lisäämään joukkue ja siihen sisälle pelaajia joille voi sitten vaihtaa sakkojen suuruutta.

## Dokumentaatiot

* [Dokumentaatiot](https://github.com/Niklas-ni/ot-harjoitustyo/tree/master/Dokumentaatiot)
* [Työaikakirjanpito](https://github.com/Niklas-ni/ot-harjoitustyo/blob/master/Dokumentaatiot/ty%C3%B6aikakirjanpito.md)
* [Vaativuusmäärittely](https://github.com/Niklas-ni/ot-harjoitustyo/blob/master/Dokumentaatiot/M%C3%A4%C3%A4rittelydokumentti.md)
* [Arkkitehtuuri](https://github.com/Niklas-ni/ot-harjoitustyo/blob/master/Dokumentaatiot/arkkitehtuuri.md)
* [Käyttöohje](https://github.com/Niklas-ni/ot-harjoitustyo/blob/master/Dokumentaatiot/kayttoohje.md)



## Releases

### Ennen käyttöä
Jotta ohjelma toimii oikein käyttäjä tarvitsee java 11 tai uudemman version.
Muuten käyttäjä voi ladata releasen ja käyttää ohjelmaa.

### Viikko Releases

* [Release viikko 5](https://github.com/Niklas-ni/ot-harjoitustyo/releases/tag/v1.0)
* [Release viikko 6](https://github.com/Niklas-ni/ot-harjoitustyo/releases/tag/V2.0)

## Testaus

Ohjelma suoritetaan komennolla

*mvn compile exec:java -Dexec.mainClass=main.Main* 

Testit suoritetaan komennolla

*mvn test*

Testikattavuusraportti luodaan komennolla

*mvn jacoco:report*

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html


Suoritettavan jarin generointi Komento:

*mvn package*

generoi hakemistoon target suoritettavan jar-tiedoston Sakkokassa-1.0-SNAPSHOT.jar

Checkstyle

Tiedostoon checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla

*mvn jxr:jxr checkstyle:checkstyle*

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto target/site/checkstyle.html


JavaDoc generoidaan komennolla

*mvn javadoc:javadoc*

JavaDocia voi tarkastella avaamalla selaimella tiedosto target/site/apidocs/index.html
