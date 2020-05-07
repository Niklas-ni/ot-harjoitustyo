# Testausdokumentti


## DomainAndDaotests

Olen tehnyt luokan DomainAndDaotests jossa sitten neljä testi luokkaa Players,Playersservice,Paybox,Payboxsrvice-test.
Jos olisi ollt aikaa olisin vielä voinnut jakaa nämä dao ja domain luokkiin mutta aika ei riittänyt. nyt molemmat tehdään samassa. 
Players ja Paybox testailevat vain helppoja getterietä ja mahdollisuuksia luoda näitä.


## PlayersServiceTest

Tässä luodaan uusia tietokantoja tietyillä nimillä jotka myöhemmin teardownissa poistetaan jotta testejä pystyy suorittamaan uudestaan.
Tässä tehdään useamman yhidistetyn metodin testejä ja toimivuutta. 
Myös virhe syöteittä on tässä otettu huomioon kuten esimerkiksi tyhjiä syötteitä tai joukkue vain numeroilla.

## PayboxServiceTest

näissä testeissä luodaan yksi valmis tietokanta johon lisätään joukkueita ja salasanoja jota voidaan sitten myöhemmin testailla.
Testiluokassa on myös teardown sitten lopussa tälle taulukolla jotta testejä pystyy suorittamaan monta kertaa.
Myös tässä testiluokassa on otettu huomioon virhesyöttitä ja vahinkoja. 


## Järjestelmätestaus
Sovellusta on kokeiltu OSX- ja Linux-ympäristöissä.

## Testikatavuus
testikattavuudessa on otettu kaikki luokkien dao ja domain metodeja huomioon.


![Luonnos](Kuvat/TestiMäärä.png)
