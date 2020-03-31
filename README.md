
## SakkoKassa

Sakkokassan tarkoitus on pitää kirjaa sakoista eri henilöille eri joukkueissa esim. 
Se on tehty helpottamaan kirjanpitoa. Tarkoitus on pystyä lisäämään joukkue ja siihen sisälle pelaajia joille voi sitten vaihtaa sakkojen suuruutta.

## Dokumentaatiot

[Dokumentaatiot](https://github.com/Niklas-ni/ot-harjoitustyo/tree/master/Dokumentaatiot)
[Työaikakirjanpito](https://github.com/Niklas-ni/ot-harjoitustyo/blob/master/Dokumentaatiot/ty%C3%B6aikakirjanpito.md)
[Vaativuusmäärittely](https://github.com/Niklas-ni/ot-harjoitustyo/blob/master/Dokumentaatiot/M%C3%A4%C3%A4rittelydokumentti.md)

## Testaus

Testit suoritetaan komennolla

*mvn test*

Testikattavuusraportti luodaan komennolla

*mvn jacoco:report*

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html
Suoritettavan jarin generointi
Komento:

:mvn package:

generoi hakemistoon target suoritettavan jar-tiedoston OtmTodoApp-1.0-SNAPSHOT.jar
JavaDoc

JavaDoc generoidaan komennolla

:mvn javadoc:javadoc:

JavaDocia voi tarkastella avaamalla selaimella tiedosto target/site/apidocs/index.html
