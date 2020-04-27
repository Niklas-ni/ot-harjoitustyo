# käyttöohje

## Ohjelman käynnistäminen
ohjelma voidaan käynnistää komennolla 
*mvn compile exec:java -Dexec.mainClass=main.Main*
ohjelman pikakuvakkeella 
tai netbeansin kautta.

## Alkunäkymä
Sovellus käynnistyy ensimmäiseen näkymään missä on joko login tai create new paytable
jos tiedät jonkun login voit suoraan siirtyä eteenpäin täyttämällä login.
Jos olet uusi käyttäjä joudut tekemään uuden create paytable napilla.

![Luonnos](Kuvat/firstpage.png)


## Create näkymä

create näkymässä voi lisätä uuden joukkueen ja sille salasanan täyttämällä 
name ja password.

![Luonnos](Kuvat/createpage.png)


## Seuraus näkymä

jos vain tietää joukkueen nimen eikä salasanaa voi tästä näkymästä katso mitä 
sakkoja on meneillä ja kenellä mitä sakkoja.

![Luonnos](Kuvat/notadminpage.png)

## Admin näkymä


Admin näkymään pääset täyttämällä admin salasanan edellisen näkymän alakulmassa.
Admin näkymässä voit sitte hallinoida pelaajia ja summia.
tästä näkymästa pääset pois back napilla. 

![Luonnos](Kuvat/adminpage.png)



