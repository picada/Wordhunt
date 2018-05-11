# Käyttöohje

Lataa tiedosto [wordhunt.jar](https://github.com/picada/otm-harjoitustyo/releases/tag/loppupalautus)

## Ohjelman käynnistäminen 

Ohjelman olettaa, että käyttäjän tietokoneelle on asennettu SQLite. Muuta konfiguraatiota ei tarvita, pelin hyödyntämä sanalisa sekä säännöt on upotettu mukaan jar-tiedostoon. Sovellus luo käyttämänsä tietokannan tarvittaessa käynnistäessä.

## Ohjelman käynnistäminen 

Ohjelma käynnistetään komennolla java -jar wordhunt.jar

## Kirjautuminen

Sovellus käynnistyy kirjautumisnäkymään:

<img src="https://github.com/picada/otm-harjoitustyo/blob/master/Wordhunt/dokumentointi/kuvat/kirjaudu.png">

Kirjautuminen onnistuu kirjoittamalla olemassaoleva käyttäjätunnus syötekenttään ja painamalla "Kirjaudu sisään".

## Uuden käyttäjän luominen

Kirjautumisnäkymästä on mahdollista siirtyä uuden käyttäjän luomisnäkymään panikkeella "Luo uusi käyttäjä".

Uusi käyttäjä luodaan syöttämällä tiedot syötekenttiin ja painamalla "Luo".

<img src="https://github.com/picada/otm-harjoitustyo/blob/master/Wordhunt/dokumentointi/kuvat/uusika%CC%88ytta%CC%88ja%CC%88.png">

Jos käyttäjän luominen onnistuu, palataan kirjautumisnäkymään.

## Päävalikko

Onnistumisen kirjautumisen jälkeen avautuu päänäkymä, josta käyttäjä voi joko siirtyä lukemaan pelin säännöst, tarkastelemaan tuloksia tai pelinäkymään. Käyttäjä voi myös kirjautua ulos päänäkymän kautta.

<img src="https://github.com/picada/otm-harjoitustyo/blob/master/Wordhunt/dokumentointi/kuvat/p%C3%A4%C3%A4valikko.png">


## Säännöt

Tästä näkymästä löytyvät pelin säännöt.


## Tulokset

Tulosnäkymässä käyttäjä voi tarkastella sekä omia huipputuloksiaan sekä kaikkien käyttäjien tuloksista koottua top-listaa. Molemmat listat näyttävät maksimissaan kymmenen parasta tulosta. 


## Pelinäkymä

Pelinäkymään siirryttyään käyttäjä voi aloittaa uuden pelin painamalla Aloita peli -nappia, minkä jälkeen pelikello käynnistyy ja pelilauta aktivoituu. 

<img src="https://github.com/picada/otm-harjoitustyo/blob/master/Wordhunt/dokumentointi/kuvat/pelin%C3%A4kym%C3%A4.png">

Sanoja kerätään valitsemalla kirjaimet ruudukosta hiirivalinnalla yksi kirjain kerrallaan. Seuraavan kirjaimen tulee sijaita edellisen valitun ruudun vieressä joko pysty-, vaaka- tai vinosuunnassa. Sana päivittyy reaaliajassa sivun oikeaan laitaan, ja sanan voi syöttää 
järjestelmään painamalla ”Syötä sana”-nappia.

<img src="https://github.com/picada/otm-harjoitustyo/blob/master/Wordhunt/dokumentointi/kuvat/uusisana.png">

Käyttäjän klikattua "Syötä sana" -nappia sovellus tarkistaa, löytyykö kyseinen sana sovellukseen ajetusta sanalistasta. Mikäli sana löytyy, pelaajan pistesaldo kasvaa ja valittujen kirjainten tilalle arvotaan uudet satunnaiset kirjaimet. Mikäli sana ei ole hyväksytty, ohjelma ilmoittaa tästä erikseen.

Käyttäjä voi koska tahansa tyhjentää valintansa painamalla "Tyhjennä valinnat".

Käyttäjä voi missä tahansa vaiheessa peliä sekoittaa pelilaudan, mutta tämä vaikuttaa negatiivisesti pistesaldoon. Pistesaldo ei voi kuitenkaan mennä miinukselle.

Kun pelikellon aika loppuu, tallentuvat pelin pisteet automaattisesti tietokantaan eikä käyttäjä pysty enää valitsemaan uusia sanoja. Pelaaja voi halutessaan aloittaa uuden pelin samasta näkymästä.

<img src="https://github.com/picada/otm-harjoitustyo/blob/master/Wordhunt/dokumentointi/kuvat/gameover.png">

Mikäli käyttäjä poistuu pelistä kesken pelin, tulokset eivät tallennu tietokantaan ja peli nollautuu. 


